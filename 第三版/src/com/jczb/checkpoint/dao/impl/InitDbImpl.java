package com.jczb.checkpoint.dao.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jczb.checkpoint.dao.IInitDb;
import com.jczb.checkpoint.db.DBHelper;
import com.jczb.checkpoint.db.SQLiteHelper;

/**
 * 初始化数据库、表、字段实现类
 * @author wlc
 * @date 2015-3-24
 */
public class InitDbImpl implements IInitDb{

	private DBHelper dbHelper;
	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase db;
	
	/**
	 * 构造方法
	 * @param context
	 */
	public InitDbImpl(Context context){
		dbHelper = new DBHelper(context);
		sqLiteHelper = new SQLiteHelper(context);
		initDb();
	}
		
	@Override
	public void initDb() {
		//创建数据库
		db = sqLiteHelper.getWritableDatabase();
		//创建数据表
		sqLiteHelper.onCreate(db);
	}
	
	
}
