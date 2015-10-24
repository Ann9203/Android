package com.requestservicemethod;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {	
	
	
	//在服务器中添加两个开始和停止播放的事件
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new Music();
	}
	public void start()
	{
		System.out.println("开始播放音乐");
	}
	public void stop()
	{
		System.out.println("停止播放音乐");
	}
	//创建对象实现Binder类同时继承Business接口
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
