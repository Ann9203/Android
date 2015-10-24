package com.example.day07_sdstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SDCard extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action=intent.getAction();
		if(Intent.ACTION_MEDIA_MOUNTED.equals(action))
		{
			Toast.makeText(context, "SDCard准备就绪", 0).show();
		}else if(Intent.ACTION_MEDIA_REMOVED.equals(action))
		{
			Toast.makeText(context, "SDCard被拔掉", 0).show();
		}else if(Intent.ACTION_MEDIA_UNMOUNTED.equals(action))
		{
			Toast.makeText(context, "SDCard被卸载", 0).show();
		}

	}

}
