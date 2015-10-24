package demo4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStock {
	/*
	 * 服务器接受数据
	 * */
	public static void main(String[] args) throws IOException {
		
		//定义端口
		ServerSocket ss=new ServerSocket(12306);
		Socket socke=ss.accept();
		//接受数据进行读取
		BufferedWriter bWriter=new BufferedWriter(new OutputStreamWriter(socke.getOutputStream()));
		BufferedReader bReader=new BufferedReader(new InputStreamReader(socke.getInputStream()));
		String line=null;
		while((line=bReader.readLine())!=null)
		{
			bWriter.write(line.toUpperCase());
			bWriter.newLine();
			bWriter.flush();
		}
		//返回数据
		System.out.println("数据成功接收");
		
	
	}

}
