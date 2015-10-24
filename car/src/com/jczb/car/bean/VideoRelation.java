package com.jczb.car.bean;

/**
 * 关联视频实体
 * 
 * @author 吴利昌
 * @date 2015-9-6下午9:20:59
 */
@SuppressWarnings("serial")
public class VideoRelation extends Entity {
	
	private String uid;
	private String title;
	private String keyword;
	private String path;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
