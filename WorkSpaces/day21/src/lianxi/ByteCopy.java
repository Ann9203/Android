package lianxi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class ByteCopy {
	/*
	 * 读取字节的方式有四种
	 * 			高效：一个字节一个字节的来读  
	 * 					一个数组一个数组的进行读取
	 * 			低效：一个字节一个字节的来读
	 * 					一个数组一个数组的进行读取
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		LowCopy();
		LowShuCopy();
		HightCopy();
		HightShuCopy();
		
	}
	//低效的读取字节文件
	public static void LowCopy()throws IOException
	{
			FileInputStream fiStream=new FileInputStream("Z:\\rr.jpeg");
			FileOutputStream fos=new FileOutputStream("X:\\xx.jpeg");
			
			int by=0;
			while((by=fiStream.read())!=-1)
			{
				fos.write(by);
				fos.flush();
			}
			fos.close();
			fiStream.close();
	}
	//低效率的字节数组读取字节文件
	public static void LowShuCopy()throws IOException
	{
		FileInputStream fis=new FileInputStream("Z:\\rr.jpeg");
		FileOutputStream fos=new FileOutputStream("X:\\uu.jpeg");
		byte[] bys=new byte[1024];
		int leng=0;
		while((leng=fis.read(bys))!=-1)
		{
			fos.write(bys,0,leng);
			fos.flush();
		}
		fos.close();
		fis.close();
	}
	//高效率的一个字节一个字节的读取文件
	public static void HightCopy()throws IOException
	{
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream("Z:\\rr.jpeg"));
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("X:\\vv.jpeg"));
		int by=0;
		while((by=bis.read())!=-1)
		{
			bos.write(by);
		}
		bos.close();
		bis.close();
		
	}
	public static void HightShuCopy()throws IOException
	{
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream("Z:\\rr.jpeg"));
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("X:\\ee.jpeg"));
		byte[] bys=new byte[1024];
		int leng=0;
		while((leng=bis.read(bys))!=-1)
		{
			bos.write(bys,0,leng);
			
		}
		bis.close();
		bos.close();
	}

}
