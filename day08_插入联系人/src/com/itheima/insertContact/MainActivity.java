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
		//����������µĶ���
		//������ʾ��id�ҳ�����Ȼ�����µ�id+1����Ҫ�������ݵ�idҲ��Contact_id��ֵ
		Cursor query = resolver.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"_id"}, null, null, null);
		int count=1;
		//�����ֱ��ת�Ƶ����һ�����ݿ϶������µ�����
		if(query.moveToLast())
		{
			int _id=query.getInt(0);
			 count=++_id;
		}
		//������ϵ��id��raw_contacts����
		ContentValues values=new ContentValues();
		values.put("contact_id", count);
		//��ʾ����id
		resolver.insert(Uri.parse("content://com.android.contacts/raw_contacts"), values);
		values.clear();
		values.put("data1", "С��");
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
