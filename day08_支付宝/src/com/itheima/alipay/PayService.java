package com.itheima.alipay;

import com.itheima.alipay.pay.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class PayService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new MyPay();
	}
	
	class MyPay extends Stub{

		@Override
		public void willPay() throws RemoteException {
			// TODO Auto-generated method stub
			PayService.this.willPay();
		}
		
	}
	public void willPay()
	{
		System.out.println("����˺��Ƿ����쳣");
		System.out.println("�������û���");
		System.out.println("����������");
		System.out.println("��֧��");
		System.out.println("֧���ɹ�");
	}

}
