package FileInPutStreamDemo;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutStreamDemo {
	/*
	 * 
	 * �����Ҫ���Ƕ�ȡ�ֽ������ļ�
	 * 
	 * */

	public static void main(String[] args) throws IOException {
		FileOutputStream fod=new FileOutputStream("a.txt");
		String string="���ǲ���";
		byte[]by=string.getBytes();
		fod.write(by);
		fod.flush();
		fod.close();
		
		
	}
}
