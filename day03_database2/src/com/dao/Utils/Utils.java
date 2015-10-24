package com.dao.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.db.MySQList.MySQList;
import com.domain.UserBean.UserBean;

public class Utils {

	private MySQList mysqlist;
	public Utils(Context context)
	{
		 mysqlist=new MySQList(context);
	}
	
	
	public  boolean add(UserBean ub) {
		// TODO Auto-generated method stub
		boolean flag=false;
		SQLiteDatabase sq = mysqlist.getReadableDatabase();
		//定义一个ContentValues
		ContentValues values=new ContentValues();
		
		values.put("name", ub.name);
		values.put("phone", ub.phone);
		long insert = sq.insert("user", null, values);
		if(insert==1){
			
			flag=false;
		}	else{
			flag=true;
		}	
		sq.close();
		return flag;
		
	}

	public  int delete(String name) {
		// TODO Auto-generated method stub
		SQLiteDatabase sq = mysqlist.getReadableDatabase();
		int result = sq.delete("user", "name=?", new String[]{name});
		return result;
	}

	public  int update( UserBean ub) {
		// TODO Auto-generated method stub
		SQLiteDatabase sq = mysqlist.getReadableDatabase();
		ContentValues values=new ContentValues();
		values.put("name", ub.name);	
		int update = sq.update("user", values, "phone=?", new String[]{ub.phone});
		return update;
	}

	public  void query(String name) {
		// TODO Auto-generated method stub
		SQLiteDatabase sq = mysqlist.getReadableDatabase();
		
		Cursor cursor = sq.query("user", new String[]{"name","phone"}, "name=?", new String[]{name}, null, null, "phone");
		if(cursor!=null && cursor.getCount()>0)
		{
			while(cursor.moveToNext())
			{
				UserBean ub=new UserBean();
				ub.name=cursor.getString(0);
				ub.phone=cursor.getString(2);
				System.out.println(ub.name+"："+ub.phone);
			}

		}
	}

}
