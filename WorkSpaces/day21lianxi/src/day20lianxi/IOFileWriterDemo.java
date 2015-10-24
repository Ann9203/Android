package day20lianxi;

import java.io.FileWriter;
import java.io.IOException;

public class IOFileWriterDemo {
	/*
	 * 写入文件也是字节符输出流
	 * */
	public static void main(String[] args) throws IOException {
		FileWriter fWriter=new FileWriter("a.txt");
		fWriter.write("Hello");
		fWriter.write("java");
		fWriter.flush();
		fWriter.close();
		//如果还要在文件中写入数据肿么办？？ 使用新的构造方法
		//FileWriter(string,boolean)
		FileWriter fw2=new FileWriter("a.txt",true);
		fw2.write("Hello\t\n");
		fw2.write("java\t\n");
		fw2.write("IO\t\n");
		fw2.write("Bayby\t\n");
		fw2.flush();
		fw2.close();
		
		
	}

}
