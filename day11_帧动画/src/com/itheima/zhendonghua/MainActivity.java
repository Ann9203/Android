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
//		//��ȡͼ��id
//		ImageView iv=(ImageView)findViewById(R.id.iv);
//		//��ӱ�����Դ
//		iv.setBackgroundResource(R.drawable.frameanimation);
//		//��ȡ������Դ
//		AnimationDrawable ad=(AnimationDrawable) iv.getBackground();
		//ad.start();
		ImageView iv=(ImageView)findViewById(R.id.iv);
		iv.setBackgroundResource(R.drawable.frameanimation);
		//��ȡ��Դ
		AnimationDrawable ad=(AnimationDrawable) iv.getBackground();
		ad.start();
		
	}

	
}
