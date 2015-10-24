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
	//׼��������Դjdbc��������
	
	public static void main(String[] args)
	{
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		//1.����һ������
		try{
				conn=JTool.getConnection();
				//3.������������
				 st=conn.createStatement();
				//�������ݽ��в�ѯ���ַ�������DQL DML �Լ�DDL
				st.execute("select * from student");
				//4.���������
			
				//��ȡ��ѯ�����Ľ��д�뵽�������
				rs=st.getResultSet();
				//����һ������
				List<student>stu=new ArrayList<student>();
				//����һ��Student����ı�������ʼ��Ϊnull��ֻ�е���ֵ��ʱ����newһ������
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
	


