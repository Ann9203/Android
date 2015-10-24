package com.jczb.checkpoint.dao;

import java.util.List;

import com.jczb.checkpoint.model.AppAnBiaoRelation;

/**
 * 安标关联表
 * @author wlc
 * @date 2015-4-1
 */
public interface IAppAnBiaoRelation {

	public void insert(AppAnBiaoRelation anBiaoRelation);
	
	public void delete(String condition);
	
	public void update(AppAnBiaoRelation anBiaoRelation);
	
	/**
	 * 通过条件获取关联表信息实体
	 * @param condition
	 * @return
	 */
	public List<AppAnBiaoRelation> getRelationInfoByCondition(String condition);
	
	public int getAnbiaoId(String condition);
	
}
