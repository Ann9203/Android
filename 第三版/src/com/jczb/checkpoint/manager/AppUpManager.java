package com.jczb.checkpoint.manager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;


import com.jczb.checkpoint.dao.IAppUp;
import com.jczb.checkpoint.dao.impl.AppUpImpl;

import com.jczb.checkpoint.model.AppUp;

public class AppUpManager {
	
	private IAppUp appUpDao;
	
	public AppUpManager(Context context)
	{
		appUpDao = new AppUpImpl(context);
	}
	public void insert(AppUp appUp)
	{
		appUpDao.insert(appUp);
	}
	public List<AppUp> getListByPage(String condition, int pageSize,int pageNum)
	{
		pageNum=pageNum-1;
		String where =" "+ condition + " order by id limit " + pageSize +" offset "+pageSize+"*"+ pageNum ;
		return appUpDao.getAppDownCongByCondition(where);
		
	}
	
	/**
	 * 根据条件获取上传记录
	 * @return
	 */
	public List<AppUp> getUpRecord(String condition){
		List<AppUp> list = new ArrayList<AppUp>();
		String conditions = com.jczb.checkpoint.db.AppUp.FIELDS.EPCCODE+" = '" + condition +"'";
		list =  appUpDao.getUpRecord(conditions);
		return list;
	}
	
	public List<AppUp> getUpDataWithID(int ID)
	{
		String condition = com.jczb.checkpoint.db.AppUp.FIELDS.ID + "='" + ID + "'";
		List<AppUp> appUps = appUpDao.getUpRecord(condition);
		return appUps;
	}
}
