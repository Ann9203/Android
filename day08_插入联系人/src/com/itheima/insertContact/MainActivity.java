package com.itheima.insertContact;

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
		ContentResolver resolver = getContentResolver();
		//插入的是最新的短信
		//所以显示吧id找出来，然后最新的id+1就是要插入数据的id也是Contact_id的值
		Cursor query = resolver.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"_id"}, null, null, null);
		int count=1;
		//结果集直接转移到最后一条数据肯定是最新的数据
		if(query.moveToLast())
		{
			int _id=query.getInt(0);
			 count=++_id;
		}
		//插入联系人id至raw_contacts表中
		ContentValues values=new ContentValues();
		values.put("contact_id", count);
		//显示加入id
		resolver.insert(Uri.parse("content://com.android.contacts/raw_contacts"), values);
		values.clear();
		values.put("data1", "小二");
		values.put("mimetype", "vnd.android.cursor.item/name");
		values.put("raw_contact_id", count);
		resolver.insert(Uri.parse("content://com.android.contacts/data"), values);
		values.clear();
		values.put("data1", 110);
		values.put("mimetype", "vnd.android.cursor.item/phone_v2");
		values.put("raw_contact_id", count);
		resolver.insert(Uri.parse("content://com.android.contacts/data"), values);
		
		
	}

}
