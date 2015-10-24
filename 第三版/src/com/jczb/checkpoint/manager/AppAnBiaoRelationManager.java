package com.jczb.checkpoint.manager;

import java.util.List;

import android.R.integer;
import android.content.Context;

import com.jczb.checkpoint.dao.IAppAnBiaoRelation;
import com.jczb.checkpoint.dao.impl.AppAnBiaoRelationImpl;
import com.jczb.checkpoint.db.AnBiaoRelation.FIELDS;
import com.jczb.checkpoint.model.AppAnBiaoRelation;

/**
 * 安标关联表管理类
 * @author wlc
 * @date 2015-4-1
 */
public class AppAnBiaoRelationManager {

	private IAppAnBiaoRelation dao;
	private List<AppAnBiaoRelation> list;
	
	public AppAnBiaoRelationManager(Context context){
		dao = new AppAnBiaoRelationImpl(context);
	}
	
	/**
	 * 获取存入本地的主安标Id
	 * @param anbiaoCode
	 * @return
	 */
	public int getAnbiaoId(String anbiaoCode){
		
		return dao.getAnbiaoId(anbiaoCode);
				
	}
	
	/**
	 * 向表中添加一条记录
	 * @param anbiaoRelationInfo
	 */
	public void insert(AppAnBiaoRelation anbiaoRelationInfo){
		dao.insert(anbiaoRelationInfo);
	}
	
	/**
	 * 根据条件获取安标关联表信息
	 * @param condition
	 * @return
	 */
	public List<AppAnBiaoRelation> getRelationInfo(String condition){
		
		String conditions = FIELDS.MAINANBIAOID + "='" + condition + "'" ;
		
		list = dao.getRelationInfoByCondition(conditions);
		
		return list;
		
	}
	
}
