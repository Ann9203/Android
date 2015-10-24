package com.itheima.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_second);
		TextView tv=(TextView)findViewById(R.id.tv_result);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String males=bundle.getString("males");
		String females=bundle.getString("females");
		
		
//		String males=intent.getStringExtra("males");
//		String females=intent.getStringExtra("females");
		String text=males+females;
		byte[] by=text.getBytes();
		int total=0;
		for(int i=0;i<by.length;i++)
		{
			total+=by[i];
		}
		
		int result=Math.abs(total)%101;
	
		if(result<60)
		{
			tv.setText(males+females+"的姻缘值为："+result+"不妙");
		}else
		{
			tv.setText(males+females+"的姻缘值为："+result+"此乃天作之合");
		}
		
		
		
		
		
	}
	
}
