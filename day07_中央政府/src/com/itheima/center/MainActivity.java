package com.itheima.center;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click(View v)
	{
		//这次是发送有序的广播
		//首先还是创建意图对象
		Intent in=new Intent();
		//创建发送的action的名称
		in.setAction("com.itheima.fan");
		//发送有序的广播
		
		sendOrderedBroadcast(in, null,//接受的权限
				
		 new MyReciver(), //传入结果接受者对象，他会在所有广播就诶守着都收到广播之后太会收到广播，并且一定会受到这条广播
		 
		 null, 100, "每个人下发100斤大米", null);//广播中可以直接携带这三样数据
		
	}
	class MyReciver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//只见诶获取到数据
			String order=getResultData();
			System.out.println("反贪局收到信息"+order);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
