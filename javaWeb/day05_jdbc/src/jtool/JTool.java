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
	 * 	根据封装的特性，
	 * 	  1.封装数据谅解
	 * 	  2.封装资源关闭
	 * */
	//在加载的时候先要初始化Properties中的内容  应该是静态的
	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	//因为要在初始化之前加载这样就要使用静态的代码块
	static{
		//----------------方法一 直接使用的是Properties,通过流来读取文件----------------
		//方法一： properties 系统的属性对象，先new一个对象，然后再吧属性卸下来
		//因为我们把公有的数据写到了一个Properties中的文件中，所以在加载数据的时候应该是用流的方式把数据读出来，然后
		//在根据Properties的属性，将读出的数据load下来，然后赋值给变量
		//1.定义一个Properties对象 Properties是唯一的一个可以和流结合起来的对象
		//Properties pro=new Properties();
		//输入流
//		try {
//			//读入数据
//			InputStream ins=new FileInputStream("src/properties/db.properties");
//			//加载到属性对象中
//			pro.load(ins);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//-----------------方法二 使用类加载器 特点：文件不宜过大 ，只能加在类路径下的
		//为什么会有第二种方法：原因当我们写的类文件生成jr包的时候，在导入的时候，这个包中的sr
		//路径就不会存在，就会导致文件找不到
		//获取当前文件下的类加载器
//		ClassLoader cl=JTool.class.getClassLoader();
//		//通过类加载器，来加载属性文件中的内容
//		InputStream in=cl.getResourceAsStream("properties/db.properties");
//		try {
//			pro.load(in);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//有了数据之后就要将数据得到，然后给定义的变量
//		driver=pro.getProperty("DRIVER");
//		url=pro.getProperty("URL");
//		user=pro.getProperty("USER");
//		password=pro.getProperty("PASSWORD");
//		//获取
		//--------------------方法三使用简单的做法 ResourceBundle
		ResourceBundle rb=ResourceBundle.getBundle("properties.db");
		driver=rb.getString("DRIVER");
		url=rb.getString("URL");
		user=rb.getString("USER");
		password=rb.getString("PASSWORD");
		
	}
	
	/**
	 * @author 星尘
	 * 封装数据的连接
	 * 返回一个连接对象
	 * */
	
	public static Connection getConnection()
	{
//		Class.forName("com.mysql.jdbc.Driver");
//		//2.创建连接
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
	 * @author 星尘
	 * @param conn
	 * @param st
	 * @param rs
	 * 
	 * 这个方法主要就是用来关闭资源的
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
