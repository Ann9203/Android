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
		//�����ܹ������೤ʱ��
		ta.setDuration(800);
		//�����ظ��Ĵ���
		ta.setRepeatCount(1);
		//���ò��ŵĵ���ʽ  ��ת����
		ta.setRepeatMode(Animation.REVERSE);
		//�����ͣ���ڽ���
		ta.setFillAfter(true);
		//��ʼ����
		iv.startAnimation(ta);
	}

}
