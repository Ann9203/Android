package com.jczb.checkpoint.ui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jczb.checkpoint.R;
import com.jczb.checkpoint.common.AgentApi;
import com.jczb.checkpoint.common.Constants;
import com.jczb.checkpoint.common.FormatTools;
import com.jczb.checkpoint.common.ImageGetFromHttp;
import com.jczb.checkpoint.common.ImageTools;
import com.jczb.checkpoint.common.ListViewTools;
import com.jczb.checkpoint.db.DBHelper;
import com.jczb.checkpoint.manager.AppAnBiaoRelationManager;
import com.jczb.checkpoint.manager.AppDownCongManager;
import com.jczb.checkpoint.manager.AppDownManager;
import com.jczb.checkpoint.manager.AppPhotoFileManager;
import com.jczb.checkpoint.manager.ScanManager;
import com.jczb.checkpoint.model.AnbiaoScanningImg;
import com.jczb.checkpoint.model.AppAnBiaoRelation;
import com.jczb.checkpoint.model.AppDown;
import com.jczb.checkpoint.model.AppDownCong;
import com.jczb.checkpoint.model.AppDownCongVo;
import com.jczb.checkpoint.model.AppPhotoFile;
import com.jczb.checkpoint.model.AppPhotoFileVo;
import com.jczb.checkpoint.model.DownCertificate;
import com.jczb.checkpoint.model.DownCertificateVo;
import com.jczb.checkpoint.model.DownCertificate_realtion;
import com.jczb.checkpoint.ui.progress.RowItem;

/**
 * 手动扫描下载
 * 
 * @author wlc
 * 
 */
public class ManualScanActivity extends BaseActivity implements OnClickListener {

	private String barcode; // 接受传递来的一维码
	private ProgressDialog dialog; // 用于下载的时候等待数据，期间不能操作界面
	private Button downButton; // 下载按钮
	private ListView listView; // 关联安标id相关ListView
	private ScanManager scanManager; // 业务逻辑类Down表
	private DownCertificate_realtion downCertificate_realtion; // 业务逻辑类Down表的关联表
	private DownCertificate tempCertificate; // 缓存参数Down表
	private AppDown downAnbiaoInfo;
	private AppAnBiaoRelation anBiaoRelation;
	private AppDownCong downRFIDInfo;
	private AppDownCongManager rFIDInfoManager;
	private AppDownManager anBiaoInfoManager;
	private AppAnBiaoRelationManager relationInfoManager;

	private String anbiaoMainCode;
	private String anbiaoViceCode;
	private List<String> anbiaoViceCodeList;
	private AppPhotoFile photoFile;
	private ImageView ivGoback;
	private Intent intent;
	private AppPhotoFile appPhotoFile;
	private AppPhotoFileManager photoFileManager;
	List<AnbiaoScanningImg> anbiaoImgList;
	private String imgNames; // 图片名称，用于RFID图片的下载赋值
	private String imgAnbiao; // 用于安标证书图片的下载

	ProgressDialog progressDialog; // 进度对话框

	// 声明一个Handler对象
	// private Handler handler = null;

	/**
	 * 安标证书编码
	 */
	TextView anbiao_code;
	/**
	 * 发证日期
	 */
	TextView provide_date;
	/**
	 * 有效期
	 */
	TextView term_validity;
	/**
	 * 持证人
	 */
	TextView holder_person;
	/**
	 * 注册地址
	 */
	TextView registered_address;
	/**
	 * 生产单位
	 */
	TextView production_unit_id;
	/**
	 * 生产地址
	 */
	TextView production_address;
	/**
	 * 产品名称
	 */
	TextView product_name;
	/**
	 * 规格型号
	 */
	TextView product_model;
	/**
	 * 标准和要求
	 */
	TextView standard;
	/**
	 * 适用范围
	 */
	TextView scope_application;
	/**
	 * 备注
	 */
	TextView remark;
	/**
	 * 状态
	 */
	TextView state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manual_scan);
		findViewById();
		initView();

		Intent intent = getIntent();
		barcode = intent.getStringExtra("barcode"); // 使用intent接收参数
		downCertificate_realtion = new DownCertificate_realtion(); // Down表的关联表

		// 接收网络数据,mhandler为继承自父类
		// 处理证书信息
		mHandler = new Handler() {
			public void handleMessage(Message msg) {

				if (msg.what == 1) {
					if (msg.obj != null) {
						DownCertificateVo downCertificate = (DownCertificateVo) msg.obj;
						// StudentVo student = (StudentVo)msg.obj;
						// 接收数据
						if (downCertificate.getResult().equals("true")) {
							// 控件赋值
							anbiao_code.setText(downCertificate
									.getDownCertificate().get(0)
									.getAnbiao_code());
							provide_date.setText(downCertificate
									.getDownCertificate().get(0)
									.getProvide_date());
							term_validity.setText(downCertificate
									.getDownCertificate().get(0)
									.getTerm_validity()
									+ "-"
									+ downCertificate.getDownCertificate()
											.get(0).getTerm_validity_end());
							holder_person.setText(downCertificate
									.getDownCertificate().get(0)
									.getHolder_person());
							registered_address.setText(downCertificate
									.getDownCertificate().get(0)
									.getRegistered_address());
							production_unit_id.setText(downCertificate
									.getDownCertificate().get(0)
									.getProduction_unit_id());
							production_address.setText(downCertificate
									.getDownCertificate().get(0)
									.getProduction_address());
							product_name.setText(downCertificate
									.getDownCertificate().get(0)
									.getProduct_name());
							product_model.setText(downCertificate
									.getDownCertificate().get(0)
									.getProduct_model());
							standard.setText(downCertificate
									.getDownCertificate().get(0).getStandard());
							scope_application.setText(downCertificate
									.getDownCertificate().get(0)
									.getScope_application());
							remark.setText(downCertificate.getDownCertificate()
									.get(0).getRemark());
							state.setText(downCertificate.getDownCertificate()
									.get(0).getState());

							// 传递参数,用于写入数据库
							tempCertificate = downCertificate
									.getDownCertificate().get(0);
							List<DownCertificate_realtion> list = tempCertificate
									.getDownCertificate_realtion();
							// 安标证书扫描件
							anbiaoImgList = tempCertificate.getDownScanning();

							// 参数赋值,用于安标关联表信息的添加
							// 获取多个关联安标证书的编码
							anbiaoViceCodeList = new ArrayList<String>();
							for (int i = 0; i < list.size(); i++) {
								anbiaoViceCodeList.add(list.get(i)
										.getViceAnBiaoCode());
							}

							// 使用List套HashMapz和SimpleAdapter配合
							// 下面的参数一定要正确匹配。。
							List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
							for (int i = 0; i < list.size(); i++) {
								Map<String, Object> listems = new HashMap<String, Object>();
								listems.put("SeriesId", i + 1);
								listems.put("ViceCode", list.get(i)
										.getViceAnBiaoCode());
								data.add(listems);
							}
							// 使用simpleAdapter为ListView添加数据
							SimpleAdapter AnbiaoCodeAdapter = new SimpleAdapter(
									ManualScanActivity.this, data,
									R.layout.activity_manual_scan_list_item,
									new String[] { "SeriesId", "ViceCode" },
									new int[] { R.id.anbiao_relation_name_id,
											R.id.anbiao_relation_code_id });
							listView.setAdapter(AnbiaoCodeAdapter);
							// 根据item高度自动计算listview的整体高度
							ListViewTools
									.setListViewHeightBasedOnChildren(listView);
						}
					}

				}
			}

		};

		new Thread() {
			public void run() {
				Message msg = new Message();
				try {

					Map<String, String> parmas = new HashMap<String, String>();
					parmas.put("barcode", barcode);// 传递一维码
					String result = AgentApi.dopost(parmas,
							Constants.DOWNLOAD_ANBIAO_URL);
					DownCertificateVo downCertificateVo = JSON.parseObject(
							result, DownCertificateVo.class);
					// StudentVo student = JSON.parseObject(result,
					// StudentVo.class);
					msg.what = 1;
					msg.obj = downCertificateVo;
				} catch (Exception e) {
					e.printStackTrace();
					msg.what = -1;
					msg.obj = e;
				}
				mHandler.sendMessage(msg);
			}
		}.start();
	}

	/**
	 * 1.插入安标证书信息到本地数据库 2.插入关联安标证书信息 3.访问服务器端并解析 4.插入RFID相关信息5.插入图片表中相关信息等
	 */
	private void downLoadRFID() {

		DownloadAnbiaoImgTask task = new DownloadAnbiaoImgTask(this);
		task.execute(new String[] {  });

		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("In progress...");// 设置Title
		progressDialog.setMessage("Loading...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setIndeterminate(false);
		progressDialog.setMax(100);
		progressDialog.setCancelable(true);
		progressDialog.show();
		// 参数中不能直接写成this。。。
		/*rFIDInfoManager = new AppDownCongManager(ManualScanActivity.this);
		downRFIDInfo = new AppDownCong();*/
		
		
//		// 开辟一个等待
//		mHandler = new Handler() {
//			public void handleMessage(Message msg) {
//
//				if (msg.what == 1) {
//					if (msg.obj != null) {
//						AppDownCongVo appDownCongVo = (AppDownCongVo) msg.obj;
//						if (appDownCongVo.getResult().equals("true")) {
//
//							// 1.添加安标证书信息
//							insertAnbiaoInfo();
//
//							// 2.添加关联安标证书信息
//							insertAnbiaoRelation();
//
//							// 图片表中插入安标扫描件
//							insertAnbiaoImgs();
//
//							// 3.添加RFID信息
//							// 获取并解析数据之后插入到相关表中
//							// 1.插入RFID表中，遍历解析回来的从表信息插入到本地
//							List<AppDownCong> list = new ArrayList<AppDownCong>();
//							list = appDownCongVo.getAppDownCong();
//							for (int i = 0; i < list.size(); i++) {
//								downRFIDInfo.setAnBiaoCode(list.get(i)
//										.getAnBiaoCode());
//								downRFIDInfo.setBeginDate(list.get(i)
//										.getBeginDate());
//								downRFIDInfo.setEPCCode(list.get(i)
//										.getEPCCode());
//								downRFIDInfo.setName(list.get(i).getName());
//								downRFIDInfo.setPutDate(list.get(i)
//										.getPutDate());
//								downRFIDInfo.setPutEnterprise(list.get(i)
//										.getPutEnterprise());
//								downRFIDInfo.setPutProdeceModel(list.get(i)
//										.getPutProdeceModel());
//								downRFIDInfo.setPutProduceName(list.get(i)
//										.getPutProduceName());
//								downRFIDInfo.setState(list.get(i).getState());
//								downRFIDInfo.setTIDCode(list.get(i)
//										.getTIDCode());
//								rFIDInfoManager.insert(downRFIDInfo);
//							}
//
//							// 在一个handler中再开辟一个。。。
//							mHandler = new Handler() {
//								public void handleMessage(Message msg) {
//									if (msg.what == 2) {
//										if (msg.obj != null) {
//											// RFID图片的Json格式单独解析
//											AppPhotoFileVo photoFileVo = (AppPhotoFileVo) msg.obj;
//											if (photoFileVo.getResult().equals(
//													"true")) {
//												List<AppPhotoFile> list = new ArrayList<AppPhotoFile>();
//												list = photoFileVo
//														.getDownPhoto();
//												
//												photoFile = new AppPhotoFile();
//												photoFileManager = new AppPhotoFileManager(
//														ManualScanActivity.this);
//												
//												for (int j = 0; j < list.size(); j++) {
//													photoFile.setID(list.get(j)
//															.getID());
//													photoFile.setBeginDate(list
//															.get(j)
//															.getBeginDate());
//													photoFile.setName(list.get(
//															j).getName());
//													photoFile
//															.setMainID(list
//																	.get(j)
//																	.getMainID());
//													// 类型直接添加
//													photoFile
//															.setMainStype(Constants.IMG_RFID_TYPE);
//													photoFile.setFileName(list
//															.get(j)
//															.getFileName());
//													photoFile.setFileType(list
//															.get(j)
//															.getFileType());
//
//													// 转换方法,用于图片路径的组合
//													imgNames = list.get(j)
//															.getFileName();
//													// 从网络获取图片然后以byte[]形式存储到本地数据库中
//													mHandler = new Handler() {
//														public void handleMessage(
//																Message msg) {
//															if (msg.what == 3
//																	&& msg.obj != null) {
//																byte[] img = (byte[]) msg.obj;
//																Bitmap bm = ImageTools
//																		.getBitmapFromByte(img);
//																photoFile
//																		.setImage(img);
//																// 将图片名称和后缀插入到本地数据库中
//																photoFileManager
//																		.Insert(photoFile);
//																ivGoback.setImageBitmap(bm);
//															}
//
//														}
//													};
//													new Thread() {
//														public void run() {
//															Message msg = new Message();
//															try {
//																// 图片路径
//																String path = Constants.CONVERT_IMG_URL
//																		+ imgNames;
//																Bitmap bm = ImageGetFromHttp
//																		.downloadBitmap(path);
//																byte[] byteImg = ImageTools
//																		.getByteFromBitmap(bm);
//
//																msg.what = 3;
//																msg.obj = byteImg;
//															} catch (Exception e) {
//																e.printStackTrace();
//																msg.what = -1;
//																msg.obj = e;
//															}
//															mHandler.sendMessage(msg);
//														}
//													}.start();
//
//												}
//
//											}
//										}
//									}
//								}
//							};
//
//							// 4.添加RFID图片信息
//							// insertPhotoFile();
//							// 上面的线程执行完毕之后再执行这个解析图片的线程
//							new Thread() {
//								public void run() {
//									Message msg = new Message();
//									try {
//
//										Map<String, String> params = new HashMap<String, String>();
//										params.put("barcode", anbiao_code
//												.getText().toString());
//										String result = AgentApi.dopost(params,
//												Constants.DOWNLOAD_IMG_URL);
//										AppPhotoFileVo photoFileVo = JSON
//												.parseObject(result,
//														AppPhotoFileVo.class);
//										msg.what = 2;
//										msg.obj = photoFileVo;
//									} catch (Exception e) {
//										e.printStackTrace();
//										msg.what = -1;
//										msg.obj = e;
//									}
//									mHandler.sendMessage(msg);
//								}
//							}.start();
//
//						}
//					}
//				}
//			};
//
//		};
//		// 开启一个新线程
//		new Thread() {
//			public void run() {
//				Message msg = new Message();
//				try {
//
//					Map<String, String> params = new HashMap<String, String>();
//					params.put("barcode", anbiao_code.getText().toString());
//					String result = AgentApi.dopost(params,
//							Constants.DOWNLOAD_RFID_URL);
//					AppDownCongVo appDownCongVo = JSON.parseObject(result,
//							AppDownCongVo.class);
//					msg.what = 1;
//					msg.obj = appDownCongVo;
//				} catch (Exception e) {
//
//					e.printStackTrace();
//					msg.what = -1;
//					msg.obj = e;
//				}
//				mHandler.sendMessage(msg);
//			}
//		}.start();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.RFID_download_btn_id: // 下载按钮
			// 数据库写入操作
			downLoadRFID();
			// downLoadInfo();
			break;
		case R.id.manual_scan_goback_img_id:
			intent = new Intent(ManualScanActivity.this,
					QueryCertificateActivity.class);
			startActivity(intent);
			finish();
		default:
			break;
		}
	}

	@Override
	protected void findViewById() {
		anbiao_code = (TextView) findViewById(R.id.anbiao_code);
		provide_date = (TextView) findViewById(R.id.provide_date);
		term_validity = (TextView) findViewById(R.id.term_validity);
		holder_person = (TextView) findViewById(R.id.holder_person);
		registered_address = (TextView) findViewById(R.id.registered_address);
		production_unit_id = (TextView) findViewById(R.id.production_unit_id);
		production_address = (TextView) findViewById(R.id.production_address);
		product_name = (TextView) findViewById(R.id.product_name_str);
		product_model = (TextView) findViewById(R.id.product_model);
		standard = (TextView) findViewById(R.id.standard);
		scope_application = (TextView) findViewById(R.id.scope_application);
		remark = (TextView) findViewById(R.id.remark);
		state = (TextView) findViewById(R.id.state);
		downButton = (Button) findViewById(R.id.RFID_download_btn_id);
		listView = (ListView) findViewById(R.id.scan_anbiao_relation_list_id);
		ivGoback = (ImageView) findViewById(R.id.manual_scan_goback_img_id);
	}

	@Override
	protected void initView() {
		downButton.setOnClickListener(this);
		ivGoback.setOnClickListener(this);
	}

	/**
	 * 添加安标信息
	 */
	public void insertAnbiaoInfo() {
		downAnbiaoInfo = new AppDown();
		String begin_date = FormatTools.getDate();
		downAnbiaoInfo.setAnbiao_code(anbiao_code.getText().toString());
		downAnbiaoInfo.setBegin_date(begin_date);
		downAnbiaoInfo.setHolder_person(holder_person.getText().toString());
		downAnbiaoInfo.setProvide_date(provide_date.getText().toString());
		downAnbiaoInfo.setTerm_validity(term_validity.getText().toString());
		downAnbiaoInfo.setRegistered_address(registered_address.getText()
				.toString());
		downAnbiaoInfo.setProduction_address(production_address.getText()
				.toString());
		downAnbiaoInfo.setProduction_unit_id(production_unit_id.getText()
				.toString());
		downAnbiaoInfo.setProduct_name(product_name.getText().toString());
		downAnbiaoInfo.setProduct_model(product_model.getText().toString());
		downAnbiaoInfo.setStandard(standard.getText().toString());
		downAnbiaoInfo.setScope_application(scope_application.getText()
				.toString());
		downAnbiaoInfo.setRemark(remark.getText().toString());
		downAnbiaoInfo.setState(state.getText().toString());

		anBiaoInfoManager = new AppDownManager(this);
		anBiaoInfoManager.insert(downAnbiaoInfo);

	}

	/**
	 * 添加关联安标信息
	 */
	public void insertAnbiaoRelation() {
		anBiaoRelation = new AppAnBiaoRelation();
		relationInfoManager = new AppAnBiaoRelationManager(this);
		String anbiaoMainCode = anbiao_code.getText().toString();
		int anbiaoId = 0;
		anBiaoInfoManager = new AppDownManager(this);
		// 查询出在安标信息表中存的主安标Id，然后插入到安标关联表中，从安标Id因为还没有，只能插入Code
		anbiaoId = anBiaoInfoManager.getAnbiaoId(anbiaoMainCode);

		// 表中存放数据的时候是一个主安标Id对应1个关联安标Code,所以对于多条关联安标Code的话就得插入多条记录了
		for (int i = 0; i < anbiaoViceCodeList.size(); i++) {
			anBiaoRelation.setMainAnBiaoID(anbiaoId);
			anBiaoRelation.setViceAnBiaoID(anbiaoViceCodeList.get(i));
			relationInfoManager.insert(anBiaoRelation);
		}

	}

	/**
	 * 添加安标证书扫描件图片
	 */
	public void insertAnbiaoImgs() {

		appPhotoFile = new AppPhotoFile();
		photoFileManager = new AppPhotoFileManager(this);
		for (int i = 0; i < anbiaoImgList.size(); i++) {
			appPhotoFile.setFileName(anbiaoImgList.get(i).getFileName());
			appPhotoFile.setFileType(anbiaoImgList.get(i).getFileType());
			// 将安标类型直接添加到数据库中
			appPhotoFile.setMainStype(Constants.IMG_ANBIAO_TYPE);
			// 转换方法,用于图片路径的组合
			imgAnbiao = anbiaoImgList.get(i).getFileName();
			String path = Constants.CONVERT_IMG_URL + imgAnbiao;
			Bitmap bm = ImageGetFromHttp.downloadBitmap(path);
			byte[] byteImg = ImageTools.getByteFromBitmap(bm);
			
			appPhotoFile.setImage(byteImg);
			// 将图片名称和后缀插入到本地数据库中
			photoFileManager.Insert(appPhotoFile);
//			// 从网络获取图片然后以byte[]形式存储到本地数据库中
//			mHandler = new Handler() {
//				public void handleMessage(Message msg) {
//					if (msg.what == 4 && msg.obj != null) {
//						byte[] img = (byte[]) msg.obj;
//						// Bitmap bm= ImageTools.getBitmapFromByte(img);
//
//						appPhotoFile.setImage(img);
//						// 将图片名称和后缀插入到本地数据库中
//						photoFileManager.Insert(appPhotoFile);
//					}
//
//				}
//			};
//			// 根据传入的图片名称结合为一个完整的图片路径，然后解析为byte[]形式
//			// 该过程必须写到子线程中，不能在主线程中
//			new Thread() {
//				public void run() {
//					Message msg = new Message();
//					try {
//						// 图片路径
//						String path = Constants.CONVERT_IMG_URL + imgAnbiao;
//						Bitmap bm = ImageGetFromHttp.downloadBitmap(path);
//						byte[] byteImg = ImageTools.getByteFromBitmap(bm);
//
//						msg.what = 4;
//						msg.obj = byteImg;
//					} catch (Exception e) {
//						e.printStackTrace();
//						msg.what = -1;
//						msg.obj = e;
//					}
//					mHandler.sendMessage(msg);
//				}
//			}.start();
		}

	}

	/**
	 * 添加RFID产品图片
	 */
	public void insertPhotoFile() {

		/*
		 * // Base64 编码： byte [] encode =
		 * Base64.encode("Hello, World".getBytes(), Base64.DEFAULT);
		 * 
		 * String enc = new String(encode);
		 * 
		 * Log.d("","base 64 encode = " + enc);
		 * 
		 * // Base64 解码： byte [] result = Base64.decode("SGVsbG8sIFdvcmxk",
		 * Base64.DEFAULT);
		 * 
		 * String res = new String(result);
		 * 
		 * Log.d("", "base 64 result = " + res);
		 */
	}

	private class DownloadAnbiaoImgTask extends
			AsyncTask<String, Integer, List<RowItem>> {

		private Activity context;
		List<RowItem> rowItems;
		int taskCount;

		public DownloadAnbiaoImgTask(Activity context) {
			this.context = context;
		}

		/**
		 * 通过url地址下载
		 */
		@Override
		protected List<RowItem> doInBackground(String... urls) {
			taskCount = urls.length;
			rowItems = new ArrayList<RowItem>();			
			
			// 1.添加安标证书信息
			insertAnbiaoInfo();

			// 2.添加关联安标证书信息
			insertAnbiaoRelation();

			// 3.图片表中插入安标扫描件
			insertAnbiaoImgs();
			// 4.从服务器取得RFID信息,并且保存RFID信息
			Map<String, String> params = new HashMap<String, String>();
			params.put("barcode", anbiao_code.getText().toString());
			String result = AgentApi.dopost(params,
					Constants.DOWNLOAD_RFID_URL);
			AppDownCongVo appDownCongVo = JSON.parseObject(result,
					AppDownCongVo.class);
			// 3.添加RFID信息
			// 获取并解析数据之后插入到相关表中
			// 1.插入RFID表中，遍历解析回来的从表信息插入到本地
			List<AppDownCong> list = appDownCongVo.getAppDownCong();
			taskCount=list.size();
			
			
			for (int i = 0; i < list.size(); i++) {
				downRFIDInfo.setAnBiaoCode(list.get(i)
						.getAnBiaoCode());
				downRFIDInfo.setBeginDate(list.get(i)
						.getBeginDate());
				downRFIDInfo.setEPCCode(list.get(i)
						.getEPCCode());
				downRFIDInfo.setName(list.get(i).getName());
				downRFIDInfo.setPutDate(list.get(i)
						.getPutDate());
				downRFIDInfo.setPutEnterprise(list.get(i)
						.getPutEnterprise());
				downRFIDInfo.setPutProdeceModel(list.get(i)
						.getPutProdeceModel());
				downRFIDInfo.setPutProduceName(list.get(i)
						.getPutProduceName());
				downRFIDInfo.setState(list.get(i).getState());
				downRFIDInfo.setTIDCode(list.get(i)
						.getTIDCode());
				rFIDInfoManager.insert(downRFIDInfo);
			}
			
			
			// 5.根据条码标号从服务器取得RFID 图片信息
			params = new HashMap<String, String>();
			params.put("barcode", anbiao_code
					.getText().toString());
			result = AgentApi.dopost(params,
					Constants.DOWNLOAD_IMG_URL);
			AppPhotoFileVo photoFileVo = JSON
					.parseObject(result,
							AppPhotoFileVo.class);
			
			if (photoFileVo.getResult().equals(
					"true")) {
				
				 List<AppPhotoFile> photoFileslist = photoFileVo
						.getDownPhoto();
				
				photoFile = new AppPhotoFile();
				photoFileManager = new AppPhotoFileManager(
						ManualScanActivity.this);
				Bitmap map = null;
				for (int j = 0; j < photoFileslist.size(); j++) {
					photoFile.setID(photoFileslist.get(j)
							.getID());
					photoFile.setBeginDate(photoFileslist
							.get(j)
							.getBeginDate());
					photoFile.setName(photoFileslist.get(
							j).getName());
					photoFile
							.setMainID(photoFileslist
									.get(j)
									.getMainID());
					// 类型直接添加
					photoFile
							.setMainStype(Constants.IMG_RFID_TYPE);
					photoFile.setFileName(photoFileslist
							.get(j)
							.getFileName());
					photoFile.setFileType(photoFileslist
							.get(j)
							.getFileType());

					// 转换方法,用于图片路径的组合
					imgNames = photoFileslist.get(j)
							.getFileName();
					String path = Constants.CONVERT_IMG_URL
							+ imgNames;
					map = downloadImage(path);
					byte[] byteImg = ImageTools
							.getByteFromBitmap(map);
					photoFile.setImage(byteImg);
					
					// 将图片名称和后缀插入到本地数据库中
					photoFileManager.Insert(photoFile);
					rowItems.add(new RowItem(map));
					
				}
				
			}

			return rowItems;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			Log.i("progress", progress[0].toString());
			progressDialog.setProgress(progress[0]);
			if (rowItems != null) {
				progressDialog.setMessage("Loading " + (rowItems.size() + 1)
						+ "/" + taskCount);
			}
		}

		@Override
		protected void onPostExecute(List<RowItem> rowItems) {
			// listViewAdapter = new CustomListViewAdapter(context, rowItems);
			// listView.setAdapter(listViewAdapter);
			progressDialog.dismiss();
		}

		/**
		 * 下载Image
		 * 
		 * @param urlString
		 * @return
		 */
		private Bitmap downloadImage(String urlString) {
			int count = 0;
			Bitmap bitmap = null;

			URL url;
			InputStream in = null;
			BufferedOutputStream out = null;

			try {

				// 获取url地址中的内容长度
				url = new URL(urlString);
				URLConnection conn = url.openConnection();
				
				//防止getContentLength返回值为-1
				conn.setRequestProperty("Accept-Encoding", "identity");
				int lengthOfFile = conn.getContentLength();

				// 读取数据到输出流中
				in = new BufferedInputStream(url.openStream());
				ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
				out = new BufferedOutputStream(dataStream);
				Log.i("lengthOfFile", lengthOfFile + "");
				byte[] data = new byte[512];
				long total = 0L;

				// 从输出流中读取数据，直到数据返回值为-1（即没有数据了）
				while ((count = in.read(data)) != -1) {
					total += count;
					int sample = (int) ((total * 100) / lengthOfFile);
					Log.i("count", count + "");
					Log.i("progress1", sample + "");
					publishProgress((int) ((total * 100) / lengthOfFile));
					out.write(data, 0, count);
				}

				out.flush();
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 1;

				byte[] bytes = dataStream.toByteArray();
				bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return bitmap;
		}

	}

}
