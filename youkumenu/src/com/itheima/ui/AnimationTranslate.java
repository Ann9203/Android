package com.itheima.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimationTranslate {

	//设置正在运行的动画的个数
	public  static  int runningAnimationCount=0;
	/**
	 * 旋转出去的动画
	 */
	public static void rotateOutAnim(RelativeLayout layout,long delay){
		//设置有多少个子空间
		int childCount = layout.getChildCount();
		//如果隐藏，则找到所有的子view，禁用
		for(int i=0;i<childCount;i++){
			layout.getChildAt(i).setEnabled(false);
		}
		//开始动画，旋转动画
		RotateAnimation rotateAnimation=new RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,1.0f);
		//设置时间
		rotateAnimation.setDuration(500);
		//设置动画停留在结束的位置
		rotateAnimation.setFillAfter(true);
		//设置动画开始延时
		rotateAnimation.setStartOffset(delay);
		//设置事件的监听
		rotateAnimation.setAnimationListener(new MyAnimationListener() );
		layout.startAnimation(rotateAnimation);
	}
	static class MyAnimationListener implements AnimationListener{

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			//开始的时候
			runningAnimationCount++;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub
			runningAnimationCount--;
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
