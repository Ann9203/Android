package com.jczb.checkpoint.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.jczb.checkpoint.R.string;
import com.jczb.checkpoint.dao.IAppDown;
import com.jczb.checkpoint.db.AnBiaoRelation;
import com.jczb.checkpoint.db.DBHelper;
import com.jczb.checkpoint.db.Users;
import com.jczb.checkpoint.db.AppDown.FIELDS;
import com.jczb.checkpoint.model.AppDown;
import com.jczb.checkpoint.model.User;

/**
 * 安标证书信息下载实现类
 * 
 * @author wlc
 * @date 2015-3-31
 */
public class AppDownImpl implements IAppDown {

	private DBHelper dbHelper;

	public AppDownImpl(Context context) {
		dbHelper = new DBHelper(context);
	}

	@Override
	public void insert(AppDown anbiaoInfo) {
		String sql = String.format(
				com.jczb.checkpoint.db.AppDown.OPERATION.INSERT,
				anbiaoInfo.getBegin_date(), anbiaoInfo.getAnbiao_code(),
				anbiaoInfo.getProvide_date(),anbiaoInfo.getTerm_validity(),
				anbiaoInfo.getHolder_person(),anbiaoInfo.getRegistered_address(),
				anbiaoInfo.getProduction_unit_id(),anbiaoInfo.getProduction_address(),
				anbiaoInfo.getProduct_name(),anbiaoInfo.getProduct_model(),
				anbiaoInfo.getStandard(),anbiaoInfo.getScope_application(),
				anbiaoInfo.getRemark(),anbiaoInfo.getState(),anbiaoInfo.getName());
		dbHelper.execSQL(sql);

	}

	@Override
	public void delete(String condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AppDown anbiaoInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * 根据条件获取相关的安标信息
	 */
	public List<AppDown> getAnbiaoInfoByCondition(String condition) {
		
		String sql = String.format(com.jczb.checkpoint.db.AppDown.OPERATION.QUERY, condition);
		
		List<AppDown> list = new ArrayList<AppDown>();
		try {
			Cursor cursor = dbHelper.rawQuery(sql);
			AppDown anbiaoInfo = new AppDown();
			// 给用户实体各个属性赋值
			while (cursor.moveToNext()) {	
				
				anbiaoInfo.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.ID))));
				anbiaoInfo.setAnbiao_code(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.ANBIAOCODE)));
				anbiaoInfo.setBegin_date(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.BEGINDATE)));
				anbiaoInfo.setHolder_person(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.HOLDERPERSON)));
				anbiaoInfo.setName(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.NAME)));
				anbiaoInfo.setProduct_model(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.PRODUCTMODEL)));
				anbiaoInfo.setProduct_name(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.PRODUCTNAME)));
				anbiaoInfo.setProduction_address(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.PRODUCTIONADDRESS)));
				anbiaoInfo.setProduction_unit_id(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.PRODUCTIONUNITID)));
				anbiaoInfo.setProvide_date(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.PROVIDEDATE)));
				anbiaoInfo.setRegistered_address(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.REGISTEREDADDRESS)));
				anbiaoInfo.setRemark(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.REMARK)));
				anbiaoInfo.setScope_application(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.SCOPEAPPLICATION)));
				anbiaoInfo.setStandard(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.STANDARD)));
				anbiaoInfo.setState(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.STATE)));
				anbiaoInfo.setTerm_validity(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.TERMVALIDITY)));
				
				list.add(anbiaoInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbHelper.close();
		}
		
		return list;
	}

	@Override
	/**
	 * 获取安标证书表中的安标Code对应的ID
	 */
	public int getAnbiaoId(String condition) {
		
		String sql = String.format(com.jczb.checkpoint.db.AppDown.OPERATION.QUERY, condition);
		int anbiaoId = 0;
	
		try {
			Cursor cursor = dbHelper.rawQuery(sql);
			cursor.moveToFirst();
			while(!cursor.isAfterLast()){
				anbiaoId = (int)cursor.getInt(0);
				cursor.moveToNext();
			}
			/*while(cursor.moveToNext()){
				anbiaoId = cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.ID);
			}*/
			
		} catch (Exception e) {
			Log.e("get anbiaoId failed:","e="+e.getMessage());
		}
		
		return anbiaoId;
	}
	
	/**
	 * 获取安标下载表所有的记录
	 * @return
	 */
	@Override
	public List<AppDown> getAnbiaoList(){
		
		
		
		String sql = com.jczb.checkpoint.db.AppDown.OPERATION.QUERYALL;

		List<AppDown> list = new ArrayList<AppDown>();
		try {
			Cursor cursor = dbHelper.rawQuery(sql);
			
			// 给用户实体各个属性赋值
			while (cursor.moveToNext()) {
				//这个实例化必须写到里面，否则会导致所循环出来的数据全部一样。。。
				AppDown anbiaoInfo = new AppDown();
				anbiaoInfo.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.ID))));
				anbiaoInfo.setAnbiao_code(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.ANBIAOCODE)));
				anbiaoInfo.setBegin_date(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.BEGINDATE)));
				anbiaoInfo.setHolder_person(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.HOLDERPERSON)));
				anbiaoInfo.setName(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.NAME)));
				anbiaoInfo.setProduct_model(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.PRODUCTMODEL)));
				anbiaoInfo.setProduct_name(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.PRODUCTNAME)));
				anbiaoInfo.setProduction_address(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.PRODUCTIONADDRESS)));
				anbiaoInfo.setProduction_unit_id(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.PRODUCTIONUNITID)));
				anbiaoInfo.setProvide_date(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.PROVIDEDATE)));
				anbiaoInfo.setRegistered_address(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.REGISTEREDADDRESS)));
				anbiaoInfo.setRemark(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.REMARK)));
				anbiaoInfo.setScope_application(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.SCOPEAPPLICATION)));
				anbiaoInfo.setStandard(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.STANDARD)));
				anbiaoInfo.setState(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.STATE)));
				anbiaoInfo.setTerm_validity(cursor.getString(cursor.getColumnIndex(com.jczb.checkpoint.db.AppDown.FIELDS.TERMVALIDITY)));
				
				list.add(anbiaoInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbHelper.close();
		}

		return list;
	}
	

}
