package com.itheima.remoteservice;

import com.itheima.remoteservice.Business.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class MyRemoteService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new MyBusiness();
	}
	
	
		class MyBusiness extends Stub
		{

			@Override
			public void qianzheng() throws RemoteException {
				// TODO Auto-generated method stub
				MyRemoteService.this.visa();
				
			}

			
		}
	
	public void visa()
	{
		System.out.println("找局长办证！！！");
	}
}
