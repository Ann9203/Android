package com.example.day04_code;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private EditText et_url;
	private TextView tv_code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取每个空间的id值
		et_url=(EditText)findViewById(R.id.et_urll);
		Button bt_download=(Button)findViewById(R.id.bt_download);
		 tv_code=(TextView)findViewById(R.id.tv_code);
		
		//点击的时间
		bt_download.setOnClickListener(this);
		
	}
	//注意主线程不可以处理耗时的程序
	//子线程不可以对UI界面进行更新
	Handler handler=new Handler(){
		//重写方法
		public void handleMessage(android.os.Message msg)
		{
			//要获取message
			String result=(String) msg.obj;
			//展示到前台界面
			tv_code.setText(result);
			
		}
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//设置点击事件
		//获取url
		final String path=et_url.getText().toString().trim();
		//定义URL对向 
		//主线程中不可以有耗时的动作所要要变成子线程
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					URL url = new URL(path);
					//设置HttpURLConnection 
					HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
					//设置请求码
					openConnection.setRequestMethod("GET");
					//设置超时时间
					openConnection.setConnectTimeout(10*1000);
					//设置状态吗
					if(openConnection.getResponseCode()==200)
					{
						InputStream in = openConnection.getInputStream();
						//我们要把流转换成字符串
						String result=StreamToString.changToString(in);
						//创建一个Message对象
						System.out.println();
						Message msg = new Message();
						//获取对象
						msg.obj=result;
						//发送对象
						handler.sendMessage(msg);
						in.close();
					}
					
					//获取输入流
					
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
