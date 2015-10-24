
package exam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Exam8 {
/*
 * 使用带缓冲功能的字节流复制文件。
 * */
	public static void main(String[] args) throws IOException {
		//定义数据源
		BufferedReader br=new BufferedReader(new FileReader("Z:\\ccc.txt"));
		//初始化目的地源对象
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("stu.txt"));
		//定义字符串
		String leng=null;
		//将读出的字符写入到字符串中，直到读完为值
		while((leng=br.readLine())!=null)
		{
			bWriter.write(leng);
			//刷新流
			bWriter.flush();
		}
		bWriter.close();
		
	}
}
