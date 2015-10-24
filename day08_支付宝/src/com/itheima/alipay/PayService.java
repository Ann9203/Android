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
		System.out.println("检测账号是否有异常");
		System.out.println("请输入用户名");
		System.out.println("请输入密码");
		System.out.println("请支付");
		System.out.println("支付成功");
	}

}
