package com.jczb.checkpoint.manager;

import java.util.List;

import android.content.Context;

import com.jczb.checkpoint.dao.IAppDown;
import com.jczb.checkpoint.dao.impl.AppDownImpl;
import com.jczb.checkpoint.db.AppDown.FIELDS;
import com.jczb.checkpoint.model.AppDown;

/**
 * 安标证书信息下载管理类
 * @author wlc
 * @date 2015-3-31
 */
public class AppDownManager {

	private IAppDown dao;
	private List<AppDown> list;
	public AppDownManager(Context context){
		dao = new AppDownImpl(context);
	}
	
	public void insert(AppDown anbiaoInfo){
		dao.insert(anbiaoInfo);
	}
	
	/**
	 * 获取存入本地的主安标Id
	 * @param anbiaoCode
	 * @return
	 */
	public int getAnbiaoId(String anbiaoCode){
		String condition = FIELDS.ANBIAOCODE + "='" + anbiaoCode + "'";
		return dao.getAnbiaoId(condition);
				
	}
	
	/**
	 * 获取安标下载表中的所有数据
	 * @return
	 */
	public List<AppDown> getAnbiaoList(){
		
		list = dao.getAnbiaoList();		
		return list;
	}
	
	/**
	 * 获取一条安标下载表中的数据
	 * @return
	 */
	public List<AppDown> getOneAnbiaoInfo(String anbiaoId){
		
		String condition = FIELDS.ID + "='" + anbiaoId + "'";
		
		list = dao.getAnbiaoInfoByCondition(condition);
				
		return list;
		
	}
	
}
