package com.jczb.car.bean;

import java.io.IOException;
import java.io.InputStream;


/**
 * 
 * 
 * --由于经理在添加该实体的时候与本app中要用到的实体重名，现在改用SiteUser---
 * --吴利昌添加注释--
 * 
 * 
 * 登录用户实体类
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class User extends Base {
	
	/** 主键标识 */
	private int uid;

	/** 用户名 */
	private String userName;

	/** 密码 */
	private String password;

	/** 昵称 */
	private String nickname;

	/** 头像 */
	private String headimage;

	/** 类型，本地还是第三方 */
	private String type;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	}
