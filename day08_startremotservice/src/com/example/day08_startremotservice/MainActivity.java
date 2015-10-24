package com.example.day08_startremotservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.itheima.remoteservice.Business;
import com.itheima.remoteservice.Business.Stub;

public class MainActivity extends Activity {

	
	Business conn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent=new Intent("com.itheima.remoteservice");
		startService(intent);
		bindService(intent, new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				conn=Stub.asInterface(service);
			}
		}, BIND_AUTO_CREATE);
		
	}


	public void click1(View v)
	{
		try {
			conn.qianzheng();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
