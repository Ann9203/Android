package com.itheima.zhendonghua;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		//获取图像id
//		ImageView iv=(ImageView)findViewById(R.id.iv);
//		//添加背景资源
//		iv.setBackgroundResource(R.drawable.frameanimation);
//		//获取背景资源
//		AnimationDrawable ad=(AnimationDrawable) iv.getBackground();
		//ad.start();
		ImageView iv=(ImageView)findViewById(R.id.iv);
		iv.setBackgroundResource(R.drawable.frameanimation);
		//获取资源
		AnimationDrawable ad=(AnimationDrawable) iv.getBackground();
		ad.start();
		
	}

	
}
