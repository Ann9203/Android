package com.jczb.car.bean;

/**
 * 站点用户实体
 * 
 * @author 吴利昌 2015-8-27下午3:05:45
 */
@SuppressWarnings("serial")
public class SiteUser extends Entity{

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
