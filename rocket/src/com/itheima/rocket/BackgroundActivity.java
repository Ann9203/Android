package com.itheima.rocket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class BackgroundActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_background);
		AlphaAnimation alphaAnimation=new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(200);
		//��������
		ImageView iv_smoke1=(ImageView) findViewById(R.id.iv_smoke_m);
		ImageView iv_somke2=(ImageView) findViewById(R.id.iv_smoke_t);
	//AnimationDrawable 
		//����һ������Ķ���
		
		iv_smoke1.startAnimation(alphaAnimation);
		iv_somke2.startAnimation(alphaAnimation);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				finish();
			}
		
		}, 500);
//		new Thread(){
//			public void run() {
//				
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				//��������
//
//				runOnUiThread(new Runnable(){
//					public void run() {
//						finish();
//					};
//				});
//				
//				
//			};
//			
//		}.start();

	}
}
