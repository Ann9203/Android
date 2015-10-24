package com.itheima.showfragement;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	Fragment01 f1;
	Fragment02 f2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent=new Intent();
		
	}

	public void click1(View v) {
		//获取管理器
		 f1=new Fragment01();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transcation = manager.beginTransaction();
		transcation.replace(R.id.fl, f1);
		//transcation.add(R.id.fl, f1);
		transcation.commit();
	}
	public void click2(View v) {
		//获取管理器
		 f2=new Fragment02();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transcation = manager.beginTransaction();
		transcation.replace(R.id.fl, f2);
		//transcation.add(R.id.fl, f2);
		transcation.commit();
	}
	public void click3(View v) {
		//获取管理器
		Fragment03 f3=new Fragment03();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transcation = manager.beginTransaction();
		transcation.replace(R.id.fl, f3);
		//transcation.add(R.id.fl, f3);
		transcation.commit();
	}
	public void click(View v){
		EditText et=(EditText)findViewById(R.id.et);
		String text=et.getText().toString().trim();
		f2.setText(text);
	}
	public void setText(String text)
	{
		EditText et=(EditText)findViewById(R.id.et);
		et.setTextColor(0xffff0000);
		et.setText(text);
	}
}
