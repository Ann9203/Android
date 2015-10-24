package demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class InetSend {
	/*
	 * 网络通讯，网络之间的编程
	 *	思路:
	 *		A: 定义端口
	 *		B: 定义数组规定传递的数的长度
	 *		C：打包数据
	 *		D: 发送
	 *		E: 释放资源
	 * */
	public static void main(String[] args) throws IOException {
		//创建发送端的服务对象，用这个对象进行发送数据
		DatagramSocket ds=new DatagramSocket();
		//InetAddress没有构造方法
		//Scanner scanner=new Scanner(System.in);
		//读取资源
		BufferedReader bReader=new BufferedReader(new  InputStreamReader(System.in));
		String string=null;
		while((string=bReader.readLine())!=null)
		{
			if("886".equals(string))
			{
				break;
			}
			InetAddress inetAddress=InetAddress.getByName("192.168.22.129");
			byte[] by=string.getBytes();
			
			//创建数据包
			DatagramPacket daPacket=new DatagramPacket(by, by.length, inetAddress, 12345);
			ds.send(daPacket);
		}
		//释放资源
		
		ds.close();
		
	}

}
