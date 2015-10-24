package com.itheima.Engien;

import java.util.ArrayList;
import java.util.List;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Debug.MemoryInfo;
import com.itheima.bean.TaskInfo;

/**
 * 获取当前的所有正在运行的进程
 * @author 雪宝宝
 *
 */
public class TaskAllInfo {

	public static List<TaskInfo> getRunningTaskinfo(Context context){
		List<TaskInfo> list=new ArrayList<TaskInfo>();
		//获取线程管理者
		ActivityManager activityManager=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		PackageManager packageManager = context.getPackageManager();
		//获取正在运行的进程
		List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
		for(RunningAppProcessInfo runAppInfo:runningAppProcesses){
			TaskInfo taskinfo=new TaskInfo();
			//获取线程的名称
			String packageName = runAppInfo.processName;
			taskinfo.setPackageName(packageName);
			//获取总的占用空间
			//先获取当前的空间的 信息	
			 MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{runAppInfo.pid});
			int totalPss = processMemoryInfo[0].getTotalPss();
			long ramSize=totalPss*1024;
			taskinfo.setRamSize(ramSize);
			
			try {
				//要获取软件的信息
				
				ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
				String name = applicationInfo.loadLabel(packageManager).toString();
				taskinfo.setName(name);
				Drawable loadIcon = applicationInfo.loadIcon(packageManager);
				taskinfo.setIcon(loadIcon);
				int flags=applicationInfo.flags;
				//判断他的状态是系统的还是用户进程
				boolean isUser;
				if((ApplicationInfo.FLAG_SYSTEM & flags)==ApplicationInfo.FLAG_SYSTEM ){
					isUser=false;
				}else{
					isUser=true;
				}
				taskinfo.setUser(isUser);
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(taskinfo.toString());
			list.add(taskinfo);
		}
		
		return list;
	}
}
