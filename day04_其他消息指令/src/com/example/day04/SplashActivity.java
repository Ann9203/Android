package com.example.day04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_splish);
//		//使用Handler的 postDeplay做一个延迟的页面
//		new Handler().postDelayed(
//				new Runnable(){
//
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						//通过使用意图来实现界面的跳转  
//						//第一个参数是关于本对象，第二个是要跳转到的那个界面
//						Intent intent=new Intent(SplashActivity.this,MainActivity.class);
//						startActivity(intent);
//					}
//					
//					
//				}
//				
//				
//				, 8000);
//		
		new Handler().postDelayed(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SplashActivity.this,MainActivity.class);
				startActivity(intent);
				
			}
			
			
		}, 8000);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
