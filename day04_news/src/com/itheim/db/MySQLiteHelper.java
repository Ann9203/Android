package com.itheim.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public MySQLiteHelper(Context context) {
		super(context, "itheimanews", null,1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
//		db.execSQL("create table news (_id integer primary key , title varchar(200),des varchar(200),time varchar(100),news_url varchar(200)," +
//				"icon_url varchar(200),comment integer ,type integer )");

		db.execSQL("create table news (_id integer primary key , title varchar(200),des varchar(200),time varchar(100),news_url varchar(200),"+
		
				"icon_url varchar(200),comment integer,type integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
