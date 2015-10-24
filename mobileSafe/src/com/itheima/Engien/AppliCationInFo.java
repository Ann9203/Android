package com.itheima.Engien;

import java.util.ArrayList;
import java.util.List;
import com.itheima.bean.InstallSoft;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class AppliCationInFo {

	public static List<InstallSoft> getAppInfo(Context context){
		List<InstallSoft> list=new ArrayList<InstallSoft>();
		//获取包的管理者
		PackageManager packageManager = context.getPackageManager();
		//获取安装的所有的软件
		List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
		for(PackageInfo packageinfo:installedPackages){
			//获取包名
			String packageName=packageinfo.packageName;
			//获取版本号
			String versionName=packageinfo.versionName;
			//获取引用程序f
			ApplicationInfo applicationInfo = packageinfo.applicationInfo;
			//获取图标
			Drawable icon=applicationInfo.loadIcon(packageManager);
			//获取程序名称
			String name = applicationInfo.loadLabel(packageManager).toString();
			//是否是用户程序
			//获取应用程序中相关信息,是否是系统程序和是否安装到SD卡
			boolean isUser;
			int flags=applicationInfo.flags;
			if((ApplicationInfo.FLAG_SYSTEM & flags )==ApplicationInfo.FLAG_SYSTEM){
				//系统程序
				isUser=false;
			}else{
				//用户程序
				isUser=true;
			}
			boolean  isSD;
			if((ApplicationInfo.FLAG_EXTERNAL_STORAGE & flags)==ApplicationInfo.FLAG_EXTERNAL_STORAGE){
				//是Sd卡存储
				isSD=true;
			}else{
				isSD=false;
			}
			InstallSoft installSoft=new InstallSoft(name, packageName, versionName, icon, isSD, isUser);
			
			list.add(installSoft);
		}
		
		
		return list;
		
	}
}
