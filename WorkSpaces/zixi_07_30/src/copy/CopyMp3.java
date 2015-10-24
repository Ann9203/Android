package copy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyMp3 {
	/*
	 * 把Z盘目录下的一首歌复制到另一个文件夹下“陈奕迅 - 十年1”
	 * 四种方式
	 * 	A:就是低效率的字节读取 InputStream
	 * 	B:高效率的字节读取		OutPutStream
	 * 	C:低效率的字节读取  BufferReader
	 * 	D:高效率的字节读取 BufferWriter
	 * 	
	 * */
	public static void main(String[] args) throws IOException {
		//metho1();
		//metho1();
		//method2();
		//method3();
		method4();
	}
	//第一种方式
	public static void metho1() throws IOException

	{
		
		FileInputStream fStream=new FileInputStream("Z:\\陈奕迅 - 十年.mp3");
		FileOutputStream fOutputStream=new FileOutputStream("X:\\what.mp3");
		
		int b=0;
		long startTime=System.currentTimeMillis();
		while((b=fStream.read())!=-1)
		{
			//fOutputStream.wait(b);
			fOutputStream.write(b);
		}
		fStream.close();
		fOutputStream.close();
		long endTime=System.currentTimeMillis();
		System.err.println("耗时："+(endTime-startTime));
	}
	public static void method2() throws IOException
	{
		FileInputStream fis=new FileInputStream("Z:\\陈奕迅 - 十年.mp3");
		FileOutputStream fos=new FileOutputStream("X:\\hh.mp3");
		byte[] b=new byte[1024];
		int leng=0;
		long startTime=System.currentTimeMillis();
		while((leng=fis.read(b))!=-1)
		{
			fos.write(b,0,leng);
		}
		fis.close();
		fos.close();
		long endTime=System.currentTimeMillis();
		System.err.println("耗时："+(endTime-startTime));	
		
	}
	//方法3，通过 使用Buffered缓冲区的类
	//字节存储的文件只能用字节流来存储
	public static void method3() throws IOException
	{
		//BufferedReader bReader=new BufferedReader(new FileOutputStream("Z:\\陈奕迅 - 十年.mp3"));
		//BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\ee.mp3"));
		BufferedInputStream bReader=new BufferedInputStream(new FileInputStream("Z:\\陈奕迅 - 十年.mp3"));
		BufferedOutputStream bWriter=new BufferedOutputStream(new FileOutputStream("X:\\ee.mp3"));
		int b=0;
		long startTime=System.currentTimeMillis();
		while((b=bReader.read())!=-1)
		{
			bWriter.write(b);
		}
		bReader.close();
		bWriter.close();
		long endTime=System.currentTimeMillis();
		System.err.println("耗时："+(endTime-startTime));	
	}
	//方法4：高效的方法
	public static  void method4() throws IOException
	{
//		BufferedReader reader=new BufferedReader(new FileReader("Z:\\陈奕迅 - 十年.mp3"));
//		BufferedWriter writer=new BufferedWriter(new FileWriter("x:\\ff.mp3"));
		//Buffered是缓冲区，后边跟的是字符流或者是字节流，
		BufferedInputStream reader=new BufferedInputStream(new FileInputStream("Z:\\陈奕迅 - 十年.mp3"));
		BufferedOutputStream writer=new BufferedOutputStream(new FileOutputStream("X:\\ff.mp3"));
		int b=0;
		//char[] ch=new char[1024];
		byte[] by=new byte[1024];
		long startTime=System.currentTimeMillis();
		while((b=reader.read(by))!=-1)
		{
			writer.write(by, 0, b);
		}
		reader.close();
		writer.close();
		long endTime=System.currentTimeMillis();
		System.err.println("耗时："+(endTime-startTime));	
		
		
	}
}
