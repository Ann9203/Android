package com.itheima.dao;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryLocationDAO {

	private String num;
	private Context context;
	

	
	/**
	 * 查询归属地的数据库层
	 */
	public static  String queryDao(String num,Context context){
		String location="";
		File file=new File(context.getFilesDir(),"address.db");
		//打开数据库
		SQLiteDatabase sq=SQLiteDatabase.openDatabase(file.getAbsolutePath(), null,SQLiteDatabase.OPEN_READONLY);
		if(num.matches("^1[34578]\\d{9}$")){
			//"select location from data2 where id=(select outkey from data1 where id=?)", new String[]{num.substring(0, 7)}
			Cursor cursor = sq.rawQuery("select location from data2 where id=(select outkey from data1 where id= ?)", new String[]{num.substring(0, 7)});
			
			
			if(cursor.moveToNext()){
				location=cursor.getString(0);
				return location;
			}
		}else {
			//如果电话号码不是正常的
			switch(num.length()){
				case 3:
					location="特殊电话";
					break;
				case 4:
					location="模拟器电话";
					break;
				case 5:
					location="客服电话";
				case 7:
				case 8:
					location="座机电话";
					break;
				default:
					//这是区号的电话，要查找出区号来
					if(num.length()>=10 & num.startsWith("0")){
						Cursor cursor2 = sq.rawQuery("select location from data2 where area=? or area=?", new String[]{num.substring(1, 3),num.substring(1, 4)});
						if(cursor2.moveToNext()){
							 location=cursor2.getString(0);
							location=location.substring(0, 2);
							return location;
						}
					}
			}
			
		}
		return location;
	}
}
