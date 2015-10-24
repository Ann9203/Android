package com.itheima.msg;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;


public class MsgInterrupt extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle = intent.getExtras();
		Object[] object = (Object[]) bundle.get("pdus");
		for(Object msg:object)
		{
			SmsMessage message=SmsMessage.createFromPdu((byte[])msg);
			String body=message.getMessageBody();
			String address=message.getOriginatingAddress();
			System.out.println(body+":"+address);
			if("183336".contains(address))
			{
				abortBroadcast();
			}
		}
		
	}

}
