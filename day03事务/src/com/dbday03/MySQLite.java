

package com.dbday03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLite extends SQLiteOpenHelper {

	public MySQLite(Context context) {
		super(context, "bank.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//db.execSQL("create table account (_id integer primary key autoincrement,name varchar(20),money varchar(20))");
		db.execSQL("create table account (_id integer primary key autoincrement , name varchar(20),money varchar(20))");
		db.execSQL("insert into account(name,money)values('小李','20000')");
		db.execSQL("insert into account(name,money)values('小王','50000')");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
