package com.itheima.dao.StudentDAO;

import java.util.ArrayList;

import com.itheima.domain.Student.StudentBean;

public class AddStudent {
	public static ArrayList<StudentBean> addAll()
	{
		ArrayList<StudentBean> array=new ArrayList<StudentBean>();
		StudentBean ub=new StudentBean();
		ub.name="小花";
		ub.sex="女";
		ub.school="北京大学";
		ub.phone="120";
		array.add(ub);
		
		StudentBean ub1=new StudentBean();
		ub1.name="小草";
		ub1.sex="男";
		ub1.school="北京大学";
		ub1.phone="119";
		array.add(ub1);
		
		
		StudentBean ub2=new StudentBean();
		ub2.name="小雨";
		ub2.sex="男";
		ub2.school="理工大学";
		ub2.phone="100";
		array.add(ub2);
		
		StudentBean ub3=new StudentBean();
		ub3.name="小风";
		ub3.sex="女";
		ub3.school="语言大学";
		ub3.phone="111";
		array.add(ub3);
		return array;
	}
}
