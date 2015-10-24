package com.itheima.Request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo2 extends HttpServlet {

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
		
		//�����ַ����������ӡ����ҳ��
		PrintWriter pw=response.getWriter();
		/*
			 * ��ȡ������Ϣͷ
			 * 
			 * */
		//1.�������ƺ�ȥ��Ϣͷ��ֵ��һ��ֵһ������
		//String value=request.getHeader("Accept");
		//pw.write(value);
		//2.����ͷ�����ƣ���ȡֵ��һ��key��Ӧ���Valueֵ
//		Enumeration<String> enu=request.getHeaders("Accept");
//		while(enu.hasMoreElements())
//		{
//			String value1=enu.nextElement();
//			System.out.println(value1);
//			
//		}
		//3.��ȡ���е�ͷ�����ƣ�Ȼ���ٻ�ȡ����ֵ
		Enumeration<String> enu=request.getHeaderNames();
		while(enu.hasMoreElements())
		{
			String key=enu.nextElement();
			String value2=request.getHeader(key);
			System.out.println(key+" : "+value2);
		}
		
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
