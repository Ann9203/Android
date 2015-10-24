package demo3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintCopyDemo {
	/*
	 * 
	 * 使用打印流进行文本文件的复制
	 * */
	public static void main(String[] args) throws IOException {
//		//创建数据源
//		BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
//		//创建目的地数据源
//		PrintWriter pWriter=new PrintWriter("hh.txt");
//		String string=null;
//		while((string=bReader.readLine())!=null)
//		{
//			//启动自动刷新的功能
//			pWriter.println(string);
//			
//		}
//		pWriter.close();
//		bReader.close();
		// 创建数据源,只能是写入数据，没有读取数据的功能
		PrintWriter pWriter=new PrintWriter("cc.txt");
		//创建数据源
		BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
		String string=null;
		while((string=bReader.readLine())!=null)
		{
			pWriter.println(string);
		}
		pWriter.close();
		bReader.close();
		
	}

}
