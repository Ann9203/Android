package it.cast.pojo;

public class UserEntity {
	//�û�����
	private String name;
	//�û�������
	private String password;
	//�û����ֻ�����
	private String phonenum;
	//�û���Email
	private String email;
	
	/*
	 * �޲ι������
	 * 
	 * */
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 * 
	 * ���εĹ��캯��
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
