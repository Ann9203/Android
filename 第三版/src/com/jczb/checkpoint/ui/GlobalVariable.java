package com.jczb.checkpoint.ui;

import android.app.Application;

/**
 * 全局变量类
 * 用于多个Activity公用，具有set和get方法
 * 程序运行的时候将被创建到进程中
 * @author wlc
 * @date 2015-4-13
 */
public class GlobalVariable extends Application {

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	
}
