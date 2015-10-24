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
		 * ��������ṩ��Ϣ
		 * 	//1.�����ߵ���Ϣ
		String remoteIP = request.getRemoteAddr();//������ip
		String remoteHost = request.getRemoteHost();//����������
		int remotePort = request.getRemotePort();//�����߶˿�   �Ǳ仯��
		//2.������Ϣ
		String localIP = request.getLocalAddr();//����ip
		String localName = request.getLocalName();//���صļ������
		int localPort = request.getLocalPort();//���صĶ˿�	�ǹ̶���
		//3.������Դ��Ϣ
		String uri = request.getRequestURI();
		String url = request.getRequestURL().toString();
		 * */
		//�����ߵ���Ϣ
		String remoteIp=request.getRemoteAddr();//�����ߵ�IP
		String remoteHost=request.getRemoteHost();//�����ߵ�����
		int remotePort=request.getRemotePort();//�����ߵĶ˿ںţ�����˿ڸ��ݲ�ͬ�����в�ͬ�Ķ˿ں��Ǳ仯��
		//���ص���Ϣ
		String localIP=request.getLocalAddr();//��ȡ���ص�IP
		String localName=request.getLocalName();//��ȡ���ؼ����������
		int localPort=request.getLocalPort();//��ȡ���ؼ�����Ķ˿ں�
		
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
