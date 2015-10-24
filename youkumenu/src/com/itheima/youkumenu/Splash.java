package com.itheima.youkumenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class Splash extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	}

	/**
	 * 初始化控件
	 */
	private void  initView(){
		//添加点击事件
		findViewById(R.id.ib_splash_icon_home).setOnClickListener(this);
		findViewById(R.id.ib_splash_menu).setOnClickListener(this);
		// 初始化控件
		RelativeLayout rl_level1=(RelativeLayout) findViewById(R.id.rl_splash_level1);
		RelativeLayout rl_level2=(RelativeLayout) findViewById(R.id.rl_splash_level2);
		RelativeLayout rl_level3=findViewById(R.id.rl_level3);
	}
}
