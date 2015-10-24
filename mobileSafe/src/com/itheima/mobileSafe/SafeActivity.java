package com.itheima.mobileSafe;

import android.content.Intent;
import android.os.Bundle;

import com.itheima.Utils.NetPreMethod;

public class SafeActivity extends NetPreMethod {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setsafe);
	}

	@Override
	public void preMethod() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextMethod() {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this,SetSafe2Activity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.set_enter_nextanim, R.anim.set_exit_nextanim);
	}

}
