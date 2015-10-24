package printdemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintDemo {
	public static void main(String[] args) throws IOException {
		/*
		 * 数据源：
		 	PrintWriterDemo.java	--	BufferedReader
		目的地：
		 	Copy.java		--	PrintWriter改写

		 * */
		PrintWriter pWriter=new PrintWriter(new FileWriter("X:\\ccc.txt"), true);
		
		BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
		
		char[] chs=new char[1024];
		int leng=0;
		while((leng=bReader.read(chs))!=-1)
		{
			pWriter.println();
		}
//		int leng=0;
////		while((leng=bReader.read(chs))！=-1)
////		{
////			
////		}
//		while((leng=bReader.read(chs))!=-1)
//		{
//			pWriter.println();
//		}		
		pWriter.close();
		bReader.close();
			
	}

}
