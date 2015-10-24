package com.receivecustomBrodacaster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receive extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("收到自定义广播");
	}

}
