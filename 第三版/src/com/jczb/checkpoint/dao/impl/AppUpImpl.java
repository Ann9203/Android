package com.jczb.checkpoint.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.jczb.checkpoint.dao.IAppUp;
import com.jczb.checkpoint.db.DBHelper;
import com.jczb.checkpoint.model.AppUp;

public class AppUpImpl implements IAppUp {

	private DBHelper dbHelper;

	/**
	 * 构造方法
	 * 
	 * @param context
	 */
	public AppUpImpl(Context context) {
		dbHelper = new DBHelper(context);
	}

	@Override
	public void insert(AppUp appUp) {

		ContentValues values = new ContentValues();
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.NAME, appUp.getName());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.BEGINDATE,
				appUp.getBeginDate());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.EPCCODE,
				appUp.getEPRCode());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.TIDCODE,
				appUp.getTIDCode());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.MEKUANGINAME,
				appUp.getMeKuangiName());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.ANJIANCODE,
				appUp.getAnJianCode());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.JOBNAME,
				appUp.getJobName());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.CHECKDATE,
				appUp.getCheckDate());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.EXCEREMARK,
				appUp.getExceRemark());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.ANBIAOCODE,
				appUp.getAnBiaoCode());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.CHECKRESULT,
				appUp.getCheckResult());
		values.put(com.jczb.checkpoint.db.AppUp.FIELDS.STATE, appUp.getState());
		dbHelper.insert(com.jczb.checkpoint.db.AppUp.TABLENAME, values);
		/*
		 * // TODO Auto-generated method stub String sql =
		 * String.format(com.jczb
		 * .checkpoint.db.AppUp.OPERATION.INSERT,appUp.getName(),
		 * appUp.getBeginDate(),appUp.getEPRCode(),
		 * appUp.getTIDCode(),appUp.getMeKuangiName
		 * (),appUp.getAnJianCode(),appUp.getJobName(),
		 * appUp.getCheckDate(),appUp.getExceRemark(),appUp.getAnBiaoCode());
		 * dbHelper.execSQL(sql);
		 */
	}

	@Override
	public List<AppUp> getAppDownCongByCondition(String condition) {
		String sql = String.format(
				com.jczb.checkpoint.db.AppUp.OPERATION.QUERY, condition);
		Cursor cursor = dbHelper.rawQuery(sql);

		List<com.jczb.checkpoint.model.AppUp> list = new ArrayList<com.jczb.checkpoint.model.AppUp>();
		try {
			// 给用户实体各个属性赋值
			while (cursor.moveToNext()) {
				com.jczb.checkpoint.model.AppUp appUp = new com.jczb.checkpoint.model.AppUp();
				appUp.setAnBiaoCode(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.ANBIAOCODE)));
				appUp.setAnJianCode(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.ANJIANCODE)));
				appUp.setBeginDate(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.BEGINDATE)));
				appUp.setCheckDate(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.CHECKDATE)));
				appUp.setMeKuangiName(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.MEKUANGINAME)));
				appUp.setEPRCode(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.EPCCODE)));
				appUp.setExceRemark(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.EXCEREMARK)));
				appUp.setID(Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.ID))));
				appUp.setCheckResult(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.CHECKRESULT)));
				appUp.setState(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.STATE)));
				appUp.setTIDCode(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.TIDCODE)));
				appUp.setJobName(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.JOBNAME)));
				appUp.setName(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.NAME)));
				list.add(appUp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbHelper.close();
		}

		return list;
	}

	/**
	 * 根据条件获取上传记录
	 * 
	 * @param condition
	 * @return
	 */
	public List<AppUp> getUpRecord(String condition) {
		String sql = String.format(
				com.jczb.checkpoint.db.AppUp.OPERATION.QUERY, condition);
		Cursor cursor = dbHelper.rawQuery(sql);

		List<com.jczb.checkpoint.model.AppUp> list = new ArrayList<com.jczb.checkpoint.model.AppUp>();
		try {
			// 给用户实体各个属性赋值
			while (cursor.moveToNext()) {
				com.jczb.checkpoint.model.AppUp appUp = new com.jczb.checkpoint.model.AppUp();
				appUp.setAnBiaoCode(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.ANBIAOCODE)));
				appUp.setAnJianCode(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.ANJIANCODE)));
				appUp.setBeginDate(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.BEGINDATE)));
				appUp.setCheckDate(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.CHECKDATE)));
				appUp.setMeKuangiName(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.MEKUANGINAME)));
				appUp.setEPRCode(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.EPCCODE)));
				appUp.setExceRemark(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.EXCEREMARK)));
				appUp.setID(Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.ID))));
				appUp.setCheckResult(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.CHECKRESULT)));
				appUp.setState(cursor.getString(cursor
						.getColumnIndex(com.jczb.checkpoint.db.AppUp.FIELDS.STATE)));
				list.add(appUp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbHelper.close();
		}
		return list;
	}

}
