package com.itheima.telrecord;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Broda extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//�ڹ㲥�з���Ϊ�㲥���ǵ绰�Ĺ㲥
		Intent in=new Intent(context,RecordServer.class);
		context.startService(in);

	}

}
