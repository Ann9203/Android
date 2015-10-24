package lianxi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class ByteCopy {
	/*
	 * ��ȡ�ֽڵķ�ʽ������
	 * 			��Ч��һ���ֽ�һ���ֽڵ�����  
	 * 					һ������һ������Ľ��ж�ȡ
	 * 			��Ч��һ���ֽ�һ���ֽڵ�����
	 * 					һ������һ������Ľ��ж�ȡ
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		LowCopy();
		LowShuCopy();
		HightCopy();
		HightShuCopy();
		
	}
	//��Ч�Ķ�ȡ�ֽ��ļ�
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
	//��Ч�ʵ��ֽ������ȡ�ֽ��ļ�
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
	//��Ч�ʵ�һ���ֽ�һ���ֽڵĶ�ȡ�ļ�
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
