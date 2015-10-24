package com.itheima.mobileSafe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.Utils.StreamToString;
import com.itheima.server.CallLocationServer;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.IOUtils;

public class SplashActivity extends Activity {

	protected static final int MSG_UPDATE_VERSION = 1;
	protected static final int MSG_JSON_EXC = 4;
	protected static final int MSG_URL_EXC = 5;
	protected static final int MSG_IO_EXC = 6;
	protected static final int MSG_SERVER_ERROR = 3;
	private static final int MSG_ENTER_HOME = 2;
	private String codes;
	private String apkurl;
	private String des;
	private TextView tv_splash_version;
	private TextView tv_splash_progress;
	private SharedPreferences sp;

	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_UPDATE_VERSION:
				
					showDialog();

				break;
			case MSG_ENTER_HOME:
				
				enterHome();
				break;
			case MSG_SERVER_ERROR:
				Toast.makeText(getApplicationContext(), "服务器连接失败", 0).show();
				break;
			case MSG_JSON_EXC:
				Toast.makeText(getApplicationContext(), "错误代码号：0x00"+MSG_JSON_EXC, 0).show();
				break;
			case MSG_IO_EXC:
				Toast.makeText(getApplicationContext(), "亲，网络连接异常。。。。", 0).show();
				enterHome();
				break;
			case MSG_URL_EXC:
				Toast.makeText(getApplicationContext(), "错误代码号：0x00"+MSG_URL_EXC, 0).show();
				break;
			}
		};
	};
	Message msg = handler.obtainMessage();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		 tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_splash_version.setText("版本号：" + getVersionName());
		tv_splash_progress=(TextView)findViewById(R.id.tv_splash_progress);
		sp=getSharedPreferences("config", MODE_PRIVATE);
		if(sp.getBoolean("update", true))
		{
			update();
		}else{
			new Thread(){
				public void run() {
					SystemClock.sleep(2000);
					enterHome();
				};
				
			}.start();
			
		}
		//拷贝数据库
		copyDB("address.db");
		copyDB("antivirus.db");
		shortCut();
		
	}
	/**
	 * 创建桌面快捷方式
	 */
	private void shortCut(){
		if(sp.getBoolean("firstshortcut", true)){
			//给桌面发送一个广播
			Intent intent=new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
			//设置舒心个，设置快捷方式名称
			intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "手机卫士_coffee");
			//设置快捷方式的图标
			Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
			intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);
			//设置快捷方式的操作
			
			Intent intent2=new Intent();
			intent2.setAction("com.itheima.mobileSafe.home");
			intent2.addCategory("android.intent.category.DEFAULT");
			intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent2);
			sendBroadcast(intent);
			//保存已经创建的快捷方式
			Editor editor=sp.edit();
			editor.putBoolean("firstshortcut", false);
			editor.commit();
		}
		
	}

	/**
	 * 获取版本号
	 * 
	 * @return
	 */
	private String getVersionName() {
		PackageManager packageManager = getPackageManager();
		String versionName = null;
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					getPackageName(), 0);
			versionName = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return versionName;

	}

	/**
	 * 获取下载的链接
	 */

	private void update() {
		new Thread() {
			public void run() {

				int startTime=(int) System.currentTimeMillis();
				try {
					// 获取url
					URL url = new URL("http://192.168.20.24:8090/json.html");
					// 打开链接
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					// 设置状态码
					conn.setConnectTimeout(5000);
					// 设置连接方式
					conn.setRequestMethod("GET");
					// 获取状态码
					int code = conn.getResponseCode();
					if (code == 200) {
						// 如果状态码获取成功的话，就获取流信息
						InputStream in = conn.getInputStream();
						// 将返回的流转换成字符串
						String result = StreamToString.streamToString(in);
						System.out.println(result);

						// 将字符串转换成JSON
						JSONObject json = new JSONObject(result);
						// 解析Json
						codes = json.getString("code");
						apkurl = json.getString("apkurl");
						des = json.getString("des");
						// 显示对话框
						msg.what = MSG_UPDATE_VERSION;

						// System.out.println(codes+apkurl+des);

					} else {
						msg.what=MSG_SERVER_ERROR;

					}

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what=MSG_URL_EXC;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what=MSG_IO_EXC;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what=MSG_JSON_EXC;
				} finally {
					//这是获取链接之后的时间
					
					int endTime=(int)System.currentTimeMillis();
					//用连接成功的最后的时间减去最开始的时间就是连接网络的时间
					int time=endTime-startTime;
					//如果网络连接小宇2000的话就要进行睡眠
					if(time<2000)
					{
						SystemClock.sleep(2000-time);
					}
					handler.sendMessage(msg);
				}

			}

		}.start();

	}

	/**
	 * 显示对话框
	 */
	protected void showDialog() {
		if (!getVersionName().equals(codes)) {
				AlertDialog.Builder builder = new Builder(this);
				// 设置对话框不可以消失
				builder.setCancelable(false);
				// 设置对话框的图标
				builder.setIcon(R.drawable.ic_launcher);
				// 设置对话框的标题
				builder.setTitle("新版本：" + codes);
				// 设置对话框的内容
				builder.setMessage(des);
				// 设置升级按钮
				builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {
		
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						downLoad();
					}
				});
				// 设置取消按钮
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
		
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						enterHome();
		
					}
				});
		
				builder.show();
		}else{
			msg.what=MSG_ENTER_HOME;
		}

	}

	/**
	 * 跳转到主页面
	 */
	protected void enterHome() {
		// 显示跳转到其他的将诶面
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		// 欢迎界面消失
		finish();
	}

	/**
	 * 下载程序
	 * 引入第三方框架XUtils
	 */
	protected void downLoad() {
		HttpUtils utils=new HttpUtils();
		//判断Sd卡是否是挂载的状态
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//如果sd卡是可以使用的
			//就要进行下载
			utils.download(apkurl, "/mnt/sdcard/mobileSafe_2.0.apk",new RequestCallBack<File>( ) {
				
				@Override
				public void onSuccess(ResponseInfo<File> arg0) {
					// TODO Auto-generated method stub
					installAPK();
				}
				
				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}
				/**
				 * 正在加载的时候，记录一下进度
				 * 总的进度，当前进度
				 * 然后将得到的值赋给文本框
				 */
				
				public void onLoading(long total, long current, boolean isUploading) {
					tv_splash_progress.setVisibility(View.VISIBLE);
					tv_splash_progress.setText(current*100/total+"/"+"100%");
				};
			} );
		}else{
			Toast.makeText(getApplicationContext(), "sdcard不可用", 0).show();
		}
	}
	/**
	 * 下载完毕就开始安装吧
	 * 
	 */
	protected void installAPK()
	{
		Intent intent=new Intent();
		//通过隐式意图
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setDataAndType(Uri.fromFile(new File("/mnt/sdcard/mobileSafe_2.0.apk")),"application/vnd.android.package-archive" );
		startActivityForResult(intent, 10);
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==10)
		{
			enterHome();
		}
		
	}
	/**
	 * 拷贝数据库，将数据库拷贝到本地的目录中
	 */
	public void  copyDB(final String name){
		//拷贝数据库是耗时操作，所以应该存放在子线程中
		new Thread(){
			public void run() {
				//创建目录
				File file2=new File(getFilesDir(),name);
				//判断目录是否存在
				if(!file2.exists()){
					//如果目录不存在的话，就拷贝数据库
					//获取资产的管理者
					AssetManager ass=getAssets();
					//创建要拷贝到的目的地
					FileOutputStream fileoutput = null;
					InputStream in = null ;
					try {
						//获取数据的输入流
						 in = ass.open(name);
						fileoutput=new FileOutputStream(file2);
						byte[] bytes=new byte[1024];
						int len=-1;
						while((len=in.read(bytes))!=-1){
							fileoutput.write(bytes, 0, len);
							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
//						in.close();
//						fileoutput.close();
						IOUtils.closeQuietly(in);
						IOUtils.closeQuietly(fileoutput);
					}
					
				}
			};
		}.start();

		
	}

}
