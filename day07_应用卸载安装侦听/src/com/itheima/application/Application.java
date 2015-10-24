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
		//��ȡ����uri
		Uri uri=intent.getData();
		
		if(Intent.ACTION_PACKAGE_ADDED.equals(action))
		{
			Toast.makeText(context, uri+"�������", 0).show();
			
		}else if(Intent.ACTION_PACKAGE_REMOVED.equals(action))
		{
			//���ȡ����action��packageremoved��һ���ģ���ô�͵������ж��
			Toast.makeText(context, uri+"���ж��", 0).show();
		}else if(Intent.ACTION_PACKAGE_REPLACED.equals(action))
		{
			Toast.makeText(context, uri+"�������", 0).show();
		}
 }

}
