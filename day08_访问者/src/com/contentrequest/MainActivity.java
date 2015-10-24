package com.contentrequest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
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
	public void insert(View v)
	{
		//获取内容访问者的实例
		ContentResolver cr= getContentResolver();
		ContentValues values=new ContentValues();
		values.put("name", "lina");
		values.put("phone", "1234");
		cr.insert(Uri.parse("content://com.itheima.copro"), values);
		values.clear();
		values.put("name", "lili");
		values.put("phone", "789798");
		cr.insert(Uri.parse("content://com.itheima.copro"), values);
		
	}
	public void query(View v)
	{
		//获取内容访问者的实例
		ContentResolver cr=getContentResolver();
		Cursor query = cr.query(Uri.parse("content://com.itheima.copro"), null, null, null, null);
		while(query.moveToNext())
		{
			String id=query.getString(0);
			String name=query.getString(1);
			String phone=query.getString(2);
			
			System.out.println(id+":"+name+":"+phone);
		}
		
		
	}
	public void update(View v)
	{
		//获取内容的访问者实例
		ContentResolver cr=getContentResolver();
		ContentValues values=new ContentValues();
		values.put("name", "小云");
		values.put("phone", "1232");
		cr.update(Uri.parse("content://com.itheima.copro"), values, "_id=?", new String[]{"1"});
	}
	public void delete(View v)
	{
		
	}

}
