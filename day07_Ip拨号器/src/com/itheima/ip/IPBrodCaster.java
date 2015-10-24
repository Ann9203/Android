package com.itheima.ip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class IPBrodCaster extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String ipcode = getResultData();
		String newcode="17951"+ipcode;
		setResultData(newcode);
	}

}
