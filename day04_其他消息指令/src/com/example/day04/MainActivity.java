package com.example.day04;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Handler handler=new Handler();
		final TextView tv_simple=(TextView)findViewById(R.id.tv_simple);
		
//		new Thread(new Runnable(){
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				//先要沉睡1秒
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				
//				//然后让子线程如果在沉睡1秒后发现自己被更新了
//				handler.postDelayed(new Runnable()
//				{
//
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						tv_simple.setText("我被更新了");
//					}
//					
//				}, 5000);
//				
//				
//			}
//			
//		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				handler.postDelayed(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						tv_simple.setText("我被更新新啦！！！！！！！");
					}
					
					
				}, 2000);
				
			}
			
			
			
			
		}).start();
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
