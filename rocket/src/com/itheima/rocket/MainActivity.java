package com.itheima.rocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.itheima.server.RocketService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void start(View view){
		Intent intent=new Intent(this,RocketService.class);
		startService(intent);
	}
	public void stop(View view){
		Intent intent=new Intent(this,RocketService.class);
		stopService(intent);
	}

}
