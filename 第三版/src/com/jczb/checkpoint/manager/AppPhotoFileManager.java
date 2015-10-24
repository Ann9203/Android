package com.jczb.checkpoint.manager;

import java.util.List;

import android.content.Context;

import com.jczb.checkpoint.dao.IAppPhotoFile;
import com.jczb.checkpoint.dao.impl.AppPhotoFileImpl;
import com.jczb.checkpoint.db.PhotoFile.FIELDS;
import com.jczb.checkpoint.model.AppPhotoFile;
import com.jczb.checkpoint.model.User;

/**
 * 图片管理类
 * @author wlc
 * @date 2015-4-1
 */
public class AppPhotoFileManager {

	private IAppPhotoFile dao;
	
	public AppPhotoFileManager(Context context){
		dao = new AppPhotoFileImpl(context);
	}
	/**
	 * 向User表中插入一条记录
	 */
	public void Insert(AppPhotoFile photoFile){
		dao.insert(photoFile);
	}
	
	/**
	 * 根据条件获取图片
	 * @return
	 */
	public List<AppPhotoFile> getPhotoFileByCondition(int condition, String imageType){
		
		String conditions = FIELDS.MAINID + "=" + condition + " and " +  FIELDS.MAINSTYPE + "='" + imageType + "'";
		List<AppPhotoFile> photos = dao.getPhotoByCondition(conditions);
		
		return photos;
	}
	
	/**
	 * 根据类型Id(抽查查询专用)获取
	 * @param id
	 * @return
	 */
	public List<AppPhotoFile> getPhotoFileById(String id){
		String conditions = FIELDS.MAINID + "=" + id ;
		List<AppPhotoFile> photos = dao.getPhotoByCondition(conditions);
		
		return photos;
	}
	
	/**
	 * 根据类型ID和类型名称获取图片
	 * 由于安标扫描件和RFID图片是同一个类型ID，故以类型名称区分
	 */
	public List<AppPhotoFile> getPhotoFileByIdAndType(String id,String type){
		String conditions = FIELDS.MAINID + "='" + id + "' and " + FIELDS.MAINSTYPE + "='" + type + "'";
		List<AppPhotoFile> photos = dao.getPhotoByCondition(conditions);
		return photos;
	}
	
}
