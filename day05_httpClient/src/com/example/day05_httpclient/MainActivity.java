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
				// 创建httpClient对象
				HttpClient httpClient = new DefaultHttpClient();
				// 创建get请求对象
				HttpGet hget = new HttpGet(path);
				try {
					// 使用客户端发送请求，获取响应对象
					HttpResponse hresponse = httpClient.execute(hget);
					// 获取状态行响应码
					if (hresponse.getStatusLine().getStatusCode() == 200) {
						// 获取实体
						HttpEntity entity = hresponse.getEntity();
						// 根据实体获取输入流
						InputStream in = entity.getContent();
						// 将输入流转换为字符串
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
				//创建一个post请求
				
				//创建一个BasicNameValuePair封装要传递的数据表单
				
				BasicNameValuePair bvp1=new BasicNameValuePair("username", username);
				BasicNameValuePair bvp2=new BasicNameValuePair("pwd",pwd);
				HttpPost httpPost = new HttpPost(path);
				ArrayList<BasicNameValuePair> array=new ArrayList<BasicNameValuePair>();
				array.add(bvp1);
				array.add(bvp2);
				//获取实体UrlEncodeFormEntity
				try{
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(array);
					//把实体封装到post中
					httpPost.setEntity(entity);
					HttpResponse response = httpClient.execute(httpPost);
					if(response.getStatusLine().getStatusCode()==200)
					{
						// 获取实体
						HttpEntity entity1 = response.getEntity();
						// 根据实体获取输入流
						InputStream in = entity1.getContent();
						// 将输入流转换为字符串
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
