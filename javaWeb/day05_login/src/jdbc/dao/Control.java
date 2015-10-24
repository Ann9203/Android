package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.domain.User;
import jtool.JTool;


public class Control {
	/*
	 * 主要就是对数据进行操作的
	 * */
	public  static User findUseer(String name,String password)
	{
		System.out.println(name);
		System.out.println(password);
		Connection conn=null;
		PreparedStatement ps=null;
		User us=null;
		ResultSet rs=null;
		try{
			//连接数据库
			conn=JTool.getConnection();
			//防止sql注入的问题使用preparedstatmen ,是sql的预编译，然后就是
			//继承了Statement 接口
		//	st = conn.prepareStatement("select id,name,password,email,birthday from users where name=? and password=? ");

			ps=conn.prepareStatement("select * from user where  name=? and password=?");
			
			//给占位符赋值
			ps.setString(1, name);
			ps.setString(2, password);
		
			//执行SQL语句返回结果集
			//rs=ps.executeQuery();
			//执行SQL语句返回结果集
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				us=new User();
				us.setId(rs.getInt("id"));
				us.setName(rs.getString("name"));
				us.setPassword(rs.getString("password"));
			}
			return us;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return us;
		
	}

}
