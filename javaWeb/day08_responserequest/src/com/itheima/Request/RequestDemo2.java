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
		
		//设置字符输出流，打印到网页中
		PrintWriter pw=response.getWriter();
		/*
			 * 获取请求消息头
			 * 
			 * */
		//1.根据名称后去消息头的值，一个值一个名称
		//String value=request.getHeader("Accept");
		//pw.write(value);
		//2.根据头的名称，获取值，一个key对应多个Value值
//		Enumeration<String> enu=request.getHeaders("Accept");
//		while(enu.hasMoreElements())
//		{
//			String value1=enu.nextElement();
//			System.out.println(value1);
//			
//		}
		//3.获取所有的头的名称，然后再获取他的值
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
