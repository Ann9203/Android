package it.cast.pojo;

public class UserEntity {
	//用户姓名
	private String name;
	//用户的密码
	private String password;
	//用户的手机号码
	private String phonenum;
	//用户的Email
	private String email;
	
	/*
	 * 无参构造参数
	 * 
	 * */
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 * 
	 * 带参的构造函数
	 * */
	public UserEntity(String name, String password, String phonenum,
			String email) {
		super();
		this.name = name;
		this.password = password;
		this.phonenum = phonenum;
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhonenum() {
		return phonenum;
	}


	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	

	
}
