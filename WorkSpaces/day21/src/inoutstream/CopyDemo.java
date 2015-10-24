package inoutstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyDemo {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("Z:\\Day21知识点总结.txt");
		FileOutputStream fos=new FileOutputStream("X:\\x.txt");
		byte[] by=new byte[1024];
		int leng=0;
		while((leng=fis.read(by))!=-1)
		{
			fos.write(by,0,leng);
		}
		fos.close();
		fis.close();
	}
}
