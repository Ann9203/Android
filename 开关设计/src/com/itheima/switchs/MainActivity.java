package com.itheima.switchs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.itheima.ui.Toggle;
import com.itheima.ui.Toggle.OnSwitchStateUpdateListener;

public class MainActivity extends Activity {

	private Toggle toggle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toggle=(Toggle) findViewById(R.id.mytoggle);
//		//设置图片
//		toggle.setBackgroundImage(R.drawable.switch_background);
//		//设置滑动的图片
//		toggle.setSlideImage(R.drawable.slide_button);
//		//设置一个要滑动的状态
//		toggle.setState(false);
		toggle.setOnSwitchStateUpdateListener(new OnSwitchStateUpdateListener() {
			
			@Override
			public void setState(boolean state) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "state:"+state, 0).show();
			}
		});
		
	}



}
