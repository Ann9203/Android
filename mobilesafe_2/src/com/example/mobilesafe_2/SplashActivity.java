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
					Toast.makeText(getApplicationContext(), "�ף����������ӳ���������", Toast.LENGTH_SHORT).show();
					enterHome();
					break;
				case MSG_URL_ERROR:
					Toast.makeText(getApplicationContext(), "�������ţ�"+MSG_URL_ERROR, Toast.LENGTH_SHORT).show();
					break;
				case MSG_IO_ERROR:
					Toast.makeText(getApplicationContext(), "�ף����糬ʱ������", Toast.LENGTH_SHORT).show();
					enterHome();
					break;
				case MSG_JSON_ERROR:
					Toast.makeText(getApplicationContext(), "�������ţ�"+MSG_JSON_ERROR, Toast.LENGTH_SHORT).show();
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
					//��˯2��֮�����������
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
	 * ��ȡ��ǰ�İ汾��
	 * 
	 * @return
	 */
	public String getVersonName() {

		// ���Ȼ�ȡ��������
		PackageManager packageManager = getPackageManager();
		// ��ȡ���ľ�����Ϣ

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
	 * ��ȡ����
	 */
	public void update() {
		// ��ʱ���������߳��н��е�
		new Thread() {

			public void run() {
				// ��ȡ��ȡ����֮ǰ��Ҫ�ĺ��뵱ǰʱ��
				long startTime = System.currentTimeMillis();

				// ��ȡUrl
				try {
					URL url = new URL("http://192.168.0.ll4:8090/json.html");
					// ������
					HttpURLConnection openconnection = (HttpURLConnection) url
							.openConnection();
					// ���ó�ʱ��ʱ��
					openconnection.setConnectTimeout(5000);
					// ���÷��ͷ�ʽ
					openconnection.setRequestMethod("GET");
					// ������Ӧ��
					int responseCode = openconnection.getResponseCode();
					// ������ӳɹ�
					if (responseCode == 200) {
						// ��ȡ������
						InputStream inputStream = openconnection
								.getInputStream();
						String str = StreamToString.streamToString(inputStream);
						// ���ַ���ת����JsonȻ���ȡ����
						JSONObject json = new JSONObject(str);
						// ����json
						code = json.getString("code");
						apkurl = json.getString("apkurl");
						des = json.getString("des");
						msg.what = MSG_UPDATE;

					} else {
						// ������Ӳ��ɹ�
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
					// ���������Ƿ�ɹ�������Ҫ����Message
					handler.sendMessage(msg);
				}

			};
		}.start();
	}

	/**
	 * ���ӵ�������֮�󣬾�Ҫ����update�Ĳ���������Ҫ�����������Ƿ���Ҫ����
	 */
	public void showUpdateDialog() {
		AlertDialog.Builder builder = new Builder(this);
		// ���ñ���
		builder.setTitle("���������");
		// ��������������
		builder.setMessage(des);
		// ����ͼƬ
		builder.setIcon(R.drawable.ic_launcher);
		// ����ȷ����ť
		builder.setPositiveButton("����", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// ȷ��������Ҫ�������ز��Ұ�װ�Ĳ���
				download();
				enterHome();
				
			}
		});
		// ȡ����������Ҫ��ת��������
		builder.setNegativeButton("ȡ��", new OnClickListener() {
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
	 * ʹ�õ��������߽�������
	 */
	public void download() {
		
		//���ж� SDcard�Ƿ���õ�
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			HttpUtils httpUtils = new HttpUtils();
			// ����
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

						// �������ص�ʱ��
						@Override
						public void onLoading(long total, long current,
								boolean isUploading) {
							// TODO Auto-generated method stub
							super.onLoading(total, current, isUploading);
							pb.setMax((int) total);
							pb.setProgress((int) current);
							// ���صĽ�����ʾ����������
							tv_splash_progress.setVisibility(View.VISIBLE  );
							tv_splash_progress.setText((total * 100 / current)
									+ "%" + "/" + "100%");

						}
					});
		}else{
			Toast.makeText(getApplicationContext(), "sdcard������", 0).show();
		}
		
		
	}

	/**
	 * �������֮��Ҫ���а�װ��
	 */
	public void insert() {
		// ʹ��ϵͳ�İ�װ������ʽ��ͼ
		Intent intent = new Intent();
		// ͨ����ʽ��ͼ
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setDataAndType(
				Uri.fromFile(new File("/mnt/sdcard/mobileSafe_2.0.apk")),
				"application/vnd.android.package-archive");
		startActivityForResult(intent, 10);
	}
	/**
	 * ������Ҫ��д���ؽ��
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
 * �������ݿ�
 * 
 */
	public void copyDB(){
		
		new Thread(){
			public void run() {
				File file=new File(getFilesDir(),"address.db");
				if(!file.exists()){
					//����ļ������ڣ��Ϳ�ʼ�������ݿ�
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
						//�ر����ݿ�
						IOUtils.closeQuietly(in);
						IOUtils.closeQuietly(out);
					}
				}
			};
		}.start();

	}
	

}
