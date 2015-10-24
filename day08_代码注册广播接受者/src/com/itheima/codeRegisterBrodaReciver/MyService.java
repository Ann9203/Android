package com.itheima.codeRegisterBrodaReciver;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class MyService extends Service {

	ScreenOffOn  soof;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//创建广播接受者的对象
		 soof=new ScreenOffOn();
		//通过服务来实现广播接收者的代码实现
		//创建IntentFileter对象
		IntentFilter inf=new IntentFilter();
		//创建action
		inf.addAction(Intent.ACTION_SCREEN_OFF);
		inf.addAction(Intent.ACTION_SCREEN_ON);
		//注册事件
		registerReceiver(soof, inf);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//解除绑定的时候，解除注册
		unregisterReceiver(soof);
	}

}
