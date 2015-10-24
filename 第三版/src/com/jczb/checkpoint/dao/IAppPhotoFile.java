package com.jczb.checkpoint.dao;

import java.util.List;

import com.jczb.checkpoint.model.AppPhotoFile;


/**
 * 图片表接口类
 * @author wlc
 * @date 2015-4-1
 */
public interface IAppPhotoFile {
	
	public void insert(AppPhotoFile photoFile);
	
	public void delete(int photoId);
	
	public void delete(String condition);
	
	public void update(AppPhotoFile photoFile);
	
	/**
	 * 根据指定条件查询
	 * @param condition
	 * @return
	 */
	public List<AppPhotoFile> getPhotoByCondition(String condition);
	
}
