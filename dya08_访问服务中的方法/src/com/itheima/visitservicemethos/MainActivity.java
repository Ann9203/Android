package com.itheima.visitservicemethos;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

	
public class MainActivity extends Activity {
	Business banzheng;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent=new Intent(this,MyService.class);
		bindService(intent, new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				
			}
			
			//又返回值就会调用这个方法，没有返回值就不会调用这个方法
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				banzheng=(Business) service;
			}
		}, BIND_AUTO_CREATE);
	}

	public void click(View v)
	{
		banzheng.banzhegn();
		//banzheng.banzhegn();
	}

}
