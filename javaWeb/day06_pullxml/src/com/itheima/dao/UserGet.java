package com.itheima.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jtool.JTool;
import com.itheima.domain.Student;
public class UserGet {
	//�����ݿ��л�ȡUser�е�����

	public static List<Student> getUser()
	{
		//��������
		Connection conn=null;
		//�������ݲ�������ֹSQLע��ʹ��PreparedStatement
		PreparedStatement ps=null;
		//���������
		ResultSet rs=null;
		Student s=null;
		List<Student> stulist=new ArrayList<Student>();
		try{
			//ͨ���Լ�д��jar���е����ݻ�ȡ������
			conn=JTool.getConnection();
			//�������ݲ���
			ps=conn.prepareStatement("select * from student");
			rs=ps.executeQuery();

			while(rs.next())
			{
				
				s=new Student();
				s.setId(rs.getInt("id"));
				s.setStuname(rs.getString("stuname"));
				s.setChinese(rs.getInt("chinese"));
				s.setEnglish(rs.getInt("english"));
				s.setMath(rs.getInt("math"));
				s.setSex(rs.getString("sex"));
				stulist.add(s);
				
			}
			//return stulist;
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return stulist;
	}
	

}
