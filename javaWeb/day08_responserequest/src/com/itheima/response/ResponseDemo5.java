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
		 * ʵ�ֶ�ʱˢ�µĹ���
		 * ��ʵҲ������һ����Ϣͷ  Refresh
		 * */
		//������Ӧ���ĵ��ַ���
		//text/html;charset=UTF-8
		response.setContentType("text/html;charset=UTF-8");
		//�����ַ������
		PrintWriter pw=response.getWriter();
		pw.write("��½ʧ�ܣ�2���Ӻ󼴽���ת����½����......");
		//�±ߵ��ǻ�ȡ�ֽ������
//		ServletOutputStream ops=response.getOutputStream();
//		ops.wr
		
		//������Ϣͷ
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
