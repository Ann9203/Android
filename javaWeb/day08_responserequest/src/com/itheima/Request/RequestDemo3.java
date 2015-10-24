package com.itheima.Request;


import java.beans.PropertyDescriptor;
import java.io.IOException;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itheima.domain.User;
import com.itheima.domain.UserA;

public class RequestDemo3 extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 请求正文，封装到javabean中
		 * 
		 * */
		//test1(request,response);
		//test2(request,response);
		//test3(request,response);
		//test4(request,response);
		//test5(request,response);
		//test6(request,response);
		//test7(request,response);
		test8(request,response);
		
		
	}
	////使用apache的beanunit实现封装请求的正文
	private void test8(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				UserA us=new UserA();
				//可以直接使用Apache公司给封装的beanunit
				
				try {
					//BeanUtils.populate(us,request.getParameterMap());
					BeanUtils.populate(us, request.getParameterMap());
				} catch (Exception e) {
			
					e.printStackTrace();
				}
				System.out.println(us.toString());
				
			}
	//反射+内省机制   							---用到的是重新确认密码
	private void test7(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				UserA us=new UserA();
				Map<String,String[]>mp=request.getParameterMap();
				for(Map.Entry<String, String[]> entry:mp.entrySet())
				{
					String name=entry.getKey();
					String[] value=entry.getValue();
					//获取属性描述其
					try {
						PropertyDescriptor pd=new PropertyDescriptor(name,UserA.class);
						//获取属性的get方法
						Method md=pd.getWriteMethod();
						//判断获取到的Value值得长度是否大于1，如果大于1的话就要添加数组，也就是添加的是对象
						if(value.length>1)
						{
							md.invoke(us, (Object)value);
						}else
						{
							md.invoke(us, Arrays.toString(value));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				System.out.println(us.toString());
		
			}
	//请求正文，使用了Map集合的方式
		private void test6(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
				{
					Map<String,String[]>mp=request.getParameterMap();
					for(Map.Entry<String, String[]> entry:mp.entrySet())
					{
						String name=entry.getKey();
						String[] value=entry.getValue();
						//这里的toString 打印的是地制值
						//要用到的是Arrays.toString（）
						System.out.println(name+","+Arrays.toString(value));
					}
			
				}
	//反射+内省（也就是一个javabean的实体类的属性+get+set机制，这就是一个内省机制）  --没有使用确认密码
	private void test5(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				//1.通过枚举获取所有的名称和值
				Enumeration<String>es=request.getParameterNames();
				User user=new User();
				while(es.hasMoreElements())
				{
					String name=es.nextElement();
					String value=request.getParameter(name);
					//使用属性描述其获取属性的名称 get Set方法
					//PropertyDescriptor
					try {
						//参数说明;第一个参数是属性名称的参数，第二个参数是类的字节码文件
						PropertyDescriptor pd=new PropertyDescriptor(name,User.class);
						//得到该属性的描述器之后，然后通过这个对象获取对象的getMethod
						Method m=pd.getWriteMethod();
						m.invoke(user, value);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(user.toString());
				
		
			}
	
	//第四种方式：通过枚举获取名称的数组 -------------没有使用重新确认密码
	//这个方式就是将获取到的数字添加到Javabean中，也就是实体javaBean中
	//这种方式也是取到一条请求就操作一条，有点太机械化，刷漆工
	private void test4(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				//获取所有的名称的枚举
		//注意不要和获取属性的搞混了
				
				User use=new User();
				use.setUsername(request.getParameter("username"));
				use.setPassword(request.getParameter("password"));
				use.setSex(request.getParameter("sex"));
				System.out.println(use.toString());
				
		
			}
	//第三种方式就是通过枚举的方式取出来然后在打印出来   -------没有确认密码的时候
	private void test3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				//获取所有的名称的枚举
		//注意不要和获取属性的搞混了
				Enumeration<String>names=request.getParameterNames();
				while(names.hasMoreElements())
				{
					String name=names.nextElement();
					String value=request.getParameter(name);
					System.out.println(name+","+value);
					
				}
		
			}
	
	
	//2.请求正文，通过key=value 的形式来请求正文-----------有确认密码的时候
	private void test2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				//因为有了确认密码，这个和密码是一样的名称，所以定义一个数组
				String username=request.getParameter("username");
				String[] password=request.getParameterValues("password");
				String sex=request.getParameter("sex");
				System.out.println(username+","+Arrays.toString(password)+","+sex);
		
			}
	
	//1.请求正文，通过key value的形式来请求正文   -----没有确认密码的时候
	private void test1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sex=request.getParameter("sex");
		System.out.println(username+","+password+","+sex);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
