package com.itheima.Request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo1 extends HttpServlet {

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
		 * 请求对象提供信息
		 * 	//1.来访者的信息
		String remoteIP = request.getRemoteAddr();//来访者ip
		String remoteHost = request.getRemoteHost();//来访者主机
		int remotePort = request.getRemotePort();//来访者端口   是变化的
		//2.本地信息
		String localIP = request.getLocalAddr();//本地ip
		String localName = request.getLocalName();//本地的计算机名
		int localPort = request.getLocalPort();//本地的端口	是固定的
		//3.请求资源信息
		String uri = request.getRequestURI();
		String url = request.getRequestURL().toString();
		 * */
		//来访者的信息
		String remoteIp=request.getRemoteAddr();//来访者的IP
		String remoteHost=request.getRemoteHost();//来访者的主机
		int remotePort=request.getRemotePort();//来访者的端口号，这个端口根据不同的人有不同的端口号是变化的
		//本地的信息
		String localIP=request.getLocalAddr();//获取本地的IP
		String localName=request.getLocalName();//获取本地计算机的名称
		int localPort=request.getLocalPort();//获取本地计算机的端口号
		
		System.out.println("remoteIp "+remoteIp);
		System.out.println("remoteHost "+remoteHost);
		System.out.println("remotePort "+remotePort);
		System.out.println("localIP "+localIP);
		System.out.println("localName "+localName);
		System.out.println("localPort "+localPort);
		//System.out.println();
		
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
