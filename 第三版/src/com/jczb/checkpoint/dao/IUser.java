package com.jczb.checkpoint.dao;

import java.util.List;

import com.jczb.checkpoint.R.string;
import com.jczb.checkpoint.model.User;

/**
 * 用户接口类
 * @author wlc
 * @date 2015-3-23
 */
public interface IUser {
	
	public void insert(User user);
	
	public void delete(int userId);
	
	public void delete(String condition);
	
	public void update(User user);
	
	/**
	 * 根据指定条件查询
	 * @param condition
	 * @return
	 */
	public List<User> getUserByCondition(String condition);
}
