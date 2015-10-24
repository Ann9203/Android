package com.example.day05_httpclient;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_username;
	private EditText et_pwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_username = (EditText) findViewById(R.id.et_username);
		et_pwd = (EditText) findViewById(R.id.et_pwd);
	}

	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			String result = (String) msg.obj;
			
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
			

		};
	};


	public void get(View v) {
		 final String username = et_username.getText().toString().trim();
		final String pwd = et_pwd.getText().toString().trim();
		new Thread(new Runnable() {
			@Override
			public void run() {
				String path = "http://192.168.56.1:8090/itheima74/servlet/LoginServlet?username="
						+ URLEncoder.encode(username) + "&pwd=" + pwd;
				// ����httpClient����
				HttpClient httpClient = new DefaultHttpClient();
				// ����get�������
				HttpGet hget = new HttpGet(path);
				try {
					// ʹ�ÿͻ��˷������󣬻�ȡ��Ӧ����
					HttpResponse hresponse = httpClient.execute(hget);
					// ��ȡ״̬����Ӧ��
					if (hresponse.getStatusLine().getStatusCode() == 200) {
						// ��ȡʵ��
						HttpEntity entity = hresponse.getEntity();
						// ����ʵ���ȡ������
						InputStream in = entity.getContent();
						// ��������ת��Ϊ�ַ���
						String result = StreamToString.streamToString(in);
						System.out.println(result);
						Message msg = handler.obtainMessage();
						msg.obj = result;
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}).start();

	}

	public void post(View v) {
		 final String username = et_username.getText().toString().trim();
		final String pwd = et_pwd.getText().toString().trim();
		new Thread(new Runnable(){
			@Override
			public void run() {
				String path = "http://192.168.56.1:8090/itheima74/servlet/LoginServlet";
				DefaultHttpClient httpClient = new DefaultHttpClient();
				//����һ��post����
				
				//����һ��BasicNameValuePair��װҪ���ݵ����ݱ�
				
				BasicNameValuePair bvp1=new BasicNameValuePair("username", username);
				BasicNameValuePair bvp2=new BasicNameValuePair("pwd",pwd);
				HttpPost httpPost = new HttpPost(path);
				ArrayList<BasicNameValuePair> array=new ArrayList<BasicNameValuePair>();
				array.add(bvp1);
				array.add(bvp2);
				//��ȡʵ��UrlEncodeFormEntity
				try{
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(array);
					//��ʵ���װ��post��
					httpPost.setEntity(entity);
					HttpResponse response = httpClient.execute(httpPost);
					if(response.getStatusLine().getStatusCode()==200)
					{
						// ��ȡʵ��
						HttpEntity entity1 = response.getEntity();
						// ����ʵ���ȡ������
						InputStream in = entity1.getContent();
						// ��������ת��Ϊ�ַ���
						String result = StreamToString.streamToString(in);
						System.out.println(result);
						Message msg = handler.obtainMessage();
						msg.obj = result;
						handler.sendMessage(msg);
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
			
		}).start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
