package bufferedinou;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyDemo {
	/*
	 * Copy�ļ�
	 * �ֽ������ļ����������ַ����ķ�����copy���ַ������ļ��������ֽ�����ȥcopy
	 * CopyͼƬ
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
	//����һ����FileInputStream���ַ�ʽȥCopy
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
	//�����ַ���
	public static void method4()
	{
		//���ʹ������д��try..catch
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
