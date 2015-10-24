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
		 * �������ģ���װ��javabean��
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
	////ʹ��apache��beanunitʵ�ַ�װ���������
	private void test8(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				UserA us=new UserA();
				//����ֱ��ʹ��Apache��˾����װ��beanunit
				
				try {
					//BeanUtils.populate(us,request.getParameterMap());
					BeanUtils.populate(us, request.getParameterMap());
				} catch (Exception e) {
			
					e.printStackTrace();
				}
				System.out.println(us.toString());
				
			}
	//����+��ʡ����   							---�õ���������ȷ������
	private void test7(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				UserA us=new UserA();
				Map<String,String[]>mp=request.getParameterMap();
				for(Map.Entry<String, String[]> entry:mp.entrySet())
				{
					String name=entry.getKey();
					String[] value=entry.getValue();
					//��ȡ����������
					try {
						PropertyDescriptor pd=new PropertyDescriptor(name,UserA.class);
						//��ȡ���Ե�get����
						Method md=pd.getWriteMethod();
						//�жϻ�ȡ����Valueֵ�ó����Ƿ����1���������1�Ļ���Ҫ������飬Ҳ������ӵ��Ƕ���
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
	//�������ģ�ʹ����Map���ϵķ�ʽ
		private void test6(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
				{
					Map<String,String[]>mp=request.getParameterMap();
					for(Map.Entry<String, String[]> entry:mp.entrySet())
					{
						String name=entry.getKey();
						String[] value=entry.getValue();
						//�����toString ��ӡ���ǵ���ֵ
						//Ҫ�õ�����Arrays.toString����
						System.out.println(name+","+Arrays.toString(value));
					}
			
				}
	//����+��ʡ��Ҳ����һ��javabean��ʵ���������+get+set���ƣ������һ����ʡ���ƣ�  --û��ʹ��ȷ������
	private void test5(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				//1.ͨ��ö�ٻ�ȡ���е����ƺ�ֵ
				Enumeration<String>es=request.getParameterNames();
				User user=new User();
				while(es.hasMoreElements())
				{
					String name=es.nextElement();
					String value=request.getParameter(name);
					//ʹ�������������ȡ���Ե����� get Set����
					//PropertyDescriptor
					try {
						//����˵��;��һ���������������ƵĲ������ڶ�������������ֽ����ļ�
						PropertyDescriptor pd=new PropertyDescriptor(name,User.class);
						//�õ������Ե�������֮��Ȼ��ͨ����������ȡ�����getMethod
						Method m=pd.getWriteMethod();
						m.invoke(user, value);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(user.toString());
				
		
			}
	
	//�����ַ�ʽ��ͨ��ö�ٻ�ȡ���Ƶ����� -------------û��ʹ������ȷ������
	//�����ʽ���ǽ���ȡ����������ӵ�Javabean�У�Ҳ����ʵ��javaBean��
	//���ַ�ʽҲ��ȡ��һ������Ͳ���һ�����е�̫��е����ˢ�Ṥ
	private void test4(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				//��ȡ���е����Ƶ�ö��
		//ע�ⲻҪ�ͻ�ȡ���Եĸ����
				
				User use=new User();
				use.setUsername(request.getParameter("username"));
				use.setPassword(request.getParameter("password"));
				use.setSex(request.getParameter("sex"));
				System.out.println(use.toString());
				
		
			}
	//�����ַ�ʽ����ͨ��ö�ٵķ�ʽȡ����Ȼ���ڴ�ӡ����   -------û��ȷ�������ʱ��
	private void test3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				//��ȡ���е����Ƶ�ö��
		//ע�ⲻҪ�ͻ�ȡ���Եĸ����
				Enumeration<String>names=request.getParameterNames();
				while(names.hasMoreElements())
				{
					String name=names.nextElement();
					String value=request.getParameter(name);
					System.out.println(name+","+value);
					
				}
		
			}
	
	
	//2.�������ģ�ͨ��key=value ����ʽ����������-----------��ȷ�������ʱ��
	private void test2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
				//��Ϊ����ȷ�����룬�����������һ�������ƣ����Զ���һ������
				String username=request.getParameter("username");
				String[] password=request.getParameterValues("password");
				String sex=request.getParameter("sex");
				System.out.println(username+","+Arrays.toString(password)+","+sex);
		
			}
	
	//1.�������ģ�ͨ��key value����ʽ����������   -----û��ȷ�������ʱ��
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
