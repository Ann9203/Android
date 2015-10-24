package com.itheima.Reservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.text.TextUtils;

public class SIMChangeReserver extends BroadcastReceiver {

	private SharedPreferences sp;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		//只有当防盗模式开启的时候才会发送短信的
		if(sp.getBoolean("protect", false)){
			//发送短信
			
			//获取simka
			TelephonyManager telephony=(TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);;
			String simchange = telephony.getSimSerialNumber();
			sp=context.getSharedPreferences("config", context.MODE_PRIVATE);
			if(!TextUtils.isEmpty(simchange) && !TextUtils.isEmpty(sp.getString("sim",""))){
				if(!simchange.equals(sp.getString("sim",""))){
					//获取短信管理
					SmsManager sms=SmsManager.getDefault();
					//发送短信
					sms.sendTextMessage("5556", null, "手机被盗了", null, null);
				}
			}
		}
		
	}

}
