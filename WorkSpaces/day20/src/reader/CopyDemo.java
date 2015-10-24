package reader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyDemo {
	public static void main(String[] args) throws IOException {
		/*
		 * 
		 * //如果D盘下有一个文件a.txt, 我们想把它复制到E盘下,并且改名copy.txt为这个如何做?
		 * 步骤：
		 * 		A:先把文件读出来
		 * 			read(char ch)//这个的意思就是把读出来的东西存入到chr数组中区
		 * 			char[] ch=new char[6]//这个意思就是说。。6个6个字节的读，然后存入到chr数组中
		 * 		B：文件读出来后，然后直接在写入到指定的目录的txt文本下
		 * */
		//创建一个读的文件
		FileReader fd=new FileReader("Z:\\aa.txt");
		//创建一个要写的。txt文件
		FileWriter fw=new FileWriter("X:\\c.txt");
		//创建一个字符数组，长度是1024的整数倍
		char[] ch=new char[1024];
		int length=0;
		//一下可以简写
		//length=fd.read
		//while(length!=-1	)
		//吧数字读入到数组中
		
		 // fd.read(ch);
		while((length=fd.read(ch))!=-1)
		{
			//写入数据			
			fw.write(ch,0,length);
			//fd.read(ch);
		}
		fd.close();
		fw.close();
		
		
		
	}

}
