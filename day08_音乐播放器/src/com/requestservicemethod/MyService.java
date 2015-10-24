package com.requestservicemethod;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {	
	
	
	//�ڷ����������������ʼ��ֹͣ���ŵ��¼�
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new Music();
	}
	public void start()
	{
		System.out.println("��ʼ��������");
	}
	public void stop()
	{
		System.out.println("ֹͣ��������");
	}
	//��������ʵ��Binder��ͬʱ�̳�Business�ӿ�
	class Music extends Binder implements Business
	{

		@Override
		public void start() {
			// TODO Auto-generated method stub
			MyService.this.start();
			
		}

		@Override
		public void stop() {
			// TODO Auto-generated method stub
			MyService.this.stop();
			
		}
		
	}

	
}
