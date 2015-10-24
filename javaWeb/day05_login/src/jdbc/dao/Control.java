package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.domain.User;
import jtool.JTool;


public class Control {
	/*
	 * ��Ҫ���Ƕ����ݽ��в�����
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
			//�������ݿ�
			conn=JTool.getConnection();
			//��ֹsqlע�������ʹ��preparedstatmen ,��sql��Ԥ���룬Ȼ�����
			//�̳���Statement �ӿ�
		//	st = conn.prepareStatement("select id,name,password,email,birthday from users where name=? and password=? ");

			ps=conn.prepareStatement("select * from user where  name=? and password=?");
			
			//��ռλ����ֵ
			ps.setString(1, name);
			ps.setString(2, password);
		
			//ִ��SQL��䷵�ؽ����
			//rs=ps.executeQuery();
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
