package com.example.mobilesafe_2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.adapter.MyGridViewAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class HomeActivity extends Activity {

	@ViewInject(R.id.gv_home_application)
	private GridView gv_home_application;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ViewUtils.inject(this);
		MyGridViewAdapter adapter=new MyGridViewAdapter(getApplicationContext());
		gv_home_application.setAdapter(adapter);
		
	}
	
}
