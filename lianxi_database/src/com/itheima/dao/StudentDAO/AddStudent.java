package com.itheima.dao.StudentDAO;

import java.util.ArrayList;

import com.itheima.domain.UserBean.UserBean;

public class AddStudent {
	public static ArrayList<UserBean> addAll()
	{
		ArrayList<UserBean> array=new ArrayList<UserBean>();
		UserBean ub=new UserBean();
		ub.name="小花";
		ub.sex="女";
		ub.shcool="北京大学";
		ub.phone="120";
		array.add(ub);
		
		UserBean ub1=new UserBean();
		ub1.name="小草";
		ub1.sex="男";
		ub1.shcool="北京大学";
		ub1.phone="119";
		array.add(ub1);
		
		
		UserBean ub2=new UserBean();
		ub2.name="小雨";
		ub2.sex="男";
		ub2.shcool="理工大学";
		ub2.phone="100";
		array.add(ub2);
		
		UserBean ub3=new UserBean();
		ub3.name="小风";
		ub3.sex="女";
		ub3.shcool="语言大学";
		ub3.phone="111";
		array.add(ub3);
		return array;
	}
}
