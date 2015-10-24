package demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jtool.JTool;

public class demo_jdbc {
	//准备工作资源jdbc驱动导入
	
	public static void main(String[] args)
	{
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		//1.创建一个驱动
		try{
				conn=JTool.getConnection();
				//3.创建操作数据
				 st=conn.createStatement();
				//操作数据进行查询三种方法操作DQL DML 以及DDL
				st.execute("select * from student");
				//4.创建结果集
			
				//获取查询出来的结果写入到结果集中
				rs=st.getResultSet();
				//创建一个集合
				List<student>stu=new ArrayList<student>();
				//创建一个Student对象的变量，初始化为null，只有当有值的时候再new一个对象
				student stu1=null;
				
				while(rs.next())
				{
					stu1=new student();
					stu1.setId(rs.getInt("id"));
					stu1.setName(rs.getString("stuname"));
					stu1.setSex(rs.getString("sex"));
					stu1.setChinese(rs.getInt("chinese"));
					stu1.setEnglish(rs.getInt("english"));
					stu1.setMath(rs.getInt("math"));
					stu.add(stu1);
								
				}
				for(student stu2:stu)
				{
					System.out.println(stu2.toString());
					
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
//			if(conn !=null)
//			{
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if(rs !=null)
//			{
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if(st !=null)
//			{
//				try {
//					st.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			JTool.closeSource(conn, st, rs);
			}
			
			
		}

		
		
		
		
	}
	


