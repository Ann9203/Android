package com.itheima.dao.StudentDAO;

import java.util.ArrayList;

import com.itheima.domain.Student.StudentBean;

public class AddStudent {
	public static ArrayList<StudentBean> addAll()
	{
		ArrayList<StudentBean> array=new ArrayList<StudentBean>();
		StudentBean ub=new StudentBean();
		ub.name="С��";
		ub.sex="Ů";
		ub.school="������ѧ";
		ub.phone="120";
		array.add(ub);
		
		StudentBean ub1=new StudentBean();
		ub1.name="С��";
		ub1.sex="��";
		ub1.school="������ѧ";
		ub1.phone="119";
		array.add(ub1);
		
		
		StudentBean ub2=new StudentBean();
		ub2.name="С��";
		ub2.sex="��";
		ub2.school="����ѧ";
		ub2.phone="100";
		array.add(ub2);
		
		StudentBean ub3=new StudentBean();
		ub3.name="С��";
		ub3.sex="Ů";
		ub3.school="���Դ�ѧ";
		ub3.phone="111";
		array.add(ub3);
		return array;
	}
}
