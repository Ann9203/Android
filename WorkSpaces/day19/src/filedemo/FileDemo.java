package filedemo;

import java.io.File;
import java.io.IOException;

public class FileDemo {
	/*
	 * 如果我们想在下面这个目录中创建文件a.txt,
	 *  那么该如何实现?F:\\hello\\world\\Java\\a.txt 创建一个文件
	 *  思路：
	 *  	先创建文件夹，再再文件夹中创建文件
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		File file1=new File("Z:"
				+ "\\hello\\world\\java");
		System.out.println(file1.mkdirs());
		File file2=new File(file1, "a.txt");
		file2.createNewFile();
		
	}
	

}
