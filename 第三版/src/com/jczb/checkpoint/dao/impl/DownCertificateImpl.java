package com.jczb.checkpoint.dao.impl;

import java.util.List;

import android.content.Context;

import com.jczb.checkpoint.dao.IDownCertificate;
import com.jczb.checkpoint.db.DBHelper;
import com.jczb.checkpoint.db.AppDown;
import com.jczb.checkpoint.db.Users;
import com.jczb.checkpoint.model.DownCertificate;
import com.jczb.checkpoint.model.User;

/**
 * 安标下载实现类
 * @author wlc
 * @date 2015-3-30
 */
public class DownCertificateImpl implements IDownCertificate {
	
	
	private DBHelper dbHelper;
	
	/**
	 * 构造方法
	 * @param context
	 */
	public DownCertificateImpl(Context context){
		dbHelper = new DBHelper(context);
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}


	@Override
	public void insert(DownCertificate downCertificate) {
		// TODO Auto-generated method stub
		String sql = String.format(AppDown.OPERATION.INSERT,downCertificate.getBegin_date(),downCertificate.getAnbiao_code(),downCertificate.getProvide_date(),downCertificate.getTerm_validity(),downCertificate.getHolder_person(),downCertificate.getRegistered_address(),downCertificate.getProduction_unit_id(),downCertificate.getProduction_address(),downCertificate.getProduct_name(),downCertificate.getProduct_model(),downCertificate.getStandard(),downCertificate.getScope_application(),downCertificate.getRemark(),downCertificate.getState(),downCertificate.getName());;
		dbHelper.execSQL(sql);
	}


	@Override
	public void delete(int downCertificate) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(String condiction) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(DownCertificate downCertificate) {
		// TODO Auto-generated method stub
		
	}




}
