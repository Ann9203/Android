package com.jczb.checkpoint.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import com.jczb.checkpoint.R.string;
import com.jczb.checkpoint.dao.IAppDownCong;
import com.jczb.checkpoint.db.DBHelper;
import com.jczb.checkpoint.model.AppDownCong;

public class AppDownCongImpl implements IAppDownCong {

	private DBHelper dbHelper;

	/**
	 * 构造方法
	 * 
	 * @param context
	 */
	public AppDownCongImpl(Context context) {
		dbHelper = new DBHelper(context);
	}

	@Override
	public void delete(int dwonCongId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String condiction) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<com.jczb.checkpoint.model.AppDownCong> getAppDownCongByCondition(
			String condition) {
		String sql = String.format(
				com.jczb.checkpoint.db.AppDownCong.OPERATION.QUERY, condition);
		Cursor cursor = dbHelper.rawQuery(sql);

		List<com.jczb.checkpoint.model.AppDownCong> list = new ArrayList<com.jczb.checkpoint.model.AppDownCong>();
		try {
			// 给用户实体各个属性赋值
			while (cursor.moveToNext()) {
				com.jczb.checkpoint.model.AppDownCong appDownCong = new com.jczb.checkpoint.model.AppDownCong();
				appDownCong
						.setEPCCode(cursor.getString(cursor
								.getColumnIndex(com.jczb.checkpoint.db.AppDownCong.FIELDS.EPCCODE)));
				appDownCong
						.setTIDCode(cursor.getString(cursor
								.getColumnIndex(com.jczb.checkpoint.db.AppDownCong.FIELDS.TIDCODE)));
				appDownCong
						.setPutEnterprise(cursor.getString(cursor
								.getColumnIndex(com.jczb.checkpoint.db.AppDownCong.FIELDS.PUTENTERPRISE)));
				appDownCong
						.setPutProduceName(cursor.getString(cursor
								.getColumnIndex(com.jczb.checkpoint.db.AppDownCong.FIELDS.PUTPRODUCTNAME)));
				appDownCong
						.setPutProdeceModel(cursor.getString(cursor
								.getColumnIndex(com.jczb.checkpoint.db.AppDownCong.FIELDS.PUTPRODUCTMODEL)));
				appDownCong
						.setAnBiaoCode(cursor.getString(cursor
								.getColumnIndex(com.jczb.checkpoint.db.AppDownCong.FIELDS.ANBIAOCODE)));
				list.add(appDownCong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbHelper.close();
		}

		return list;

	}

	@Override
	public void insert(AppDownCong downCong) {

		/**
		 * 给底层Insert语句传参，顺序不能反
		 */
		String sql = String.format(
				com.jczb.checkpoint.db.AppDownCong.OPERATION.INSERT,
				downCong.getName(), downCong.getBeginDate(),
				downCong.getEPCCode(), downCong.getTIDCode(),
				downCong.getPutDate(), downCong.getPutEnterprise(),
				downCong.getPutProduceName(),downCong.getPutProdeceModel(), 
				downCong.getAnBiaoCode(),downCong.getState());
		dbHelper.execSQL(sql);
	}

	@Override
	public void update(com.jczb.checkpoint.model.AppDownCong dwonCong) {
		// TODO Auto-generated method stub

	}

}
