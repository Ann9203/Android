package com.itheima.dao;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryVirus {/**
	 * 查询是否存在着病毒
	 */
	public static  boolean queryDao(String md5,Context context){
		boolean result=false;
		File file=new File(context.getFilesDir(),"antivirus.db");
		//打开数据库
		SQLiteDatabase sq=SQLiteDatabase.openDatabase(file.getAbsolutePath(), null,SQLiteDatabase.OPEN_READONLY);
		Cursor cursor = sq.query("datable", null, "md5=?", new String[]{md5}, null, null, null);
		if(cursor.moveToNext()){
			result=true;
		}	
		
		return result;
	}
}
