package com.itheima.visitservicemethos;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService  extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		//要返回一个对象
		return new MiShu();
	}
	
	class MiShu extends Binder implements Business{

		@Override
		public void banzhegn() {
			// TODO Auto-generated method stub
			MyService.this.banzhegn();
			//System.out.println("办证");
			
			
		}
		
	}
	public void banzhegn()
	{
		System.out.println("办理出国证件");
	}
	

}
