package com.itheima.Utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;

public class CallLocationUtils {
	/**
	 * 这个是查看在后台运行了多少个程序
	 * 查看我们传过来的这个className是否在后台运行着
	 * @param calssName
	 * @return
	 */
	public static boolean isRunning(String className,Context context){
		//获取活动管理器
		ActivityManager activityManager=(ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		//获取后台运行的总个数
		//1000： maxNum的意思是上限是多少个
		List<RunningServiceInfo> runningServices = activityManager.getRunningServices(1000);
		//遍历循环这些service信息
		for(RunningServiceInfo runningService:runningServices){
				//获取服务
			ComponentName service = runningService.service;
			//和服务的类名进行比较
			if(className.equals(service.getClassName())){
				return true;
			}
		}
		return false;
	}
}
