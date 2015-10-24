package demo3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class printIoDemo {
	/*
	 * printStream:字节打印流
	 * printWriter：字符打印流
	 * 打印流的特点：
	 * 		A:可以写入任意类型的数据
	 * 		B:可以自动刷新，必须先启动，并且使用println,printf以及format方法才
	 * 			可以使用自动刷新哦功能功能
	 		C:可以直接对文件进行写入
	 				哪些流对象可以直接对文件进行操作？
	 					看构造方法，是否有同事接受File和String类型的参数
	 				注意：打印流只写数据，不读取数据
	 * */
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter pWriter=new PrintWriter("ww.txt");
		pWriter.println("Hello");
		pWriter.print("nihao");
		pWriter.flush();
		pWriter.close();
		
	}

}
