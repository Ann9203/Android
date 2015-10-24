package com.itheima.bean;

import android.graphics.drawable.Drawable;

public class TaskInfo {

	private long ramSize;
	private Drawable icon;
	private String name;
	private String packageName;
	private boolean isUser;
	private boolean isChecked;
	
	public long getRamSize() {
		return ramSize;
	}

	public void setRamSize(long ramSize) {
		this.ramSize = ramSize;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

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

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public TaskInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskInfo(long ramSize, Drawable icon, String name,
			String packageName, boolean isUser, boolean isChecked) {
		super();
		this.ramSize = ramSize;
		this.icon = icon;
		this.name = name;
		this.packageName = packageName;
		this.isUser = isUser;
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return "TaskInfo [ramSize=" + ramSize + ", icon=" + icon + ", name="
				+ name + ", packageName=" + packageName + ", isUser=" + isUser
				+ ", isChecked=" + isChecked + "]";
	}
	
}
