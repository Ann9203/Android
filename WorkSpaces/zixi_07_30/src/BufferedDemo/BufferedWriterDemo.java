package BufferedDemo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterDemo {
/*
 * BufferedWriter这种流称之为缓冲流，他只提供数据的缓冲，但是读写的数据还要靠别人
 * 这就相当于一个装饰模式，提供附加功能
 * 缓冲区只提供缓冲的功能，没有真正的读写
 * 基本流、：
 * 	可以直接进行读写数据
 * 高级流：
 * 	站在基本流的基础上，提供一些特殊的功能
 * 
 * */
	public static void main(String[] args) throws IOException {
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("u.txt"));
		bWriter.write("Hello,beijing");
		bWriter.flush();
		bWriter.close();
	}
}
