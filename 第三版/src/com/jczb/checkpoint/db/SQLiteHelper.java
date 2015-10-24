package com.jczb.checkpoint.db;


import com.jczb.checkpoint.common.CommonUtil;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 用于创建表数据库、表和升级
 * @author wlc
 * @date 2015-3-23
 */
public class SQLiteHelper extends SQLiteOpenHelper {

	/**
	 * 构造函数，创建数据库
	 * 
	 * @param context
	 */
	public SQLiteHelper(Context context) {
		super(context, DB.DATABASENAME, null, DB.DATABASE_VERSION);
	}

	/**
	 * 创建表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			/*db.execSQL(T_User.OPERATION.CREATETABLE);
			db.execSQL(DB.TABLES.EMAIL.SQL.CREATE);
			db.execSQL(DB.TABLES.GROUP.SQL.CREATE);
			db.execSQL(DB.TABLES.IM.SQL.CREATE);
			db.execSQL(DB.TABLES.TEL.SQL.CREATE);*/
			//CommonUtil.Log("sqy", "SQLiteHelper", "onCreate", 'i');
			
			//execSQL为执行非查询语句或者没有返回值的语句
			db.execSQL(Users.OPERATION.CREATETABLE);
			db.execSQL(AnBiaoRelation.OPERATION.CREATETABLE);
			db.execSQL(AppDown.OPERATION.CREATETABLE);
		    db.execSQL(PhotoFile.OPERATION.CREATETABLE);
			db.execSQL(AppDownCong.OPERATION.CREATETABLE);
			db.execSQL(AppUp.OPERATION.CREATETABLE);
			CommonUtil.Log("sqy", "SQLiteHelper", "onCreate", 'i');
			
		} catch (SQLException sqlEx) {
			throw sqlEx;
		}
	}

	/**
	 * 数据库升级
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*db.execSQL(DB.TABLES.CONTACT.SQL.DROPTABLE);
		db.execSQL(DB.TABLES.EMAIL.SQL.DROPTABLE);
		db.execSQL(DB.TABLES.GROUP.SQL.DROPTABLE);
		db.execSQL(DB.TABLES.IM.SQL.DROPTABLE);
		db.execSQL(DB.TABLES.TEL.SQL.DROPTABLE);
		onCreate(db);*/
		db.execSQL(Users.OPERATION.DROPTABLE);
		db.execSQL(AnBiaoRelation.OPERATION.DROPTABLE);
		db.execSQL(AppDown.OPERATION.DROPTABLE);
		db.execSQL(PhotoFile.OPERATION.DROPTABLE);
		db.execSQL(AppDownCong.OPERATION.DROPTABLE);
		db.execSQL(AppUp.OPERATION.DROPTABLE);
		onCreate(db);
		CommonUtil.Log("sqy", "SQLiteHelper", "onUpgrade", 'i');
	}

}
