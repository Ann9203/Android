package com.itheima.response;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseDemo3 extends HttpServlet {

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
		 * ������֤��
		 * */
		
		//1����ͼ��Ŀ�Ⱥ͸߶�
		
		int width=200;
		int height=25;
		//2.����һ��ͼ��
		BufferedImage img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//3.��ȡһ�����ʵĶ���
		Graphics g=img.getGraphics();
		//3.1���ñ�����ɫ
		g.setColor(Color.GRAY);
		//3.2��䱳��ɫ
		g.fillRect(1, 1, width-2, height-2);
		//3.3���ñ߿���ɫ
		g.setColor(Color.BLUE);
		//3.4���߿�
		g.drawRect(0, 0, width, height);
		//3.5��������
		g.setColor(Color.WHITE);
		//������Ҳ�������
		Random r=new Random();
		for(int i=0;i<10;i++)
		{
			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}
		//3.6�������ֵ���ɫ��������
		//����һ���������
		Font font=new Font("����",Font.BOLD,30);
		//��������
		g.setFont(font);
		g.setColor(Color.RED);
		//����ÿһ�����ֵ�λ��
		int size=30;
		for(int x=0;x<4;x++)
		{
			g.drawString(String.valueOf(r.nextInt(10)),size , 25);
			size+=30;
		}
		//4.���һ��ͼ��
		//����˵����1���ڴ�ͼ��2��ͼ�����չ����3���䵽����ȥ
		ImageIO.write(img, "jpg", response.getOutputStream());
		
				
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
