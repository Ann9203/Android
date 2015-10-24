package com.dao.day03_database;

import com.domain.UserBean.UserBean;

import sqlitedb.db;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Utils {

//	public Utils(Context context)
//	{
//		db database=new db(context);
//	}
	public static void add(Context context,UserBean user) {
		// TODO Auto-generated method stub
		db database=new db(context);
		SQLiteDatabase sq= database.getReadableDatabase();
		
		sq.execSQL("insert into user(name,phone)values(?,?)", new Object[]{user.name,user.phone});
		sq.close();
	}
	public static void update(Context context,UserBean user){
		db database=new db(context);
		SQLiteDatabase sq=database.getReadableDatabase();
		sq.execSQL("update user set name=? where phone=?", new Object[]{user.name,user.phone});
		sq.close();
		
	}
	public static void delete(Context context,String name)
	{
		db database =new db(context);
		SQLiteDatabase sq=database.getReadableDatabase();
		sq.execSQL("delete from user where name=?",new Object[]{name});
	}
	public static void query(Context context,String name)
	{
		db database=new db(context);
		SQLiteDatabase sq=database.getReadableDatabase();
		Cursor cur=	sq.rawQuery("select name,phone from user where name=?", new String[]{name});
		if(cur!=null && cur.getCount()>0)
		{
			while(cur.moveToNext())
			{
				String name1=cur.getString(0);
				String phone=cur.getString(1);
				System.out.println(name1+":"+phone);
			}
		}
	}
	

}
