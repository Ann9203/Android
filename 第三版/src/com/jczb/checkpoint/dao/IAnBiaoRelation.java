package com.jczb.checkpoint.dao;

import com.jczb.checkpoint.manager.DownRelationManager;

/**
 * 安标关联表接口类
 * @author wlc
 * @date 2015-3-31
 */
public interface IAnBiaoRelation {

	public void insert(DownRelationManager downRelationManager);
	
	public void delete(int downCertificate);
	
	public void delete(String condiction);
	
	public void update(DownRelationManager downRelationManager); 

}
