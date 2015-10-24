package com.itheima.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.bean.BlackNum;
import com.itheima.db.MySQLHelper;

public class BlackNumSQL {

	//设置存储的形式
	public  final String CALL="0";
	public  final String MSG="1";
	public final String ALL="2";
	private MySQLHelper mysql;

	public BlackNumSQL(Context context){
		mysql = new MySQLHelper(context);
	}
	/**
	 * 添加的黑名单
	 */
	public void addBlackNum(String phone,String style){
		SQLiteDatabase sq = mysql.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("phone", phone);
		values.put("style", style);
		 sq.insert(mysql.DB_NAME, null, values);
		 sq.close();
	}
	/**
	 * 查询黑名单，查询所有的
	 */
	public List<BlackNum> queryBlackNum(){
		List<BlackNum> list=new ArrayList<BlackNum>();
		SQLiteDatabase sq = mysql.getReadableDatabase();
		Cursor cursor = sq.query(mysql.DB_NAME, new String[]{"phone","style"}, null, null, null,null,"_id desc");
		while(cursor.moveToNext()){			
			String phone=cursor.getString(0);
			String style=cursor.getString(1);
			BlackNum blacknum =new BlackNum(phone,style);
			list.add(blacknum);		
		}
		sq.close();
		return list;
		
	}
	/**
	 * 根据电话号码查询他的状态
	 */
	public String queryBlackNumStyle(String phone){
		String style=null;
		SQLiteDatabase sq = mysql.getReadableDatabase();
		Cursor cursor = sq.query(mysql.DB_NAME, new String[]{"style"}, "phone=?", new String[]{phone}, null, null, null);
		if(cursor.moveToNext()){
			style=cursor.getString(0);
		}
		return style;
		
		
	}
	
	/**
	 * 修改状态
	 */
	public void updateBlackNumStyle(String phone,String style){
		SQLiteDatabase sq = mysql.getWritableDatabase();
		ContentValues values =new ContentValues();
		values.put("style", style);
		sq.update(mysql.DB_NAME, values, "phone=?", new String[]{phone});
		
	}
	/**
	 * 删除数据
	 */
	public void deleteBlackNum(String phone){
		SQLiteDatabase sq = mysql.getWritableDatabase();
		sq.delete(mysql.DB_NAME, "phone=?", new String[]{phone});
	}
	/**
	 * 查询部分数据
	 * @return
	 */
	public List<BlackNum> queryPartData(int limit,int offset){
		List<BlackNum> list=new ArrayList<BlackNum>();
		SQLiteDatabase sq = mysql.getReadableDatabase();
		Cursor cursor = sq.rawQuery("select phone,style  from info order  by  _id  desc  limit ? offset ?; ", new String[]{String.valueOf(limit),String.valueOf(offset)});
		while(cursor.moveToNext()){
			BlackNum blackNum=new BlackNum(cursor.getString(0), cursor.getString(1));
			list.add(blackNum);
		}
		System.out.println(list.size());
		return list;
	}

}
