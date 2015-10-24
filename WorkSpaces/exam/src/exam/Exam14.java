package exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Exam14 {
	/*
	 * 定义一个文件输入流，调用read(byte[] b)方法将
	 * exercise.txt文件中的所有内容打印出来(byte数组的大小限制为5)。
	 * */
	public static void main(String[] args) throws FileNotFoundException {
		//定义文件输入流
		BufferedInputStream bInputStream=new BufferedInputStream(new FileInputStream("Z:\\ccc.txt"));
		//定义一个byte数组
		byte[] by=new byte[5];
		int leng=0;
		try {
			while((leng=bInputStream.read(by))!=-1)
			{
				System.out.print(new String(by,0,leng));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
