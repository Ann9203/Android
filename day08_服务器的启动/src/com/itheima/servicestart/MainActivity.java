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
			//��ʼ���������ӵĶ���
			msm=new MyServiceMethod();
		}	

		public void start(View v)
		{
			//������ͼ����
			Intent intent=new Intent(this,MyService.class);
			startService(intent);
		}
		public void stop(View v)
		{
			//������ͼ����
			Intent intent=new Intent(this,MyService.class);
			//ֹͣ����
			stopService(intent);
		}
		public void bind(View v)
		{
			//�󶨷���
			//������ͼ����
			Intent intent=new Intent(this,MyService.class);
			//������
			//�󶨷�����Ҫ�Ĳ�����Ҫ�У���ͼ����ServiceConnection�����Լ��󶨵ľ�̬����
			bindService(intent, msm, BIND_AUTO_CREATE);
		}
		public void unbind(View v)
		{
			//������ͼ����
			Intent intent=new Intent(this,MyService.class);
			//������󣬵�ʱ�������ٶ�����ǽ������
			unbindService(msm);
		}
	
	  class	MyServiceMethod implements ServiceConnection
	  {
		
		  //�����ӳɹ���ʱ����ô˷���
			
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
		}
		//������ʧ�ܵ�ʱ��
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
	  }
}
