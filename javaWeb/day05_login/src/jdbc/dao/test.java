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
//			//�������ݿ�
//			conn=JTool.getConnection();
//			//��ֹsqlע�������ʹ��preparedstatmen ,��sql��Ԥ���룬Ȼ�����
//			//�̳���Statement �ӿ�
//			ps=conn.prepareStatement("insert into user (id,name,password) values(?,?,?)");
//			//��ռλ����ֵ
//			ps.setInt(1, 1);
//			ps.setString(2, "li");
//			ps.setString(3, "123");
//			//ִ��SQL��䷵�ؽ����
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
			//�������ݿ�
			conn=JTool.getConnection();
			//��ֹsqlע�������ʹ��preparedstatmen ,��sql��Ԥ���룬Ȼ�����
			//�̳���Statement �ӿ�
			ps=conn.prepareStatement("select * from user where  name=? password=?");
			//��ռλ����ֵ
			ps.setString(1, name);
			ps.setString(2, password);
			//ִ��SQL��䷵�ؽ����
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
