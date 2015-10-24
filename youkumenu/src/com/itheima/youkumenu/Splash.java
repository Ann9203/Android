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
	 * 初始化控件
	 */
	private void  initView(){
		//添加点击事件
		findViewById(R.id.ib_splash_icon_home).setOnClickListener(this);
		findViewById(R.id.ib_splash_menu).setOnClickListener(this);
		rl_level1 = (RelativeLayout) findViewById(R.id.rl_splash_level1);
		rl_level2 = (RelativeLayout) findViewById(R.id.rl_splash_level2);
		rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
		
		
	}
	/**
	 * 重写onKeyDown方法a
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_MENU){
			if(AnimationTranslate.runningAnimationCount>0){
				return true;
			}
			//如果按下的是菜单按钮
			if(isLevel1Display){
				long delay=0;
				//隐藏三级菜单
				if(isLevel3Display){
					AnimationTranslate.rotateOutAnim(rl_level3, 0);
					isLevel3Display=false;
					delay+=200;
				}
				//隐藏二级菜单
				if(isLevel2Display){
					AnimationTranslate.rotateOutAnim(rl_level2, delay);
					isLevel2Display=false;
					delay+=20;
				}
				//隐藏一级菜单
				AnimationTranslate.rotateOutAnim(rl_level1, delay);
			}else{
				//一次进入
				AnimationTranslate.rotateInAnim(rl_level1, 0);
				AnimationTranslate.rotateInAnim(rl_level2, 200);
				AnimationTranslate.rotateInAnim(rl_level3, 400);
				isLevel2Display=true;
				isLevel3Display=true;
			}
			isLevel1Display=!isLevel1Display;
			return true;//消费当前的事件
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 设置点击事件
	 */
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.ib_splash_icon_home:
				if(isLevel2Display){
					long delay=0;
					//如果当前第三级菜单已经显示了，就先转出去
					if(isLevel3Display){
						AnimationTranslate.rotateOutAnim(rl_level3, 0);
						isLevel3Display=false;
						delay+=200;
					}
					//如果当前二级菜单已经显示了
					AnimationTranslate.rotateOutAnim(rl_level2, delay);
				}else{
					//如果二级菜单没有显示出来就先显示出来
					AnimationTranslate.rotateInAnim(rl_level2, 0);
				}
				//置反
				isLevel2Display=!isLevel2Display;
				break;
			case R.id.ib_splash_menu:
				if(isLevel3Display){
					//如果第三季菜单显示了就要转出去
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
