package com.jczb.checkpoint.model;

import java.util.List;

/**
 * 登陆用户模型
 * @author 星尘
 * @date 2015-03-27
 */

public class UserVo {
	
	String result;
	List<User> user;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}

}
