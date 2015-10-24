package reader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import sun.security.util.Length;

public class CopyFile2 {
	/*
	 * 今天脑子有点冒泡
	 * 复制文件的高效率的写法
	 * */
	public static void main(String[] args) throws IOException {
		FileReader fd=new FileReader("Z:\\aa.txt");
		FileWriter fw=new FileWriter("X:\\u.txt");
		char[]ch=new char[1024];
		int leng=0;
		//这个就是定义一个字符数组，然后将数组的长度定义为1024的倍数
		//在读取的时候，就是按照一个数组的长度来读取，返回的整个字符的长度
		while((leng=fd.read(ch))!=-1)
		{
			fw.write(ch, 0, leng);
		}
		//复制完后，要关闭流文件
		fd.close();
		fw.close();
		
	}
	

}
