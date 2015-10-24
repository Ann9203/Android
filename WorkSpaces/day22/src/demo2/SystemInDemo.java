package demo2;

import java.io.InputStream;
import java.io.OutputStream;

public class SystemInDemo {
	/*
	 * 键盘录入，然后写入到文档中
	 * */
	public static void main(String[] args) {
		//inputStream表示的是输入流，输入流就是显示在控制台上
		//输出流就是写入在文件的流
		InputStream inputStream=System.in;//字节流
		System.out.println(inputStream);
		OutputStream opsOutputStream=System.out;//输出流
		System.out.println(opsOutputStream);//底层就是打印流
		
		
	}

}
