package com.jczb.checkpoint.model;

import java.io.Serializable;


/**
 * 用户实体类
 * @author wlc
 * @date 2015-3-23
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5813470172620089669L;
	/**
	 * 
	 */
	
	private int userId;
	private String userName;
	private String passWord;
	private String realName;
	private String serverid;
	
	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public User(){
		super();
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", passWord=" + passWord + ", realName=" + realName
				+ ", serverid=" + serverid + "]";
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
}
