package com.jczb.checkpoint.manager;

import java.util.List;

import android.content.Context;

import com.jczb.checkpoint.dao.IUser;
import com.jczb.checkpoint.dao.impl.UserImpl;
import com.jczb.checkpoint.db.Users;
import com.jczb.checkpoint.db.Users.FIELDS;
import com.jczb.checkpoint.model.User;

/**
 * 用户管理类
 * @author wlc
 * @date 2015-3-23
 */
public class UserManager {
	
	//声明接口
	private IUser userDao;
	
	//构造函数,实例化接口实现类
	public UserManager(Context context){
		userDao = new UserImpl(context);
	}
	
	/**
	 * 根据用户名和密码查找是否存在该用户
	 * @return 用户实体
	 */
	public List<User> getUserByNameAndPwd(String userName,String passWord){
		String condition = FIELDS.USERNAME + "='" + userName + "' and " + FIELDS.PASSWORD + "='" + passWord+"'";
		List<User> users = userDao.getUserByCondition(condition);
		return users;
	}
	
	/**
	 * 向User表中插入一条记录
	 */
	public void Insert(User user){
		userDao.insert(user);
	}
	
	/**
	 * 根据用户名删除记录
	 */
	public void Delete(String userName){
		userDao.delete(userName);
	}
	
	/**
	 * 根据用户名获取用户记录
	 * @param userName
	 * @return
	 */
	public List<User> getUser(String userName){
		String condition = Users.FIELDS.USERNAME + "='"+userName +"'";
		List<User> users = userDao.getUserByCondition(condition);
		return users;
	}
	
}
