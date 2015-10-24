package com.itheima.youkumenu;

import com.itheima.ui.AnimationTranslate;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class Splash extends Activity implements OnClickListener {

	private RelativeLayout rl_level1;
	private RelativeLayout rl_level2;
	private RelativeLayout rl_level3;
	 boolean isLevel3Display =true;
	 boolean isLevel2Display =true;
	 boolean isLevel1Display =true;
	 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		initView();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void  initView(){
		//��ӵ���¼�
		findViewById(R.id.ib_splash_icon_home).setOnClickListener(this);
		findViewById(R.id.ib_splash_menu).setOnClickListener(this);
		rl_level1 = (RelativeLayout) findViewById(R.id.rl_splash_level1);
		rl_level2 = (RelativeLayout) findViewById(R.id.rl_splash_level2);
		rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
		
		
	}
	/**
	 * ��дonKeyDown����a
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_MENU){
			if(AnimationTranslate.runningAnimationCount>0){
				return true;
			}
			//������µ��ǲ˵���ť
			if(isLevel1Display){
				long delay=0;
				//���������˵�
				if(isLevel3Display){
					AnimationTranslate.rotateOutAnim(rl_level3, 0);
					isLevel3Display=false;
					delay+=200;
				}
				//���ض����˵�
				if(isLevel2Display){
					AnimationTranslate.rotateOutAnim(rl_level2, delay);
					isLevel2Display=false;
					delay+=20;
				}
				//����һ���˵�
				AnimationTranslate.rotateOutAnim(rl_level1, delay);
			}else{
				//һ�ν���
				AnimationTranslate.rotateInAnim(rl_level1, 0);
				AnimationTranslate.rotateInAnim(rl_level2, 200);
				AnimationTranslate.rotateInAnim(rl_level3, 400);
				isLevel2Display=true;
				isLevel3Display=true;
			}
			isLevel1Display=!isLevel1Display;
			return true;//���ѵ�ǰ���¼�
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * ���õ���¼�
	 */
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.ib_splash_icon_home:
				if(isLevel2Display){
					long delay=0;
					//�����ǰ�������˵��Ѿ���ʾ�ˣ�����ת��ȥ
					if(isLevel3Display){
						AnimationTranslate.rotateOutAnim(rl_level3, 0);
						isLevel3Display=false;
						delay+=200;
					}
					//�����ǰ�����˵��Ѿ���ʾ��
					AnimationTranslate.rotateOutAnim(rl_level2, delay);
				}else{
					//��������˵�û����ʾ����������ʾ����
					AnimationTranslate.rotateInAnim(rl_level2, 0);
				}
				//�÷�
				isLevel2Display=!isLevel2Display;
				break;
			case R.id.ib_splash_menu:
				if(isLevel3Display){
					//����������˵���ʾ�˾�Ҫת��ȥ
					AnimationTranslate.rotateOutAnim(rl_level3, 0);
					//isLevel3Display=false;
				}else{
					AnimationTranslate.rotateInAnim(rl_level3, 0);
				}
				isLevel3Display=!isLevel3Display;
				break;
			default:
					break;
		}
		
	}


}
