package com.itheima.intentservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

	//zaimyservice�д�����ʱ�Ĺ���
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		//�÷���������ʱ�����������ANR���쳣
		long endTime=System.currentTimeMillis()+20*1000;
		System.out.println("Let's go");
		while(System.currentTimeMillis()<endTime)
		{
			synchronized (this) {
				try {
					//�߳�����
					wait(endTime-System.currentTimeMillis());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
		}
		System.out.println("-----------��ʱ�������----------");
		
		return super.onStartCommand(intent, flags, startId);
	}

}
