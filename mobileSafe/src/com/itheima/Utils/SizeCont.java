package com.itheima.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;

public class SizeCont {

	/**
	 * 获取正在运行进程的个数
	 * @param context
	 * @return
	 */
	public static int getProcessCount(Context context){
		ActivityManager am=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
		return runningAppProcesses.size();
	}
	/**
	 * 获取剩余内存
	 */
	public static long getAvailableRam(Context context){
		ActivityManager am=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		//获取内存信息
		MemoryInfo outInfo = new MemoryInfo();
		am.getMemoryInfo(outInfo);
		//获取空闲内存
		return outInfo.availMem;
	}
	/**
	 * 获取总内存
	 */
	public static long getTotalRam(Context context){
		ActivityManager am=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		//获取内存的信息，保存到memory中
		MemoryInfo outInfo=new MemoryInfo();
		am.getMemoryInfo(outInfo);
		return outInfo.totalMem;//这个只有16版本才能 用

	} 

	 /**
	  * 通过解析流数据
	  */
	public static long getStreamRam(Context context){
		File file=new File("/proc/meminfo");
		StringBuffer sb=new StringBuffer();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			//只是读取第一行就OK了
			String readLine = br.readLine();
			//讲字符串转换成数字
			char[] charArray=readLine.toCharArray();
			for(char c:charArray){
				sb.append(c);
			}
			String string=sb.toString();
			//转换成Long类型
			long parseLong=Long.parseLong(string);
			return parseLong*1024;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
}
