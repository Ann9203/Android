package com.jczb.checkpoint.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.jczb.checkpoint.dao.IAppAnBiaoRelation;
import com.jczb.checkpoint.db.AnBiaoRelation;
import com.jczb.checkpoint.db.DBHelper;
import com.jczb.checkpoint.db.PhotoFile.FIELDS;
import com.jczb.checkpoint.model.AppAnBiaoRelation;

/**
 * 安标关联实现类
 * 
 * @author wlc
 * @date 2015-4-1
 */
public class AppAnBiaoRelationImpl implements IAppAnBiaoRelation {

	private DBHelper dbHelper;
	private AnBiaoRelation anBiaoRelation;
	private List<AppAnBiaoRelation> list;

	public AppAnBiaoRelationImpl(Context context) {
		dbHelper = new DBHelper(context);
	}

	@Override
	public void insert(AppAnBiaoRelation anBiaoRelation) {

		String sql = String.format(AnBiaoRelation.OPERATION.INSERT,
				anBiaoRelation.getName(), anBiaoRelation.getBeginDate(),
				anBiaoRelation.getMainAnBiaoID(),
				anBiaoRelation.getViceAnBiaoID());
		dbHelper.execSQL(sql);
	}

	@Override
	public void delete(String condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AppAnBiaoRelation anBiaoRelation) {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取安标关联表实体集合
	 */
	@Override
	public List<AppAnBiaoRelation> getRelationInfoByCondition(String condition) {

		String sql = String.format(AnBiaoRelation.OPERATION.QUERY, condition);
		list = new ArrayList<AppAnBiaoRelation>();
		try {
			Cursor cursor = dbHelper.rawQuery(sql);
			
			//获取记录并赋值给实体
			while(cursor.moveToNext()){
				AppAnBiaoRelation relation = new AppAnBiaoRelation();
				relation.setViceAnBiaoID(cursor.getColumnName(cursor.getColumnIndex(AnBiaoRelation.FIELDS.VICEANBIAOID)));
				list.add(relation);
			}
		} catch (Exception e) {
			Log.e("get getRelationInfoByCondition failed:","e="+e.getMessage());
		}
		
		return list;
	}

	/**
	 * 获取主安标证书ID
	 */
	@Override
	public int getAnbiaoId(String condition) {
		anBiaoRelation = new AnBiaoRelation();
		String sql = String.format(AnBiaoRelation.OPERATION.QUERY, condition);

		int anbiaoId = 0;

		try {
			Cursor cursor = dbHelper.rawQuery(sql);
			while (cursor.moveToNext()) {
				anbiaoId = cursor.getColumnIndex(AnBiaoRelation.FIELDS.ID);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return anbiaoId;
	}

}
