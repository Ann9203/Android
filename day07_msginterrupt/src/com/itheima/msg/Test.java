package com.itheima.msg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class Test extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		//获取Bundle
		Bundle bundle = intent.getExtras();
		//获取短信组
		Object[] object = (Object[]) bundle.get("pdus");
		for(Object msg:object)
		{
			SmsMessage message=SmsMessage.createFromPdu((byte[]) msg);
			String body=message.getMessageBody();
			String address=message.getOriginatingAddress();
			System.out.println(body+":"+address);
			if("186665".contains(address))
			{
				abortBroadcast();
			}
		}

	}

}
