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
		//�����㲥�����ߵĶ���
		 soof=new ScreenOffOn();
		//ͨ��������ʵ�ֹ㲥�����ߵĴ���ʵ��
		//����IntentFileter����
		IntentFilter inf=new IntentFilter();
		//����action
		inf.addAction(Intent.ACTION_SCREEN_OFF);
		inf.addAction(Intent.ACTION_SCREEN_ON);
		//ע���¼�
		registerReceiver(soof, inf);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//����󶨵�ʱ�򣬽��ע��
		unregisterReceiver(soof);
	}

}
