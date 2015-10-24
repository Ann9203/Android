package com.jczb.checkpoint.manager;

import java.util.List;

import android.content.Context;

import com.jczb.checkpoint.dao.IAppDownCong;
import com.jczb.checkpoint.dao.impl.AppDownCongImpl;
import com.jczb.checkpoint.model.AppDownCong;

/**
 * 从表即RFID表下载管理类
 * @author wlc
 * @date 2015-3-31
 */
public class AppDownCongManager {
	
	private IAppDownCong dao;
	private List<AppDownCong> list;
	
	public AppDownCongManager(Context context){
		dao = new AppDownCongImpl(context);
	}
	
	/**
	 * 添加一条RFID信息
	 * @param RFIDInfo
	 */
	public void insert(AppDownCong RFIDInfo){
		dao.insert(RFIDInfo);
	}
	public List<AppDownCong> getListByPage(String condition, int pageSize,int pageNum)
	{
		String where = String.format("{2} order by id limit {0} offset {0}*{1}", pageSize,pageNum,condition);
		return dao.getAppDownCongByCondition(where);
		
	}
}
