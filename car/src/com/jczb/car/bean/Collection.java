package com.jczb.car.bean;

import java.util.Date;

/**
 * 收藏实体
 * @author 吴利昌
 * 2015-8-27下午2:55:33
 */
@SuppressWarnings("serial")
public class Collection extends Entity{
	
	/**主键标识*/
	private int uid;
	
	/**内容Id*/
	private String contentId;
	
	/**用户Id*/
	private String userId;
	
	/**收藏时间*/
	private Date collectTime;
	
	

	

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	
}
