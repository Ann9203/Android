package com.itheima.server;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.text.format.Formatter;
import android.widget.RemoteViews;

import com.itheima.Reservice.MyWidget;
import com.itheima.Utils.SizeCont;
import com.itheima.mobileSafe.R;

public class MyWidgetServer extends Service {

	private WidgetReceiver widgetReceiver;
	private AppWidgetManager appWidgetManager;
	private Timer timer;
	private ScreenOffReceiver screenOffReceiver;
	private ScreenOnReceiver screenOnReceiver;
	//获取广播
	private class WidgetReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			killProcess();
		}
		
	}
	/**
	 *锁屏的广播接受者
	 */
	private class ScreenOffReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//先清理进程
			killProcess();
			//锁屏
			stopUpdates();
		}
		
	}
	/**
	 * 解锁的广播接受者
	 */
	private class ScreenOnReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			updateWidgets();
		}
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		widgetReceiver = new WidgetReceiver();
		//注册一个事件
		IntentFilter intentFilter=new IntentFilter();
		intentFilter.addAction("aa.bb.cc");
		//注册广播接受者
		registerReceiver(widgetReceiver,intentFilter);
		
		screenOffReceiver = new ScreenOffReceiver();
		//设置接受广播
		IntentFilter offIntentFilter=new IntentFilter();
		offIntentFilter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(screenOffReceiver, offIntentFilter);
		
		screenOnReceiver = new ScreenOnReceiver();
		IntentFilter onIntentFilter=new IntentFilter();
		onIntentFilter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(screenOnReceiver, onIntentFilter);

		//widget的管理者
		appWidgetManager=AppWidgetManager.getInstance(this);
		updateWidgets();
		
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopUpdates();
		if(widgetReceiver!=null){
			unregisterReceiver(widgetReceiver);
			widgetReceiver=null;		
		}
		if(screenOffReceiver!=null){
			unregisterReceiver(screenOffReceiver);
			screenOffReceiver=null;
		}
		if(screenOnReceiver!=null){
			unregisterReceiver(screenOnReceiver);
			screenOnReceiver=null;
		}
	}
	/**
	 * 杀死进程的操作
	 */
	public void  killProcess(){
		//获取进程的管理者
		ActivityManager am=(ActivityManager) getSystemService(ACTIVITY_SERVICE);
		//获取正在运行的线程
		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
		for(RunningAppProcessInfo rainfo:runningAppProcesses){
			if(!rainfo.processName.equals(getPackageName())){
				//进程杀死
				am.killBackgroundProcesses(rainfo.processName);
			}
		}
	}
	/**
	 * 停止更新
	 */
	public  void stopUpdates(){
		if(timer!=null){
			timer.cancel();
			timer=null;
		}
	}
	/**
	 * 更新小窗体的控件
	 * 时隔多长时间更新一次
	 */
	public void updateWidgets(){
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("widget更新啦啦啦啦");
				//获取组件的标示
				ComponentName provider=new ComponentName(MyWidgetServer.this, MyWidget.class);
				//获取远程布局
				//packageName:应用的包名
				//layoutId:widget布局文件
				RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.process_widget);
				//远程布局不能通过findViewById获取初始化空间
				//更新布局文件中相应控件的值
				//viewId：更新控件的id
				//text：更新的内容
				remoteViews.setTextViewText(R.id.process_count, "正在运行的软件："+SizeCont.getProcessCount(MyWidgetServer.this));
				remoteViews.setTextViewText(R.id.process_memory, "可用内存："+Formatter.formatFileSize(MyWidgetServer.this, SizeCont.getAvailableRam(MyWidgetServer.this)));
				//设置按钮的点击事件
				Intent intent=new Intent();
				//发送广播
				intent.setAction("aa.bb.cc");
				//通过发送一个广播标示要执行清理的操作，通过接受发送的广播执行清理的操作
				//flags标示制定的信息的标签
				//延迟发送广播
				PendingIntent pendingIntent=PendingIntent.getBroadcast(MyWidgetServer.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				//pendingIntent ：延迟意图，包含一个intent意图，当点击的时候才回去执行这个意图，不点击就不会执行
				remoteViews.setOnClickPendingIntent(R.id.btn_clear, pendingIntent);
				//更新的操作
				appWidgetManager.updateAppWidget(provider, remoteViews);
			}
		}, 2000, 2000);
		
	}

}
