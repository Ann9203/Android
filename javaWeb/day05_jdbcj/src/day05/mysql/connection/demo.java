package day05.mysql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;
public class demo {
	@Test	
	//��������ǲ�ѯ���ݿ��е����ݵ�
	public  void function2() throws Exception
	{
		//1.��������
		//2.ע������
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//3.�������ݿ�
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/day05", "root", "199028");
		//4.�������ݿ�
		Statement st=con.createStatement();
		//�������ӵ����
		String sql="select * from user_tb";
		//��������
		ResultSet rs=st.executeQuery(sql);
		//��������
		while(rs.next())
		{
			int id=rs.getInt("id");
			String name=rs.getString("name");
			String pwd=rs.getString("pwd");
			System.out.println(id+"----"+name+"--"+pwd);
		}
		//��ӡ����
			
		//5.�ر����ݿ�
		//6.��ӡ����
		
		st.close();
		con.close();
		
	}

}
