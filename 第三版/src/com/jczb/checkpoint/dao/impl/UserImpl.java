package com.jczb.checkpoint.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.jczb.checkpoint.dao.IUser;
import com.jczb.checkpoint.db.DBHelper;
import com.jczb.checkpoint.db.Users;
import com.jczb.checkpoint.model.User;

/**
 * 用户实现类
 * 
 * @author wlc
 * @date 2015-3-23
 */
public class UserImpl implements IUser {

	private DBHelper dbHelper;

	/**
	 * 构造方法
	 * 
	 * @param context
	 */
	public UserImpl(Context context) {
		dbHelper = new DBHelper(context);
	}

	@Override
	public void insert(User user) {
		String sql = String.format(Users.OPERATION.INSERT, user.getUserName(),
				user.getPassWord(), user.getRealName(),user.getServerid());
		dbHelper.execSQL(sql);
	}

	@Override
	public void delete(int userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String condition) {
		String conditions = Users.FIELDS.USERNAME + "= '" + condition + "'";
		String sql = String.format(Users.OPERATION.DELETE, conditions);
		dbHelper.execSQL(sql);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getUserByCondition(String condition) {

		String sql = String.format(Users.OPERATION.QUERY, condition);

		List<User> list = new ArrayList<User>();
		try {
			Cursor cursor = dbHelper.rawQuery(sql);
			User user = new User();
			// 给用户实体各个属性赋值
			while (cursor.moveToNext()) {				
				user.setUserId(cursor.getInt(cursor
						.getColumnIndex(Users.FIELDS.ID)));
				user.setUserName(cursor.getString(cursor
						.getColumnIndex(Users.FIELDS.USERNAME)));
				user.setPassWord(cursor.getString(cursor
						.getColumnIndex(Users.FIELDS.PASSWORD)));
				user.setRealName(cursor.getString(cursor
						.getColumnIndex(Users.FIELDS.REALNAME)));
				user.setServerid(cursor.getString(cursor.getColumnIndex(Users.FIELDS.SERVERID)));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbHelper.close();
		}

		return list;
	}

}
