package com.itheima.insertMessgae;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void insert(View v)
	{
		ContentResolver resolver = getContentResolver();
		ContentValues values=new ContentValues();
		/*
		 * values.put("address", 95555);
    	    	values.put("body", "您尾号为XXXX的招行储蓄卡收到转账1,000,000");
    	    	values.put("type", 1);
    	    	values.put("date", System.currentTimeMillis());
		 * */
		values.put("address", 10086);
		values.put("body", "从10反1000");
		values.put("type", 1);
		values.put("date", System.currentTimeMillis());
		resolver.insert(Uri.parse("content://sms"), values);
	}

}
