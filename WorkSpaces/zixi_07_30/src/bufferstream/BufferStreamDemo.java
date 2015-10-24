package bufferstream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.shape.Line;

public class BufferStreamDemo {
	/*
	 * 
	 * BufferWriter：
	 * 		public void newLine():根据系统平台写入行分隔符
	 *BufferReader:
	 *		public String readLine();一次读取一行的数据，但是不包含换行符
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		//写数据
//		BufferedWriter bWriter=new BufferedWriter(new FileWriter("u.txt"));
//		for (int i = 0; i < 5; i++) {
//			bWriter.write("hello"+i);
//			//可以写入行分隔符
//			bWriter.write("\r\n");
//			bWriter.newLine();
//			bWriter.flush();
//			
//		}
//		bWriter.close();
		//读取文件
		BufferedReader br=new BufferedReader(new FileReader("u.txt"));
		//这样的话只能读出两行数据
//		String line = br.readLine();
//		System.out.println(line);
//		line = br.readLine();
//		System.out.println(line);
//		line = br.readLine();
//		System.out.println(line);
//		line = br.readLine();
//		System.out.println(line);
//		String line=null;
//		while ((line=br.readLine())!=null) {
//			System.out.println(line);
//			
//		}
		String line=null;
		while((line=br.readLine())!=null)
		{
			System.out.println(line);
		}
		
		br.close();
		
		
	}

}
