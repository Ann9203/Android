package com.itheima.local;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class XianZF extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String order=getResultData();
		System.out.println("县政府收到命令"+order);
	}

}
