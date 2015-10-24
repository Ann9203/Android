package com.itheima.Utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;

public abstract class NetPreMethod extends Activity {

	protected SharedPreferences sp;
	protected GestureDetector gesture;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sp=getSharedPreferences("config", MODE_PRIVATE);
		//初始化
		gesture=new GestureDetector(this, new MyGestureDetector());
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		gesture.onTouchEvent(event);
		return super.onTouchEvent(event);
		
	}
	class MyGestureDetector extends SimpleOnGestureListener{
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			//获取按下去的x的坐标
			float startX = e1.getRawX();
			float endX = e2.getRawX();
			float rawY1 = e1.getRawY();
			float rawY2=e2.getRawY();
			if(startX-endX>100){
				nextMethod();
				
			}
			if(endX-startX>100){
				preMethod();
				
			}
			if((Math.abs(rawY1-rawY2))>50){
				return false;
			}
			return true;
		}
	}
	public void pre(View v){
		preMethod();
	}
	public void next(View v){
		nextMethod();
	}
	public abstract void preMethod();
	public abstract void nextMethod();
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if( keyCode==KeyEvent.KEYCODE_BACK){
			preMethod();
		}
		
		return super.onKeyDown(keyCode, event);
		
	}
}
