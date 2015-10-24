package com.itheima.mobileSafe.test;

import java.util.HashMap;
import java.util.Map;

import com.itheima.db.MySQLHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class SQLTest extends AndroidTestCase {

	private MySQLHelper mysql;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		mysql = new MySQLHelper(getContext());
		
	}
	public void insertTest(){
		//MySQLHelper mysql=new MySQLHelper(getContext());
		SQLiteDatabase sq = mysql.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("phone", "110");
		values.put("style", "1");
		sq.insert(mysql.DB_NAME, null, values);
		
	}
	public void queryTest(){
		Map<String,String> blacknum=new HashMap<String,String>();
		MySQLHelper mysql=new MySQLHelper(getContext());
		SQLiteDatabase sq = mysql.getReadableDatabase();
		Cursor cursor = sq.query(mysql.DB_NAME, new String[]{"phone","style"}, null, null, null,null,null);
		while(cursor.moveToNext()){
			String phone=cursor.getString(0);
			String style=cursor.getString(1);
			blacknum.put("phone", phone);
			blacknum.put("style", style);
			
		}
		System.out.println(cursor.toString());
		System.out.println(blacknum.toString());
		assertEquals(1, 1);
	}
	
	public void queryStyle(){
		MySQLHelper mysql=new MySQLHelper(getContext());
		String style=null;
		SQLiteDatabase sq = mysql.getReadableDatabase();
		Cursor cursor = sq.query(mysql.DB_NAME, new String[]{"style"}, "phone=?", new String[]{"110"}, null, null, null);
		if(cursor.moveToNext()){
			style=cursor.getString(0);
		}
		System.out.println(style);
	}
	public void updateStyle(){
		MySQLHelper mysql=new MySQLHelper(getContext());
		SQLiteDatabase sq = mysql.getWritableDatabase();
		ContentValues values =new ContentValues();
		values.put("style", "2");
		sq.update(mysql.DB_NAME, values, "phone=?", new String[]{"110"});
	}
	public void deletePhone(){
		MySQLHelper mysql=new MySQLHelper(getContext());
		SQLiteDatabase sq = mysql.getWritableDatabase();
		sq.delete(mysql.DB_NAME, "phone=?", new String[]{"110"});
	}
}
