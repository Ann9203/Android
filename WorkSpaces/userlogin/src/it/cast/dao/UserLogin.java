package it.cast.dao;

import it.cast.pojo.UserEntity;

public interface UserLogin {
	/***
	 * 
	 * ����߶��ǳ���ķ����ӿ�
	 * ��Ҫ�����У���½��ע��Ľӿ�
	 * 
	 * */
	//�ж��Ƿ��½�ɹ�����Ҫ�ǶԱ��û����ƺ�����
	public abstract boolean isLogin(String name,String password);
	//ע�Ṧ�ܣ���Ҫ���Ǵ�ǰ�˴�����User����
	public abstract void regist(UserEntity user);
}
