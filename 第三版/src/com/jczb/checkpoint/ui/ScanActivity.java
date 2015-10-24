package com.jczb.checkpoint.ui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.hdhe.uhf.reader.Tools;
import com.android.hdhe.uhf.reader.UhfReader;
import com.jczb.checkpoint.R;
import com.jczb.checkpoint.common.CommonUtil;
import com.jczb.checkpoint.common.Constants;
import com.jczb.checkpoint.common.FormatTools;
import com.jczb.checkpoint.common.ImageTools;
import com.jczb.checkpoint.manager.AppDownManager;
import com.jczb.checkpoint.manager.AppPhotoFileManager;
import com.jczb.checkpoint.manager.AppUpManager;
import com.jczb.checkpoint.manager.DownCongManager;
import com.jczb.checkpoint.manager.UserManager;
import com.jczb.checkpoint.model.AppDown;
import com.jczb.checkpoint.model.AppDownCong;
import com.jczb.checkpoint.model.AppPhotoFile;
import com.jczb.checkpoint.model.AppUp;
import com.jczb.checkpoint.model.User;
import com.jczb.checkpoint.plug.scanner.ScreenStateReceiver;
import com.jczb.checkpoint.plug.scanner.Util;
import com.jczb.checkpoint.ui.ProductInfoActivity.ImageAdapter;

/**
 * 安标抽查页面
 * 
 * @author wlc
 * @date 2015-3-17
 */
public class ScanActivity extends BaseActivity implements OnClickListener {

	// 这两个listView用于显示文字内容
	private ListView leftListView;
	private ListView rightListView;
	private ListView productInfo_listview;
	/**
	 * 不同点是LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化；
	 * 而findViewById()是找xml布局文件下的具体widget控件(如Button、TextView等) 具体作用：
	 * 1、对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.inflate()来载入；
	 * 2、对于一个已经载入的界面，就可以使用Activiyt.findViewById()方法来获得其中的界面元素。
	 */
	private LayoutInflater layoutInflater;

	private String userName; //登录的用户名
	private String currentTime;
	private Button buttonStart;
	private boolean runFlag = true;
	private boolean startFlag = false;
	// private boolean connectFlag = false;
	private UhfReader reader; // 超高频读写器
	private ScreenStateReceiver screenReceiver;
	// private TextView EPCInfoTextView;
	private Thread thread;
	private String EPCinfo; // 获取到的EPC串号

	private EditText msgInfo; // 输入的异常信息备注

	List<AppDownCong> appDownCongs; // 查询的结果]
	List<AppPhotoFile> list; //图片查询结果
	List<User> listUsers;
	private ImageView ivPic;
	TextView EPCInfo; // EPC
	TextView TIDInfo; // TID
	TextView PutEnterpriseInfo; // 发放企业名称
	TextView PutProductName; // 企业名称
	TextView modelInfo; // 规格型号
	TextView anbiaoCode; // 安标code
	private EditText etExceptionInfo;
	private RadioButton rbQualification; // 合格按钮
	private RadioButton rbDisqualification;// 不合格按钮
	private Button btSubmit; // 提交按钮
	private EditText etColliery; //煤矿名称
	private String checkResult = "";
	private GridView gridView; //显示RFID图片用
	private String imageName;
	private ScrollView scrollView; //滚动

	/********************** 跟拍照相关----开始 ***********************/
	// 拍照按钮
	private Button btPhotograph;
	/**
	 * 获取sd卡存储地址
	 */
	private static final File PHOTO_DIR = new File(
			Environment.getExternalStorageDirectory() + "/DCIM/Camera");
	// 图片文件
	private File mCurrentPhotoFile;
	// 当前上下文对象
	private Context context;
	// 新的弹出窗口
	private PopupWindow popupWindow;
	private View popView;
	private Bitmap cameraBitmap;
	// 头像选择
	private ImageView imgPickPhoto;
	public static final int RESULT_BTN_SAVE = 0;
	public static final int REQUEST_BTN_FILE_SELECT = 2;
	private boolean flagBtnImage = false;// 标记是否改变头像
	private UserManager userManager;

	/********************** 跟拍照相关----结束 ***********************/
	// private ScollView displayScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		findViewById();
		initView(); // 这两行代码不能互换位置
		
		//设置ScrollView滚动条不显示
		scrollView.setVerticalScrollBarEnabled(false);
		//获取登录的用户名
		GlobalVariable userGlobalVariable = (GlobalVariable)getApplication();
		userName = userGlobalVariable.getUserName();
		
		//获取当前日期
		currentTime = FormatTools.getDate();
		//初始化合格不合格按钮的相关信息
		rbQualification.setChecked(true); 
		etExceptionInfo.setEnabled(false);
		etExceptionInfo.setHint("无");
		
		// 获取读写器实例，若返回Null,则串口初始化失败
		reader = UhfReader.getInstance();
		if (reader == null) {

			Toast.makeText(this, "射频硬件未能初始化", Toast.LENGTH_SHORT).show();
			return;
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 获取用户设置功率,并设置
		SharedPreferences shared = getSharedPreferences("power", 0);
		int value = shared.getInt("value", 26);
		Log.e("", "value" + value);
		reader.setOutputPower(value);

		// 添加广播，默认屏灭时休眠，屏亮时唤醒
		screenReceiver = new ScreenStateReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(screenReceiver, filter);

		Thread thread = new InventoryThread();
		thread.start();
		// 初始化声音池
		Util.initSoundPool(this);

	}
	
	@Override
	protected void findViewById() {

		buttonStart = (Button) findViewById(R.id.button_start);
		// buttonClear = (Button) findViewById(R.id.submit_id);
		msgInfo = (EditText) findViewById(R.id.msginfo);

		EPCInfo = (TextView) findViewById(R.id.EPCInfo);
		TIDInfo = (TextView) findViewById(R.id.TIDInfo);
		PutEnterpriseInfo = (TextView) findViewById(R.id.PutEnterpriseInfo);
		PutProductName = (TextView) findViewById(R.id.PutProductName);
		modelInfo = (TextView) findViewById(R.id.modelInfo);
		anbiaoCode = (TextView) findViewById(R.id.anbiaoCode);
		btPhotograph = (Button) findViewById(R.id.photograph_id);
		imgPickPhoto = (ImageView) findViewById(R.id.pic_1_id);
		rbDisqualification = (RadioButton) findViewById(R.id.rb_disqualification_id);
		rbQualification = (RadioButton) findViewById(R.id.rb_qualification_id);
		etExceptionInfo = (EditText) findViewById(R.id.msginfo);
		btSubmit = (Button) findViewById(R.id.submit_id);
		etColliery = (EditText)findViewById(R.id.et_colliery_id);
		ivPic = (ImageView)findViewById(R.id.pic_1_id);
		gridView = (GridView)findViewById(R.id.gv_product_pic_id);
		scrollView = (ScrollView)findViewById(R.id.displayView);
	}

	@Override
	protected void initView() {
		buttonStart.setOnClickListener(this);
		// buttonClear.setOnClickListener(this);
		setButtonClickable(buttonStart, true);
		// 拍照按钮，监听从照相机获取头像
		btPhotograph.setOnClickListener(onClickListener1);
		// 提交按钮
		btSubmit.setOnClickListener(this);
		rbDisqualification.setOnClickListener(this);
		rbQualification.setOnClickListener(this);
		
		
	}

	
	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_start:
			
			if (!startFlag) {
				startFlag = true;
				buttonStart.setText(R.string.scan_btn_end);

			} else {
				startFlag = false;
				buttonStart.setText(R.string.scan_btn_str);
			}

			break;
		case R.id.submit_id:
			submitData();
			break;
		case R.id.rb_disqualification_id:
			disqualificate();
			break;
		case R.id.rb_qualification_id:
			qualificate();
			break;
		default:
			break;
		}
	}

	/**
	 * 单击不合格按钮
	 */
	public void disqualificate(){
		etExceptionInfo.setEnabled(true);
		etExceptionInfo.setHint("请输入异常信息");
	}
	/**
	 * 单击合格按钮
	 */
	public void qualificate(){
		etExceptionInfo.setEnabled(false);
		etExceptionInfo.setText("无");
		//etExceptionInfo.setHint("无");
	}
	
	/**
	 * 获取真实姓名
	 * @return
	 */
	public List<User> getRealNameAndId(String userName){
		userManager = new UserManager(this);
		//String realName = "";
		List<User> list = userManager.getUser(userName);
		/*if (list.size() > 0) {
			 realName= list.get(0).getRealName().toString();
		}*/
		
		return list;
	}
	
	
	//TODO:获取图片
	
	// 提交数据
	private boolean submitData() {

		
		if (rbDisqualification.isChecked()) {
			if (etExceptionInfo.getText().toString().trim().equals("")) {
				Toast.makeText(this, "请您填写异常信息", Toast.LENGTH_SHORT).show();
				//etExceptionInfo.setFocusable(true);
				return false;
			}else {
				checkResult = "不合格";
			}
		}else {
			if (etColliery.getText().toString().trim().equals("")) {
				Toast.makeText(this, "请您填写煤矿名称", Toast.LENGTH_SHORT).show();
				//etColliery.setFocusable(true);
				return false;
			} else{
				//获取用户名真实姓名
				listUsers = getRealNameAndId(userName);  
				//抽查结果
				checkResult = "合格";
				
 				new Thread(new Runnable() {

					@Override
					public void run() {
						//looper的两个方法调用解决toast在线程中不能使用的问题
						Looper.prepare();						
						//数据插入
						//获取手机唯一序列编号IMEI
						String deviceId = ((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getDeviceId(); 						
						AppUpManager upManager = new AppUpManager(ScanActivity.this);
						AppUp appUp = new AppUp();
						appUp.setEPRCode(EPCInfo.getText().toString());
						appUp.setTIDCode(TIDInfo.getText().toString());
						appUp.setExceRemark(msgInfo.getText().toString());
						appUp.setAnJianCode(deviceId);
						appUp.setMeKuangiName(etColliery.getText().toString().trim());
						appUp.setJobName(listUsers.get(0).getRealName()); 
						appUp.setUserID(listUsers.get(0).getUserId());
						appUp.setBeginDate(currentTime);
						appUp.setCheckDate(currentTime);
						appUp.setAnBiaoCode(anbiaoCode.getText().toString().trim());
						appUp.setCheckResult(checkResult);
						appUp.setName("");
						appUp.setState("1");
						upManager.insert(appUp);
						
						//****************图片插入--开始***********************
						AppPhotoFileManager photoManager = new AppPhotoFileManager(ScanActivity.this);
						AppPhotoFile photoFile = new AppPhotoFile();
						
						photoFile.setBeginDate(currentTime);
						photoFile.setMainStype(Constants.IMG_SPOTCHECK_TYPE);
						
						//获取拍照后显示到imageView上的图片
						ivPic.setDrawingCacheEnabled(true); 
						Bitmap bm = ivPic.getDrawingCache();
						
						byte[] img =ImageTools.getByteFromBitmap(bm);						
						photoFile.setImage(img);
						ivPic.setDrawingCacheEnabled(false);
						
						//获取图片所对应的上传记录的id
						List<AppUp> list = new ArrayList<AppUp>();
						String condition = EPCInfo.getText().toString();
						list = upManager.getUpRecord(condition);
						int upId = list.get(0).getID();
						photoFile.setMainID(upId);
						
						//图片名称和后缀类型
						photoFile.setFileName(imageName);
						photoFile.setFileType("jpg");
						
						photoManager.Insert(photoFile);
						//**************************图片插入--结束****************
						
						Toast.makeText(ScanActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
						//clearInfo();
						
						Looper.loop();
					}
				}).start();
 				//提交成功后清空显示的信息
 				clearInfo();
				return true;
			}
			
		}		
		return false;
	}

	
	

	/**
	 * 扫描线程
	 * 
	 * @author zhengbo
	 * 
	 */
	class InventoryThread extends Thread {
		private List<byte[]> epcList;
		private String epcStr = "";

		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					buttonStart.setText(R.string.scan_btn_str);
					String info = (String) msg.obj;
					// EPCInfoTextView.setText(info);
					// EPCinfo = info;
					// Log.i("zb", EPCinfo);

					// 控件赋值
					EPCInfo.setText(info);

					// 查找数据库
					DownCongManager downCongManager = new DownCongManager(
							ScanActivity.this);
					appDownCongs = downCongManager
							.getAppDownCongByCondition(info);

					if (appDownCongs == null || appDownCongs.size() == 0) {
						// 提示没有数据
						Toast.makeText(ScanActivity.this, "没有找到该EPC编码的相关信息！",
								Toast.LENGTH_SHORT).show();
						clearInfo();
						return;
					} else {
						TIDInfo.setText(appDownCongs.get(0).getTIDCode());
						PutEnterpriseInfo.setText(appDownCongs.get(0)
								.getPutEnterprise());
						PutProductName.setText(appDownCongs.get(0)
								.getPutProduceName());
						modelInfo.setText(appDownCongs.get(0)
								.getPutProdeceModel());
						anbiaoCode.setText(appDownCongs.get(0).getAnBiaoCode());
						
						
						
						//获取图片并显示
						//1.获取安标信息Id
						AppDownManager downManager = new AppDownManager(ScanActivity.this);
						String id = ""+downManager.getAnbiaoId(appDownCongs.get(0).getAnBiaoCode());
						//2.根据id和类型获取RFID图片
						AppPhotoFileManager photoManager = new AppPhotoFileManager(ScanActivity.this);
						list = photoManager.getPhotoFileByIdAndType(id, Constants.IMG_RFID_TYPE);
						
						//使用gridview显示图片
						gridView.setAdapter(new ImageAdapter(ScanActivity.this));// 调用ImageAdapter.java
						//Gridview单击事件，可以用于放大等效果，现在只是给个提示。
						gridView.setOnItemClickListener(new OnItemClickListener() {// 监听事件
							public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
								Toast.makeText(ScanActivity.this, "" + position,
										Toast.LENGTH_SHORT).show();// 显示信息;
							}
						});
						
					}
				}

			}

		};

		@Override
		public void run() {
			super.run();
			while (runFlag) {
				if (startFlag) {
					// reader.stopInventoryMulti()
					epcList = reader.inventoryRealTime(); // 实时盘存
					if (epcList != null && !epcList.isEmpty()) {
						// 播放提示音
						Util.play(1, 0);
						for (byte[] epc : epcList) {
							epcStr = Tools.Bytes2HexString(epc, epc.length);
							startFlag = false;				
						}
						Message message = new Message();
						message.what = 1;
						message.obj = epcStr;
						handler.sendMessage(message);
					}
					epcList = null;
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// break;
			}
		}
	}

	// 设置按钮是否可用
	private void setButtonClickable(Button button, boolean flag) {
		button.setClickable(flag);
		if (flag) {
			// button.setTextColor(Color.WHITE);
		} else {
			// button.setTextColor(Color.GRAY);
		}
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(screenReceiver);
		runFlag = false;
		if (reader != null) {
			reader.close();
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {

		startFlag = false;
		super.onPause();
	}

	// 头像选择的监听事件
	private OnClickListener onClickListener1 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			// 从照相机获取头像
			case R.id.photograph_id:
				/*
				 * if (popupWindow == null) { popView =
				 * inflater.inflate(R.layout.popup_photo_select, null);
				 * popupWindow = new PopupWindow(popView,
				 * LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); //
				 * 下面三个设置可以使PopupWindow中的项和外面的键都可以响应点击或触摸事件
				 * popupWindow.setFocusable(true);
				 * popupWindow.setTouchable(true);
				 * popupWindow.setBackgroundDrawable(new BitmapDrawable()); }
				 */
				try {
					// Launch camera to take photo for selected contact
					PHOTO_DIR.mkdirs();
					mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE,
							null);
					intent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(mCurrentPhotoFile));
					// 开启一个具有回调性质的Activity
					startActivityForResult(intent, Activity.DEFAULT_KEYS_DIALER);
				} catch (ActivityNotFoundException e) {
					CommonUtil.Toast(context, "not find photo");
				}
				// popupWindow.dismiss();
				break;

			}
		}
	};

	/**
	 * 创建图片名称
	 * 年月日时分秒毫秒+6位随机数
	 * @return
	 */
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		//6位随机数
		int i = (int)((Math.random()*9+1)*100000);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyyMMddhhmmssSSS");
		String picName = dateFormat.format(date)  + i;
		imageName = picName + ".jpg";
		return imageName;
	}

	/***
	 * Constructs an intent for image cropping.
	 */
	public static Intent getCropImageIntent(Uri photoUri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(photoUri, "image/**");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 77);
		intent.putExtra("outputY", 77);
		intent.putExtra("return-data", true);
		return intent;
	}

	/**
	 * Activity回调函数
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Bitmap bitmap = null;
		/*
		 * // 文件返回的 if (requestCode == REQUEST_BTN_FILE_SELECT && resultCode ==
		 * FileSelectActivity.RESULT_FILE_SELECTED) { String imagePath =
		 * data.getStringExtra("path"); File file = new File(imagePath);
		 * CommonUtil.Log("jczb", "file.length()", file.length() + "", 'i'); try
		 * { if (file.length() > 800000)// 压缩后转换为Bitmap bitmap =
		 * ImageTools.saveBefore(imagePath); else bitmap =
		 * ImageTools.getBitemapFromFile(file); CommonUtil.Log("jczb",
		 * "onActivityResult", imagePath, 'i'); // 将图片转换为指定大小的图片,并显示在头像上
		 * imgPickPhoto.setImageBitmap(ImageTools.createBitmapBySize( bitmap,
		 * 77, 77)); flagBtnImage = true; } catch (Exception ex) {
		 * CommonUtil.Toast(context, "图片过大，无法显示"); } }
		 */
		// 照相机返回的
		if (requestCode == Activity.DEFAULT_KEYS_DIALER) {
			try {
				// Add the image to the media store
				Intent intentScan = new Intent(
						Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
				sendBroadcast(intentScan);
				// Launch gallery to crop the photo
				final Intent intent = getCropImageIntent(Uri
						.fromFile(mCurrentPhotoFile));
				startActivityForResult(intent, 3);
			} catch (Exception e) {
				CommonUtil.Log("jczb", "Devdiv", "Cannot crop image", 'i');
				CommonUtil.Toast(context, "not find photo ");
			}
		}
		// 得到的裁剪后的photo的bitmap对象
		if (requestCode == 3)
			if (data != null) {
				cameraBitmap = data.getParcelableExtra("data");
				imgPickPhoto.setImageBitmap(cameraBitmap);
				flagBtnImage = true;
			}
	}
	
	/**
	 * 重写BaseAdapter，
	 * 使用Gridview实现图片的加载
	 * @author wlc
	 * @date 2015-4-15
	 */
	class ImageAdapter extends BaseAdapter {
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			//return mThumbIds.length;
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return list.get(position).getID();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			ImageView imageview;
			if (convertView == null) {
				imageview = new ImageView(mContext);
				imageview.setLayoutParams(new GridView.LayoutParams(140, 140));
				imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageview.setPadding(8, 8, 8, 8);
			} else {
				imageview = (ImageView) convertView;
			}
			final AppPhotoFile appPhotoFile = list.get(position);
			imageview.setImageBitmap(ImageTools.getBitmapFromByte(appPhotoFile.getImage()));
			//imageview.setImageResource(mThumbIds[position]);
			//imageview.setb
			return imageview;
		}	

	}
	/**
	 * 清除控件上的信息
	 */
	public void clearInfo(){
		//清空其他控件的信息
		EPCInfo.setText("");
		TIDInfo.setText("");
		PutEnterpriseInfo.setText("");
		PutProductName.setText("");
		modelInfo.setText("");
		anbiaoCode.setText("");
		etExceptionInfo.setText("");
	}
	
	
}