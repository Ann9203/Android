package com.itheima.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jtool.JTool;
import com.itheima.domain.Student;
public class UserGet {
	//从数据库中获取User中的数据

	public static List<Student> getUser()
	{
		//创建连接
		Connection conn=null;
		//创建数据操作，防止SQL注入使用PreparedStatement
		PreparedStatement ps=null;
		//创建结果集
		ResultSet rs=null;
		Student s=null;
		List<Student> stulist=new ArrayList<Student>();
		try{
			//通过自己写的jar包中的内容获取到连接
			conn=JTool.getConnection();
			//创建数据操作
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
