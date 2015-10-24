package com.itheima.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseDemo5 extends HttpServlet {

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
		 * 实现定时刷新的功能
		 * 其实也是设置一个消息头  Refresh
		 * */
		//设置响应正文的字符集
		//text/html;charset=UTF-8
		response.setContentType("text/html;charset=UTF-8");
		//定义字符输出流
		PrintWriter pw=response.getWriter();
		pw.write("登陆失败，2秒钟后即将跳转到登陆界面......");
		//下边的是获取字节输出流
//		ServletOutputStream ops=response.getOutputStream();
//		ops.wr
		
		//设置消息头
		response.setHeader("Refresh", "2;URL=/day08_responserequest/login.html");
	
		
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
