package com.itheima.dao.StudentDAO;

import java.util.ArrayList;

import com.itheima.domain.UserBean.UserBean;

public class AddStudent {
	public static ArrayList<UserBean> addAll()
	{
		ArrayList<UserBean> array=new ArrayList<UserBean>();
		UserBean ub=new UserBean();
		ub.name="С��";
		ub.sex="Ů";
		ub.shcool="������ѧ";
		ub.phone="120";
		array.add(ub);
		
		UserBean ub1=new UserBean();
		ub1.name="С��";
		ub1.sex="��";
		ub1.shcool="������ѧ";
		ub1.phone="119";
		array.add(ub1);
		
		
		UserBean ub2=new UserBean();
		ub2.name="С��";
		ub2.sex="��";
		ub2.shcool="����ѧ";
		ub2.phone="100";
		array.add(ub2);
		
		UserBean ub3=new UserBean();
		ub3.name="С��";
		ub3.sex="Ů";
		ub3.shcool="���Դ�ѧ";
		ub3.phone="111";
		array.add(ub3);
		return array;
	}
}
