package com.itheima.local;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShiZF extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String order=getResultData();
		System.out.println("ÊÐÕþ¸®"+order);
		abortBroadcast();

	}

}
