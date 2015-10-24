package com.itheima.Utils;

import android.os.Handler;

/**
 * 创建一个线程的工具类
 * @author 雪宝宝
 *
 */
public abstract class MyAysncTask {
	private Handler handler =new Handler(){
		public void handleMessage(android.os.Message msg) {
			nextExecute();
		};
		
	};
	public abstract void preExecute();
	public abstract void nextExecute();
	public abstract void doInExecute();
	
	public void execute(){
		preExecute();
		new Thread(){
			public void run() {
				doInExecute();
				handler.sendEmptyMessage(0);
			};
		}.start();
		
	}

}
