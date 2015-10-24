package it.cast.dao;

import it.cast.pojo.UserEntity;

public interface UserLogin {
	/***
	 * 
	 * 这里边都是抽象的方法接口
	 * 主要就是有：登陆和注册的接口
	 * 
	 * */
	//判断是否登陆成功，主要是对比用户名称和密码
	public abstract boolean isLogin(String name,String password);
	//注册功能，主要就是从前端传过来User对象。
	public abstract void regist(UserEntity user);
}
