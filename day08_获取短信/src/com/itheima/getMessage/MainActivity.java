package com.itheima.getMessage;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
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
	public void click1(View v)
	{
		//后去内容提供者的对象
		ContentResolver resolver = getContentResolver();
		Cursor query = resolver.query(Uri.parse("content://sms"), new String[]{"body","date","address","type"}, null, null, null);
		while(query.moveToNext())
		{
			//获取短信的主体
			String body=query.getString(0);
			long date=query.getLong(1);
			String address=query.getString(2);
			int type=query.getInt(3);
			System.out.println(address+":"+body+":"+date+":"+type);
			
			
		}
	}

}
