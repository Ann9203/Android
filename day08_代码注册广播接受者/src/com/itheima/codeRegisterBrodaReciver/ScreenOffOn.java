package com.itheima.codeRegisterBrodaReciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenOffOn extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//����inent
		String action = intent.getAction();
		if(Intent.ACTION_SCREEN_OFF.equals(action))
		{
			System.out.println("�ر���Ļ");
		}else if(Intent.ACTION_SCREEN_ON.equals(action))
		{
			System.out.println("��Ļ����");
		}
	}

}
