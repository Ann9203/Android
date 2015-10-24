package com.itheima.leisuo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LeSuo extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("¿ª»úÆô¶¯");
		Intent in=new Intent(context,MainActivity.class);
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(in);

	}

}
