package com.itheima.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService {

	public MyIntentService() {
		super("MyIntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
			long start=System.currentTimeMillis();
			long endTime=System.currentTimeMillis()+20*1000;
			System.out.println("let's go");
			while(System.currentTimeMillis()<endTime)
			{
				synchronized (this) {
					try {
						wait(endTime-System.currentTimeMillis());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			long end =System.currentTimeMillis();
			System.out.println("ºÄÊ±²Ù×÷£º"+(end-start));
			
	}

}
