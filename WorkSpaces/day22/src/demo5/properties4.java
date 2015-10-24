package demo5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;

import demo3.printIoDemo;

public class properties4 {
	/*
	 * 结合的方式就是：参数传递IO流对象
	 * 	怎么使用：
	 * 		public void  list(PrintStream out); //传入字节输出流
	 * 		public void lis(PrintWriter out); //传入字符输出流
	 * */
	public static void main(String[] args) throws IOException {
		//传入系统的文件
		Properties properties=System.getProperties();
		//写入文件中
//		BufferedWriter bWriter=new BufferedWriter(new FileWriter("system.txt"));
//		//读取Properties中的元素，然后意义写入文件红
//		Set<String>key=properties.stringPropertyNames();
//		for (String string : key) {
//			String  vlue=properties.getProperty(string);
//			bWriter.write(string);
//			bWriter.write("=");
//			bWriter.write(vlue);
//			bWriter.newLine();
//		}
//		bWriter.close();
		//使用list方法
//		PrintWriter pWriter=new PrintWriter("aa.txt");
//		//list方法就是将属性列表输出到指定的流
//		properties.list(pWriter);
//		pWriter.close();
		PrintWriter pw=new PrintWriter("bb.txt");
		properties.list(pw);
		pw.close();
	}
}
