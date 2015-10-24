package com.itheima.dao.StudentDAO;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.SQLiteHelper.MySQLiteHelper;
import com.itheima.domain.UserBean.UserBean;

public class StudentDAO {
	
	//这个就是进行增删改查的
	
	//定义一个构造方法
	private  MySQLiteHelper mysql;
	public StudentDAO(Context context)
	{
		this.mysql=new MySQLiteHelper(context);
	}
	//添加的方法
	public boolean add(UserBean user)
	{
		boolean flag=false;
		//创建数据库
			SQLiteDatabase sq = mysql.getReadableDatabase();
		//创建ContentValues方法
			ContentValues values=new ContentValues();
			values.put("name", user.name);
			values.put("sex", user.sex);
			values.put("school", user.shcool);
			values.put("phone", user.phone);
			long insert = sq.insert("student1", null, values);
			if(insert!=-1)
			{
				flag=true;
				
			}else{
				flag=false;
			}
			sq.close();
		return flag;		
	}
	//删除数据
	public int delete(String name)
	{
		SQLiteDatabase sq = mysql.getReadableDatabase();
		int delete = sq.delete("student1", "name=?", new String[]{name});
		sq.close();
		return delete;
	}
	//修改数据
	public int update(UserBean user)
	{
		SQLiteDatabase sq = mysql.getReadableDatabase();
		ContentValues values=new ContentValues();
		values.put("phone", user.phone);
		values.put("school",user.shcool );
		int update = sq.update("student1", values, "name=?", new String[]{user.name});
		sq.close();
		
		return update;
	}
	//查询所有的数据
	public ArrayList<UserBean> queryAll()
	{
		ArrayList<UserBean> array=new ArrayList<UserBean>();
		SQLiteDatabase sq = mysql.getReadableDatabase();
	
		Cursor cursor = sq.query("student1", new String[]{"name","sex","school","phone"}, null, null, null, null, null);
		if(cursor!=null && cursor.getCount()>0)
		{
			while(cursor.moveToNext())
			{
				UserBean ub=new UserBean();
				ub.name=cursor.getString(0);
				ub.sex=cursor.getString(1);
				ub.shcool=cursor.getString(2);
				ub.phone=cursor.getString(3);
				array.add(ub);
			}
		}
		sq.close();
		return array;
	}
	

}
