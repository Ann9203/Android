package day05.mysql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;
public class demo {
	@Test	
	//这个方法是查询数据库中的数据的
	public  void function2() throws Exception
	{
		//1.导入驱动
		//2.注册驱动
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//3.连接数据库
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/day05", "root", "199028");
		//4.操作数据库
		Statement st=con.createStatement();
		//创建连接的语句
		String sql="select * from user_tb";
		//接受数据
		ResultSet rs=st.executeQuery(sql);
		//便利数据
		while(rs.next())
		{
			int id=rs.getInt("id");
			String name=rs.getString("name");
			String pwd=rs.getString("pwd");
			System.out.println(id+"----"+name+"--"+pwd);
		}
		//打印数据
			
		//5.关闭数据库
		//6.打印数据
		
		st.close();
		con.close();
		
	}

}
