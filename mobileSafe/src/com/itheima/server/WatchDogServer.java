package com.itheima.server;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import com.itheima.db.dao.WatchDogDAO;
import com.itheima.mobileSafe.WatchDogActivity;

/**
 * watchDog的服务
 * @author 雪宝宝
 *
 */
public class WatchDogServer extends Service {

	private Unlock unlock;
	private ScreenOffReceiver screenOffReceiver;
	private boolean islock;
	private List<String> list;
	private WatchDogDAO watchDogDao;
	private String unlockcurrentPackageName;
	/**
	 * 解锁的广播接受者
	 */
	private class Unlock extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//解锁的操作
			unlockcurrentPackageName=intent.getStringExtra("packagename");
		}
		
	}
	/**
	 * 注册锁屏的广播接受者
	 */
	private class ScreenOffReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//枷锁的操作
			unlockcurrentPackageName=null;
		}
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		watchDogDao=new WatchDogDAO(getApplicationContext());
		//注册解锁的广播接受者
		unlock = new Unlock();
		IntentFilter intentFilter =new IntentFilter();
		intentFilter.addAction("com.itheima.mobileSafe.Unlock");
		registerReceiver(unlock, intentFilter);
		
		screenOffReceiver = new ScreenOffReceiver();
		IntentFilter screenFilter=new IntentFilter();
		screenFilter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(screenOffReceiver, screenFilter);
		//时时刻刻监听用户打开activity
		final ActivityManager am=(ActivityManager) getSystemService(ACTIVITY_SERVICE);
		islock=true;
		new Thread(){
			
			public void run() {
				//先将数据库中的数据,查询存放到内存,然后再把数据从内存中获取出来进行操作
				//当数据库变化的时候重新更新内存中的数据,当数据库变化的时候通知内容观察者数据库变化了,然后在内容观察者中去更新最新的数据
				//获取数
				Uri path=Uri.parse("content://com.itheima.mobileSafe.lock.change");
				
				//参数2：精确匹配还是模糊匹配
				getContentResolver().registerContentObserver(path, true, new ContentObserver(null) {
				
					public void onChange(boolean selfChange) {
						//监测更新道德数据
						list=watchDogDao.queryAllPackageName();
					};
				});
				//加载数据
				list=watchDogDao.queryAllPackageName();
				//上锁
				while(islock){
					//监听操作，
					//监听用户打开了那个任务栈，打开了那些应用程序
					//监听正在运行的程序
					//maxNum：获取正在运行的任务栈的个数
					//现在打开应用的任务栈，永远在第一个而之前的应用的任务栈会一次往后
					//现在打开
					List<RunningTaskInfo> runningTasks = am.getRunningTasks(1);
					for(RunningTaskInfo runningTaskInfo:runningTasks){
						//获取任务栈栈底的activity
						ComponentName baseactivity=runningTaskInfo.baseActivity;
						//获取包名
						String packageName = baseactivity.getPackageName();
						//判断list包中是否包含packagename 
						boolean b=list.contains(packageName);
						if(b){
							//如果包含的话，就弹出解锁的界面
							if(!packageName.equals(unlockcurrentPackageName)){
								//弹出解锁的界面
								Intent intent =new Intent(WatchDogServer.this,WatchDogActivity.class);
								//把包名带过去
								intent.putExtra("packagename", packageName);
								intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								//发送 
								startActivity(intent);
							}
						}
						
					}
					SystemClock.sleep(1000);
				}
			};
		}.start();
		
	}
	/**
	 * 注销
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		islock=false;
		if(unlock!=null){
			unregisterReceiver(unlock);
			unlock=null;
		}
		if(screenOffReceiver!=null){
			unregisterReceiver(screenOffReceiver);
			screenOffReceiver=null;
		}
	}
	
	

}
