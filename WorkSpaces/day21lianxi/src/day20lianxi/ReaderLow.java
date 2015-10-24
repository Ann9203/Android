package day20lianxi;
import java.io.FileReader;
import java.io.IOException;

import sun.misc.FDBigInteger;

public class ReaderLow {
	/*
	 * 字符输入流的低效的方法
	 * 	就是一个字符一个字符的来读取
	 * */
	public static void main(String[] args) throws IOException {
		FileReader rd=new FileReader("Z:\\aa.txt");
		//低效率的方式读取文件
		int leng=0;
		//int leng=rd.read();SSSSS
		//这个读取文件rd.read返回的是int类型的值
		//所以读取完后直接把int类型的的值转换成char类型就可以
		//不需要再次读取，这样的话会造成缺少数据
//		while ((leng=rd.read())!=-1) {
//			//System.out.println(rd.read));
//			//char ch=(char)rd.read();
//		System.out.print((char)leng);
//			
//		}
//		rd.close();
//		while((leng=rd.read())!=-1)
//		{
//			System.out.println((char)leng);
//		}
		while((leng=rd.read())!=-1)
		{
			System.out.println((char)leng);
		}
		rd.close();
		
	}

}
