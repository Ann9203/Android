package com.itheima.Reservice;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.itheima.server.MyWidgetServer;

public class MyWidget extends AppWidgetProvider {

	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		System.out.print("onUpdate");
		//开启更新服务
		Intent intent=new Intent(context,MyWidgetServer.class);
		context.startService(intent);
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		System.out.print("onReceive");
	}
	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		System.out.print("onEnabled");
	}
	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		System.out.print("onDisabled");
		//停止服务
		Intent intent=new Intent(context,MyWidgetServer.class);
		context.stopService(intent);
	}
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		System.out.print("onDeleted");
	}
}
