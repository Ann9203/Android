package com.example.httpclient;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	private Context mContex=this;
	private EditText et_username;
	private EditText et_pwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_username=(EditText)findViewById(R.id.et_username);
		et_pwd=(EditText)findViewById(R.id.et_pwd);
		

	}
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			String result=(String)msg.obj;
			if(result.contains("success"))
			{
				Toast.makeText(mContex, "登陆成功", 0).show();
			}else{
				Toast.makeText(mContex, "登陆失败", 0).show();
			}
			
		};
		
	};

	
	public void post(View v)
	{
		final String username=et_username.getText().toString().trim();
		final String pwd=et_pwd.getText().toString().trim();
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String path="http://192.168.56.1:8090/itheima74/servlet/LoginServlet";
				//创建客户端
				HttpClient httpClient = new DefaultHttpClient();
				//创建post
				HttpPost httpPost = new HttpPost(path);
				//封装Forma表单提交的数据
				BasicNameValuePair basic1 = new BasicNameValuePair("username", username);
				BasicNameValuePair basic2 = new BasicNameValuePair("pwd", pwd);
				ArrayList<BasicNameValuePair> array=new ArrayList<BasicNameValuePair>();
				array.add(basic1);
				array.add(basic2);
				
				try{
					//将表单的数据已经存在集合中了，这时候将集合中的数据给实体
					UrlEncodedFormEntity entity=new UrlEncodedFormEntity(array, "utf-8");
					httpPost.setEntity(entity);
					//使用客户端发送post请求
					HttpResponse hr = httpClient.execute(httpPost);
					if(hr.getStatusLine().getStatusCode()==200)
					{
						InputStream in = hr.getEntity().getContent();
						String result=StreamToString.streamToString(in);
						System.out.println(result);
						Message message = Message.obtain();
						message.obj=result;
						handler.sendMessage(message);
					}
					
					
				}catch(Exception e)
				{e.printStackTrace();}
				
			}
			
			
			
		}).start();
	}
	
	public void get(View v)
	{
		
		final String username=et_username.getText().toString().trim();
		final String pwd=et_pwd.getText().toString().trim();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String path="http://192.168.56.1:8090/itheima74/servlet/LoginServlet?username="+URLEncoder.encode(username)+"&pwd="+pwd;
				//获取Http客户端
				HttpClient httpClient = new DefaultHttpClient();
				//new 一个get对象
				HttpGet httpGet = new HttpGet(path);
				//获取请求
			try {
				HttpResponse hp = httpClient.execute(httpGet);
				//拿到响应头中的状态行
				StatusLine statusLine = hp.getStatusLine();
				if(statusLine.getStatusCode()==200)
				{
					//拿到响应头中的实体
					HttpEntity entity = hp.getEntity();
					//拿到实体中的内容，也就是得到响应的数据
					InputStream in = entity.getContent();
					//将输入流转换成字符串
					System.out.println(in.toString());
					String result=StreamToString.streamToString(in);		
					Message msg = Message.obtain();
					msg.obj=result;
					handler.sendMessage(msg);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
