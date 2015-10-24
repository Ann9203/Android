package jtool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

public class JTool {
	/*
	 * 	���ݷ�װ�����ԣ�
	 * 	  1.��װ�����½�
	 * 	  2.��װ��Դ�ر�
	 * */
	//�ڼ��ص�ʱ����Ҫ��ʼ��Properties�е�����  Ӧ���Ǿ�̬��
	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	//��ΪҪ�ڳ�ʼ��֮ǰ����������Ҫʹ�þ�̬�Ĵ����
	static{
		//----------------����һ ֱ��ʹ�õ���Properties,ͨ��������ȡ�ļ�----------------
		//����һ�� properties ϵͳ�����Զ�����newһ������Ȼ���ٰ�����ж����
		//��Ϊ���ǰѹ��е�����д����һ��Properties�е��ļ��У������ڼ������ݵ�ʱ��Ӧ���������ķ�ʽ�����ݶ�������Ȼ��
		//�ڸ���Properties�����ԣ�������������load������Ȼ��ֵ������
		//1.����һ��Properties���� Properties��Ψһ��һ�����Ժ�����������Ķ���
		//Properties pro=new Properties();
		//������
//		try {
//			//��������
//			InputStream ins=new FileInputStream("src/properties/db.properties");
//			//���ص����Զ�����
//			pro.load(ins);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//-----------------������ ʹ��������� �ص㣺�ļ����˹��� ��ֻ�ܼ�����·���µ�
		//Ϊʲô���еڶ��ַ�����ԭ������д�����ļ�����jr����ʱ���ڵ����ʱ��������е�sr
		//·���Ͳ�����ڣ��ͻᵼ���ļ��Ҳ���
		//��ȡ��ǰ�ļ��µ��������
//		ClassLoader cl=JTool.class.getClassLoader();
//		//ͨ����������������������ļ��е�����
//		InputStream in=cl.getResourceAsStream("properties/db.properties");
//		try {
//			pro.load(in);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//��������֮���Ҫ�����ݵõ���Ȼ�������ı���
//		driver=pro.getProperty("DRIVER");
//		url=pro.getProperty("URL");
//		user=pro.getProperty("USER");
//		password=pro.getProperty("PASSWORD");
//		//��ȡ
		//--------------------������ʹ�ü򵥵����� ResourceBundle
		ResourceBundle rb=ResourceBundle.getBundle("properties.db");
		driver=rb.getString("DRIVER");
		url=rb.getString("URL");
		user=rb.getString("USER");
		password=rb.getString("PASSWORD");
		
	}
	
	/**
	 * @author �ǳ�
	 * ��װ���ݵ�����
	 * ����һ�����Ӷ���
	 * */
	
	public static Connection getConnection()
	{
//		Class.forName("com.mysql.jdbc.Driver");
//		//2.��������
//		 conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqldb4", "root", "199028");
		Connection conn=null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 
	 * @author �ǳ�
	 * @param conn
	 * @param st
	 * @param rs
	 * 
	 * ���������Ҫ���������ر���Դ��
	 * */
	public static void  closeSource(Connection conn, Statement st,ResultSet rs)
	{
		if(conn !=null)
			{
				try{
					conn.close();
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			if(st !=null){
				try{
					st.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(rs !=null)
			{
				try{
					rs.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
	}
}
