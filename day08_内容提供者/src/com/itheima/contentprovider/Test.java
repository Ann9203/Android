package com.itheima.contentprovider;

import com.itheima.db.MySQLHelper;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {
	public void test()
	{
		MySQLHelper msh=new MySQLHelper(getContext());
		msh.getReadableDatabase();
	}
}
