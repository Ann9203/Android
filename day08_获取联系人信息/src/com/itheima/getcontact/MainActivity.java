package com.itheima.getcontact;

import com.itheima.domain.Contact;

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
	public void get(View v)
	{
		//获取内容接受者
		ContentResolver resolver = getContentResolver();
		//获取联系人
		Cursor query = resolver.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"contact_id"}, null, null, null);
		while(query.moveToNext())
		{
			Contact contact = new Contact();
			String contact_id=query.getString(0);
			//根据联系人id来查询具体的内容
			Cursor query2 = resolver.query(Uri.parse("content://com.android.contacts/data"), new String[]{"data1","mimetype"}, "raw_contact_id= ? ", new String[]{contact_id}, null);
			while(query2.moveToNext())
			{
				String data1=query2.getString(0);
				String mimetype=query2.getString(1);
				if("vnd.android.cursor.item/name".equals(mimetype))
				{
					contact.setName(data1);
				}else if(("vnd.android.cursor.item/email_v2").equals(mimetype))
				{
					contact.setEmail(data1);
					
				}else if(("vnd.android.cursor.item/phone_v2").equals(mimetype))
				{
					contact.setPhone(data1);
				}
			}
			System.out.println(contact.toString());
		}
	}

}
