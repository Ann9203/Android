package com.tweenanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv=(ImageView)findViewById(R.id.iv);
	}

	public void translate(View v)
	{
		TranslateAnimation ta=new TranslateAnimation(-100, 100, -50, 50);
		//设置总共延续多长时间
		ta.setDuration(800);
		//设置重复的次数
		ta.setRepeatCount(1);
		//设置播放的的样式  旋转播放
		ta.setRepeatMode(Animation.REVERSE);
		//让组件停留在结束
		ta.setFillAfter(true);
		//开始动画
		iv.startAnimation(ta);
	}

}
