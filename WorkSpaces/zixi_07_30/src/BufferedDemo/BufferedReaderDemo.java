package BufferedDemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class BufferedReaderDemo {
	/*
	 * 读数据输入流
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		BufferedReader bReader=new BufferedReader(new FileReader("Z:\\aa.txt"));
//		int b=0;
//		//低效一个一个字节的去读
//		//读出来的字节，一个一个的都给了b
//		while((b=bReader.read())!=-1)
//		{
//			System.out.print((char)b);
//		}
//		bReader.close();
		//高效的读取方式，就是使用数组
		char[] ch=new char[1024];
		int b=0;
		while((b=bReader.read(ch))!=-1)
		{
			System.out.println(new String(ch,0,b));
		}
		bReader.close();
	}

}
