package com.itheima.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimationTranslate {

	//�����������еĶ����ĸ���
	public  static  int runningAnimationCount=0;
	/**
	 * ��ת��ȥ�Ķ���
	 */
	public static void rotateOutAnim(RelativeLayout layout,long delay){
		//�����ж��ٸ��ӿռ�
		int childCount = layout.getChildCount();
		//������أ����ҵ����е���view������
		for(int i=0;i<childCount;i++){
			layout.getChildAt(i).setEnabled(false);
		}
		//��ʼ��������ת����
		RotateAnimation rotateAnimation=new RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,1.0f);
		//����ʱ��
		rotateAnimation.setDuration(500);
		//���ö���ͣ���ڽ�����λ��
		rotateAnimation.setFillAfter(true);
		//���ö�����ʼ��ʱ
		rotateAnimation.setStartOffset(delay);
		//�����¼��ļ���
		rotateAnimation.setAnimationListener(new MyAnimationListener() );
		layout.startAnimation(rotateAnimation);
	}
	static class MyAnimationListener implements AnimationListener{

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			//��ʼ��ʱ��
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
