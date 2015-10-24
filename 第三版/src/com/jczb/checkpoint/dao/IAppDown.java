package com.jczb.checkpoint.dao;

import java.util.List;

import com.jczb.checkpoint.model.AppDown;

/**
 * 安标下载接口
 * @author wlc
 * @date 2015-3-31
 */
public interface IAppDown {

	public void insert(AppDown anbiaoInfo);
	
	public void delete(String condition);
	
	public void update(AppDown anbiaoInfo);
	
	public List<AppDown> getAnbiaoInfoByCondition(String condition);
	
	public int getAnbiaoId(String condition);
	
	public List<AppDown> getAnbiaoList();
	
}
