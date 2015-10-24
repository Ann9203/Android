package com.jczb.car.bean;

import java.util.Date;

/**
 * 搜索关键字实体
 * @author 吴利昌
 * 2015-8-27下午3:53:42
 */
@SuppressWarnings("serial")
public class SearchKey extends Entity {

	/**标识*/
	private int uid;
	
	/**关键字*/
	private String keywords;
	
	/**搜索时间*/
	private Date searchTime;
	


	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Date getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(Date searchTime) {
		this.searchTime = searchTime;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	
	
}
