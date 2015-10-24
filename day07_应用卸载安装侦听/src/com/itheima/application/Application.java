package com.itheima.application;

import java.net.URI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class Application extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		String action = intent.getAction();
		//获取数据uri
		Uri uri=intent.getData();
		
		if(Intent.ACTION_PACKAGE_ADDED.equals(action))
		{
			Toast.makeText(context, uri+"软件加载", 0).show();
			
		}else if(Intent.ACTION_PACKAGE_REMOVED.equals(action))
		{
			//如果取出的action和packageremoved是一样的，那么就弹出软件卸载
			Toast.makeText(context, uri+"软件卸载", 0).show();
		}else if(Intent.ACTION_PACKAGE_REPLACED.equals(action))
		{
			Toast.makeText(context, uri+"软件更新", 0).show();
		}
 }

}
