package com.itheima.codeRegisterBrodaReciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenOffOn extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//接受inent
		String action = intent.getAction();
		if(Intent.ACTION_SCREEN_OFF.equals(action))
		{
			System.out.println("关闭屏幕");
		}else if(Intent.ACTION_SCREEN_ON.equals(action))
		{
			System.out.println("屏幕开启");
		}
	}

}
