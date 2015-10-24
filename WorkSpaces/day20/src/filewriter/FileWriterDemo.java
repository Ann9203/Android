package filewriter;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
	
	/*
	 * Io流
	 * 		字符流
	 * 再创建的文件中，写入一个数据
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		//创建流对象，
		//流对象可以自动调用系统功能创建对象
		FileWriter fileWriter=new FileWriter("a.txt");
		//在流中写入数据
		fileWriter.write("Hello,EveryBody");
		fileWriter.flush();
		fileWriter.close();
		
		
	}
	
}
