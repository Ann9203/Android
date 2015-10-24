package com.example.mobilesafe_2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.StreamToString;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SplashActivity extends Activity {

	protected static final int MSG_UPDATE = 6;
	protected static final int MSG_SERVER_ERROR = 1;
	protected static final int MSG_URL_ERROR = 2;
	protected static final int MSG_IO_ERROR = 3;
	protected static final int MSG_JSON_ERROR = 4;
	protected static final int MSG_ENTER_HOME = 5;
	private String code;
	private String apkurl;
	private String des;
	@ViewInject(R.id.tv_splash_version)
	private TextView tv_splash_version;
	@ViewInject(R.id.tv_splash_progress)
	private TextView tv_splash_progress;
	@ViewInject(R.id.sp_splash_sp)
	private ProgressBar pb;
	private SharedPreferences sp;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			
			switch(msg.what){
				case MSG_SERVER_ERROR:
					Toast.makeText(getApplicationContext(), "亲，服务器连接出错啦！！", Toast.LENGTH_SHORT).show();
					enterHome();
					break;
				case MSG_URL_ERROR:
					Toast.makeText(getApplicationContext(), "错误代码号："+MSG_URL_ERROR, Toast.LENGTH_SHORT).show();
					break;
				case MSG_IO_ERROR:
					Toast.makeText(getApplicationContext(), "亲，网络超时啦！！", Toast.LENGTH_SHORT).show();
					enterHome();
					break;
				case MSG_JSON_ERROR:
					Toast.makeText(getApplicationContext(), "错误代码号："+MSG_JSON_ERROR, Toast.LENGTH_SHORT).show();
					break;
				case MSG_ENTER_HOME:
					enterHome();
					break;
				case MSG_UPDATE:
					showUpdateDialog();
					break;
 			
			}
		};
	};
	private Message msg = handler.obtainMessage();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ViewUtils.inject(this);
		tv_splash_version.setText(getVersonName());
		sp=getSharedPreferences("config", MODE_PRIVATE);
		if(sp.getBoolean("update", true)){
			update();
		}else{
			new Thread(){
				public void run() {
					SystemClock.sleep((2000 ));
					//沉睡2秒之后进入主界面
					enterHome();
				};
				
			}.start();
		}
		copyDB();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	/**
	 * 获取当前的版本号
	 * 
	 * @return
	 */
	public String getVersonName() {

		// 首先获取包管理器
		PackageManager packageManager = getPackageManager();
		// 获取包的具体信息

		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					getPackageName(), 0);
			String versonName = packageInfo.versionName;
			return versonName;

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取链接
	 */
	public void update() {
		// 耗时操作在子线程中进行的
		new Thread() {

			public void run() {
				// 获取获取链接之前需要的毫秒当前时间
				long startTime = System.currentTimeMillis();

				// 获取Url
				try {
					URL url = new URL("http://192.168.0.ll4:8090/json.html");
					// 打开链接
					HttpURLConnection openconnection = (HttpURLConnection) url
							.openConnection();
					// 设置超时的时间
					openconnection.setConnectTimeout(5000);
					// 设置发送方式
					openconnection.setRequestMethod("GET");
					// 设置响应码
					int responseCode = openconnection.getResponseCode();
					// 如果连接成功
					if (responseCode == 200) {
						// 获取流对象
						InputStream inputStream = openconnection
								.getInputStream();
						String str = StreamToString.streamToString(inputStream);
						// 将字符串转换成Json然后读取出来
						JSONObject json = new JSONObject(str);
						// 解析json
						code = json.getString("code");
						apkurl = json.getString("apkurl");
						des = json.getString("des");
						msg.what = MSG_UPDATE;

					} else {
						// 如果连接不成功
						msg.what=MSG_SERVER_ERROR;
					}

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what=MSG_URL_ERROR;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what=MSG_IO_ERROR;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what=MSG_JSON_ERROR;
				} finally {
					int endTime = (int) System.currentTimeMillis();
					int dur = (int) (endTime - startTime);
					if (dur < 2000) {
						SystemClock.sleep((2000 - dur));
					}
					// 不管链接是否成功，都是要发送Message
					handler.sendMessage(msg);
				}

			};
		}.start();
	}

	/**
	 * 链接到了数据之后，就要进行update的操作，首先要弹出弹出框，是否需要下载
	 */
	public void showUpdateDialog() {
		AlertDialog.Builder builder = new Builder(this);
		// 设置标题
		builder.setTitle("新软件升级");
		// 设置描述的内容
		builder.setMessage(des);
		// 设置图片
		builder.setIcon(R.drawable.ic_launcher);
		// 设置确定按钮
		builder.setPositiveButton("升级", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// 确定升级就要进行下载并且安装的操作
				download();
				enterHome();
				
			}
		});
		// 取消升级，就要跳转到主界面
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				msg.what=MSG_ENTER_HOME;
				
			}
		});

	}
	public void enterHome(){
		Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
		startActivity(intent);
		finish();
	}

	/**
	 * 使用第三方工具进行下载
	 */
	public void download() {
		
		//先判断 SDcard是否可用的
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			HttpUtils httpUtils = new HttpUtils();
			// 下载
			httpUtils.download(apkurl, "/mnt/sdcard/mobilesafe_2",new RequestCallBack<File>() {
						@Override
						public void onSuccess(ResponseInfo<File> arg0) {
							// TODO Auto-generated method stub
							insert();
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub

						}

						// 正在下载的时候
						@Override
						public void onLoading(long total, long current,
								boolean isUploading) {
							// TODO Auto-generated method stub
							super.onLoading(total, current, isUploading);
							pb.setMax((int) total);
							pb.setProgress((int) current);
							// 下载的进度显示到进度条中
							tv_splash_progress.setVisibility(View.VISIBLE  );
							tv_splash_progress.setText((total * 100 / current)
									+ "%" + "/" + "100%");

						}
					});
		}else{
			Toast.makeText(getApplicationContext(), "sdcard不可用", 0).show();
		}
		
		
	}

	/**
	 * 下载完毕之后要进行安装的
	 */
	public void insert() {
		// 使用系统的安装，用隐式意图
		Intent intent = new Intent();
		// 通过隐式意图
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setDataAndType(
				Uri.fromFile(new File("/mnt/sdcard/mobileSafe_2.0.apk")),
				"application/vnd.android.package-archive");
		startActivityForResult(intent, 10);
	}
	/**
	 * 下载需要重写返回结果
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==10){
			enterHome();
		}
	}
/**
 * 复制数据库
 * 
 */
	public void copyDB(){
		
		new Thread(){
			public void run() {
				File file=new File(getFilesDir(),"address.db");
				if(!file.exists()){
					//如果文件不存在，就开始复制数据库
					FileOutputStream out=null;
					AssetManager ass=getAssets();
					InputStream in =null;
					try {
						in= ass.open("address");
						out=new FileOutputStream(file);
						byte[] bytes=new byte[1024];
						int len=-1;
						while((len=in.read(bytes))!=-1){
							out.write(bytes, 0, len);	
							out.flush();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						//关闭数据库
						IOUtils.closeQuietly(in);
						IOUtils.closeQuietly(out);
					}
				}
			};
		}.start();

	}
	

}
