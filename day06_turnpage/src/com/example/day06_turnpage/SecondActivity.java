package com.example.day06_turnpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}
	public void click2(View v)
	{
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
	}

}
