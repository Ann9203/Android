package FileInPutStreamDemo;

import java.io.FileInputStream;
import java.io.IOException;
public class FileInPutDemo {
	/*
	 * FileInPutStream：专门操作字节的输入流，专门用于读取数据中的文件。主要用于读取原始的字节流，
	 * 读取字符流的主要是FileReader
	 * */
	public static void main(String[] args) throws IOException {
		
		FileInputStream fp=new FileInputStream("Z:\\aa.txt");
		//记录读取的次数
//		int b=0;
//		while((b=fp.read())!=-1)
//		{
//			//读出来的都是一个一个的字节
//			//所以中文的话就不能读出来了，就会乱码
//			System.out.print((char)b);
//		}
		//高效的读取方式
		
//		byte[] by=new byte[1024];
//		int b=0;
//		while((b=fp.read(by))!=-1)
//		{
//			System.out.println(new String(by, 0,b));
//		}
//		
		byte[] by=new byte[1024];
		int b=0;
		while((b=fp.read(by))!=-1)
		{
			System.out.println(new String(by,0,b));
		}
		//总是忘记了释放资源
	
		fp.close();
		
	}

}
