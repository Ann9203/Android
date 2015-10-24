package com.itheima.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.itheima.db.WatchDogSQLHelper;

public class WatchDogDAO {

	private WatchDogSQLHelper watchdaohelper;
	private Context context;
	public WatchDogDAO(Context context){
		this.watchdaohelper=new WatchDogSQLHelper(context);
		this.context=context;
	}
	/**
	 * 添加应用程序的包名
	 */
	public void addPackageName(String packageName){
		SQLiteDatabase writableDatabase = watchdaohelper.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("packagename", packageName);
		writableDatabase.insert(watchdaohelper.DB_NAME, null, values);
		//通过内容观察者，观察数据的变化
		ContentResolver contentResolver=context.getContentResolver();
		//自己定义一个uri
		Uri uri=Uri.parse("content://com.itheima.mobileSafe.lock.change");
		//观察者进行观察
		contentResolver.notifyChange(uri, null);
		//关闭数据库
		writableDatabase.close();
	}
	/**
	 * 查询数据库中是否包含着包名
	 */
	public boolean queryPackageName(String packageName){
		boolean flag = false;
		
		SQLiteDatabase readableDatabase = watchdaohelper.getReadableDatabase();
		Cursor cursor = readableDatabase.query(watchdaohelper.DB_NAME, null, "packagename=?", new String[]{packageName}, null, null, null);
		if(cursor.moveToNext()){
			flag=true;
		}
		cursor.close();
		readableDatabase.close();
		return flag;
	}
	/**
	 * 查询所有的包名
	 */
	public List<String> queryAllPackageName(){
		List<String>list=new ArrayList<String>();
		SQLiteDatabase readableDatabase = watchdaohelper.getReadableDatabase();
		Cursor cursor = readableDatabase.query(watchdaohelper.DB_NAME, new String[]{"packagename"}, null, null, null, null, null);
		while(cursor.moveToNext()){
			String packageName=cursor.getString(0);
			list.add(packageName);
		}
		cursor.close();
		readableDatabase.close();
		return list;
	}
	/**
	 * 删除一个包名
	 */
	public void deletePackageName(String packageName){
		SQLiteDatabase writableDatabase = watchdaohelper.getWritableDatabase();
		writableDatabase.delete(watchdaohelper.DB_NAME, "packagename=?", new String[]{packageName});
		//观察者
		ContentResolver contentResolver=context.getContentResolver();
		//自己定义一个uri
		Uri uri=Uri.parse("content://com.itheima.mobileSafe.lock.change");
		contentResolver.notifyChange(uri, null);
		writableDatabase.close();
		
	}
	
}
