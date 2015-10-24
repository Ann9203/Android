package com.itheima.contentprovider;

import com.itheima.db.MySQLHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class CoPro extends ContentProvider {

	
	private SQLiteDatabase db;
	
	//创建uri匹配器，判断uri末尾携带的路径是什么
	UriMatcher um=new UriMatcher(UriMatcher.NO_MATCH);
	{
		//添加匹配规则
		um.addURI("com.itheima.copro", "person", 1);
		um.addURI("com.itheima.copro", "writer", 2);
		um.addURI("com.itheima.copro", "person/#", 3);
		
	};
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		MySQLHelper msh=new MySQLHelper(getContext());
		db=msh.getReadableDatabase();
		System.out.println("我被创建了");
		return false;
	}

	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		if(um.match(uri)==1)
		{
			db.insert("person", null, values);
			//注册一个观察值，只要一发生改变就发出警告
			getContext().getContentResolver().notifyChange(uri, null);
			
		}else if(um.match(uri)==2)
		{
			db.insert("writer", null, values);
			getContext().getContentResolver().notifyChange(uri, null);
		}else if(um.match(uri)==3)
		{
			//			//获取路径后面携带的数字
			//long id = ContentUris.parseId(uri);
			//获取路径后边携带的数据
			//long id=ContentUris.parseId(uri);
			db.insert("person", null, values);
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return uri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		db.delete("person", null, null);
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		if(um.match(uri)==1)
		{
			
			
			db.update("person", values, selection, selectionArgs);
		}else if(um.match(uri)==2)
		{
			db.update("writer", values, selection, selectionArgs);
		}else if(um.match(uri)==3)
		{
			//后去uri后边的数值
			long id=ContentUris.parseId(uri);
			db.update("person", values, "_id", new String[]{String.valueOf(id)});
		}
		return 0;
	}
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		Cursor query = db.query("person", projection, null, null, null, null, null, null);
		return query;
	}
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
