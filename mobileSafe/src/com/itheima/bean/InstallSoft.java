package com.itheima.bean;

import android.graphics.drawable.Drawable;
/**
 * 获取系统软件的具体信息
 * @author 雪宝宝
 *
 */

public class InstallSoft {
	private String name;
	private String packageName;
	private String versionName;
	private Drawable icon;
	private boolean isSD;
	private boolean isUser;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public boolean isSD() {
		return isSD;
	}
	public void setSD(boolean isSD) {
		this.isSD = isSD;
	}
	public boolean isUser() {
		return isUser;
	}
	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}
	public InstallSoft() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InstallSoft(String name, String packageName, String versionName,
			Drawable icon, boolean isSD, boolean isUser) {
		super();
		this.name = name;
		this.packageName = packageName;
		this.versionName = versionName;
		this.icon = icon;
		this.isSD = isSD;
		this.isUser = isUser;
	}
	@Override
	public String toString() {
		return "InstallSoft [name=" + name + ", packageName=" + packageName
				+ ", versionName=" + versionName + ", icon=" + icon + ", isSD="
				+ isSD + ", isUser=" + isUser + "]";
	}
	
}
