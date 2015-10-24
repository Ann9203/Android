package com.itheima.local;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShengZF extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
			String data=getResultData();
			System.out.println("省政府"+data);
			setResultData("中央下发80斤");
	}

}
