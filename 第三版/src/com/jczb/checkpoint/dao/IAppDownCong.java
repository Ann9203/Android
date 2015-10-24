package com.jczb.checkpoint.dao;

import java.util.List;

import com.jczb.checkpoint.model.AppDownCong;


/**
 * RFID下载接口类
 * @author wlc
 * @date 2015-3-31
 */
public interface IAppDownCong {

	public void insert(AppDownCong dwonCong);
	
	public void delete(int dwonCongId);
	
	public void delete(String condiction);
	
	public void update(AppDownCong dwonCong);
	
	/**
	 * 根据指定条件查询
	 * @param condition
	 * @return
	 */
	public List<AppDownCong> getAppDownCongByCondition(String condition);

	
	
}
