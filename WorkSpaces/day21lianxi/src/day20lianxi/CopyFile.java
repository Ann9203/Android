package day20lianxi;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFile {
	/*
	 * Copy 文件的高效的方式
	 * 		A:就是通过数组来写入文件中
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		//定义一个读取的文件
		FileReader rd=new FileReader("Z:\\aa.txt");
		//定义一个写入的文件
		FileWriter fw=new FileWriter("X:\\c.txt");
		//定义一个数组char类型的
//		char[] ch=new char[1024];
//		int length=0;
//		while ((length=rd.read(ch))!=-1)
//		{
//			//读取的文件要写入到新的.txt中
//			fw.write(ch, 0, length);
//		}
//		rd.close();
//		fw.close();
		char[] ch=new char[1024];
		int length=0;
		while((length=rd.read(ch))!=-1)
		{
			fw.write(ch, 0, length);
		}
		fw.close();
		rd.close();
		
		
	}

}
