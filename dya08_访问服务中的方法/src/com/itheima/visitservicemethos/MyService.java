package com.itheima.visitservicemethos;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService  extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		//Ҫ����һ������
		return new MiShu();
	}
	
	class MiShu extends Binder implements Business{

		@Override
		public void banzhegn() {
			// TODO Auto-generated method stub
			MyService.this.banzhegn();
			//System.out.println("��֤");
			
			
		}
		
	}
	public void banzhegn()
	{
		System.out.println("�������֤��");
	}
	

}
