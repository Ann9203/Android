package com.itheima.servicestart;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {
	MyServiceMethod msm;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			//初始化服务链接的对象
			msm=new MyServiceMethod();
		}	

		public void start(View v)
		{
			//创建意图对象
			Intent intent=new Intent(this,MyService.class);
			startService(intent);
		}
		public void stop(View v)
		{
			//创建意图对象
			Intent intent=new Intent(this,MyService.class);
			//停止服务
			stopService(intent);
		}
		public void bind(View v)
		{
			//绑定服务
			//创建意图对象
			Intent intent=new Intent(this,MyService.class);
			//开启绑定
			//绑定服务需要的参数主要有：意图对象，ServiceConnection对象，以及绑定的静态常量
			bindService(intent, msm, BIND_AUTO_CREATE);
		}
		public void unbind(View v)
		{
			//创建意图对象
			Intent intent=new Intent(this,MyService.class);
			//解除对象，当时不是销毁对象而是解除服务
			unbindService(msm);
		}
	
	  class	MyServiceMethod implements ServiceConnection
	  {
		
		  //当链接成功的时候调用此方法
			
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
		}
		//当链接失败的时候
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
	  }
}
