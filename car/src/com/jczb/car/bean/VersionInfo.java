package com.jczb.car.bean;

import java.util.Date;

/**
 * 版本信息实体
 * @author 吴利昌
 * 2015-8-27下午3:11:44
 */
@SuppressWarnings("serial")
public class VersionInfo extends Entity{

	/**主键*/
	private int uid;
	
	/**版本号*/
	private String versionNumber;
	
	/**更新内容*/
	private String updateContent;
	
	/**发布日期*/
	private Date issueDate;
	
	/**更新时间*/
	private String updateTime;
	
	


	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getUpdateContent() {
		return updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}


	
}
