package com.itheima.register;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//注册内容观察者
		ContentResolver resolver = getContentResolver();
		resolver.registerContentObserver(Uri.parse("content://sms"),//观察那个uri长得数据
				true, new MyOberser(new Handler()));
	}
	class MyOberser extends ContentObserver{

		public MyOberser(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}
		//数据库发生了改变需要进行通知，此方法调用
	
		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			super.onChange(selfChange);
			System.out.println("短信发生了通知");
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
