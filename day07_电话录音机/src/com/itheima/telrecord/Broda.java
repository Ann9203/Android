package com.itheima.telrecord;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Broda extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//在广播中发因为广播就是电话的广播
		Intent in=new Intent(context,RecordServer.class);
		context.startService(in);

	}

}
