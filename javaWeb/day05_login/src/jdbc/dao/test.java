package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import jdbc.domain.User;
import jtool.JTool;



public class test {
//	@Test
//	public void findUser()
//	{
//		Connection conn=null;
//		PreparedStatement ps=null;
//		User us=null;
//		ResultSet rs=null;
//		try{
//			//连接数据库
//			conn=JTool.getConnection();
//			//防止sql注入的问题使用preparedstatmen ,是sql的预编译，然后就是
//			//继承了Statement 接口
//			ps=conn.prepareStatement("insert into user (id,name,password) values(?,?,?)");
//			//给占位符赋值
//			ps.setInt(1, 1);
//			ps.setString(2, "li");
//			ps.setString(3, "123");
//			//执行SQL语句返回结果集
//			ps.executeUpdate();
////			rs=ps.executeQuery();
////			while(rs.next())
////			{
////				us=new User();
////				us.setId(rs.getInt("id"));
////				us.setName(rs.getString("name"));
////				us.setPassword(rs.getString("password"));
////			}
////			
//	}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}	
//		
//	}
	public  static User findUseer(String name,String password)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		User us=null;
		ResultSet rs=null;
		try{
			//连接数据库
			conn=JTool.getConnection();
			//防止sql注入的问题使用preparedstatmen ,是sql的预编译，然后就是
			//继承了Statement 接口
			ps=conn.prepareStatement("select * from user where  name=? password=?");
			//给占位符赋值
			ps.setString(1, name);
			ps.setString(2, password);
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
