package FileInPutStreamDemo;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutStreamDemo {
	/*
	 * 
	 * 这个主要就是读取字节流的文件
	 * 
	 * */

	public static void main(String[] args) throws IOException {
		FileOutputStream fod=new FileOutputStream("a.txt");
		String string="传智播客";
		byte[]by=string.getBytes();
		fod.write(by);
		fod.flush();
		fod.close();
		
		
	}
}
