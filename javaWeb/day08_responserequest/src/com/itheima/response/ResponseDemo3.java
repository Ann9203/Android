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
		 * 生成验证码
		 * */
		
		//1定义图像的宽度和高度
		
		int width=200;
		int height=25;
		//2.创建一副图像
		BufferedImage img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//3.获取一个画笔的对象
		Graphics g=img.getGraphics();
		//3.1设置背景颜色
		g.setColor(Color.GRAY);
		//3.2填充背景色
		g.fillRect(1, 1, width-2, height-2);
		//3.3设置边框颜色
		g.setColor(Color.BLUE);
		//3.4画边框
		g.drawRect(0, 0, width, height);
		//3.5画干扰线
		g.setColor(Color.WHITE);
		//干扰线也是随机数
		Random r=new Random();
		for(int i=0;i<10;i++)
		{
			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}
		//3.6设置数字的颜色，画数字
		//定义一个字体对象
		Font font=new Font("宋体",Font.BOLD,30);
		//设置字体
		g.setFont(font);
		g.setColor(Color.RED);
		//设置每一个数字的位置
		int size=30;
		for(int x=0;x<4;x++)
		{
			g.drawString(String.valueOf(r.nextInt(10)),size , 25);
			size+=30;
		}
		//4.输出一幅图画
		//参数说明：1是内存图像，2，图像的扩展名，3，输到哪里去
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
