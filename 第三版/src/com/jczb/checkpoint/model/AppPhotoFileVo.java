package com.jczb.checkpoint.model;

import java.util.List;

import com.jczb.checkpoint.R.string;

/**
 * 安标图片下载映射类
 * @author wlc
 * @date 2015-4-1
 */
public class AppPhotoFileVo {

	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	private List<AppPhotoFile> DownPhoto;
	
	
	public List<AppPhotoFile> getDownPhoto() {
		return DownPhoto;
	}
	public void setDownPhoto(List<AppPhotoFile> downPhoto) {
		DownPhoto = downPhoto;
	}
	
	
	
}
