package bufferedinou;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyDemo {
	/*
	 * Copy文件
	 * 字节流的文件补可以用字符流的方法区copy，字符流的文件可以用字节流的去copy
	 * Copy图片
	 * */
	public static void main(String[] args) throws IOException {
		long start=System.currentTimeMillis();
		method1();
		method2();
		method3();
		method4();
		long end=System.currentTimeMillis();
		System.out.println(end-start);
		
		
	}
	//方法一，用FileInputStream这种方式去Copy
	public static void  method1() throws IOException
	{
		FileInputStream fis=new FileInputStream("Z:\\money.jpeg");
		FileOutputStream fos=new FileOutputStream("X:\\ss.jpeg");
		int by=0;
		while((by=fis.read())!=-1)
		{
			fos.write(by);
		}
		fis.close();
		fos.close();
	}
	public  static void  method2() throws IOException
	{
		FileInputStream fis=new FileInputStream("Z:\\money.jpeg");
		FileOutputStream fos=new FileOutputStream("X:\\li.jpeg");
		byte[] bys=new byte[1024];
		int leng=0;
		while((leng=fis.read(bys))!=-1)
		{
			fos.write(bys,0,leng);
		}
		fis.close();
		fos.close();
	}
	public static void method3() throws IOException
	{
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream("Z:\\money.jpeg"));
		BufferedOutputStream bop=new BufferedOutputStream(new FileOutputStream("X:\\hh.jpeg"));
		int b=0;
		while((b=bis.read())!=-1)
		{
			bop.write(b);
		}
		bis.close();
		bop.close();
	}
	//第四种方法
	public static void method4()
	{
		//这个使用上手写的try..catch
		BufferedInputStream bis=null;
		BufferedOutputStream bop=null;
		try {
			bis=new BufferedInputStream(new FileInputStream("Z:\\money.jpeg"));
			bop=new BufferedOutputStream(new FileOutputStream("X:\\oo.jpeg"));
			byte[] bys=new byte[1024];
			int leng=0;
			while((leng=bis.read(bys))!=-1)
			{
				bop.write(bys,0,leng);
			}
	
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			if(bis!=null)
			{
				try {
					bis.close();
				} catch (Exception e2) {
					// TODO: handle exception'
						e2.printStackTrace();
				}
			}
			if(bop!=null)
			{
				try {
					bop.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

}
