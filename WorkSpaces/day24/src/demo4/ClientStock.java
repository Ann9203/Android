package demo4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStock {
	/*
	 * 客户端负责传入数据，然后获得反馈
	 * 步骤：
	 * 	A:创建服务器端口】
	 * 	B: 键盘录入
	 * 	C:写入数据  同过管道写入数据
	 * 	D:接受反馈，通过管道接受反馈
 	 * 	E:读取数据
	 * */
	public static void main(String[] args) throws UnknownHostException, IOException {
		//创建客户端口
		//ServerSocket ss=new ServerSocket("192.168.22.129",80086);
		Socket socket=new Socket("192.168.22.30", 12306);
		//键盘录入数据
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
		//键盘获取数据，通过管道获取数据
		BufferedWriter bWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		//写入数据
		String lineString=null;
		while((lineString=bReader.readLine())!=null)
		{
			if("over".equals(lineString))
			{
				break;
				
			}
			bWriter.write(lineString);
			bWriter.newLine();
			bWriter.flush();
		}
		bWriter.close();
		//写入完数据，得到反馈
		BufferedReader bReader2=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String ling= bReader.readLine();
		System.out.println("server"+ling);
		bReader.close();
		bReader2.close();
		
	}

}
