package copy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyMp3 {
	/*
	 * ��Z��Ŀ¼�µ�һ�׸踴�Ƶ���һ���ļ����¡�����Ѹ - ʮ��1��
	 * ���ַ�ʽ
	 * 	A:���ǵ�Ч�ʵ��ֽڶ�ȡ InputStream
	 * 	B:��Ч�ʵ��ֽڶ�ȡ		OutPutStream
	 * 	C:��Ч�ʵ��ֽڶ�ȡ  BufferReader
	 * 	D:��Ч�ʵ��ֽڶ�ȡ BufferWriter
	 * 	
	 * */
	public static void main(String[] args) throws IOException {
		//metho1();
		//metho1();
		//method2();
		//method3();
		method4();
	}
	//��һ�ַ�ʽ
	public static void metho1() throws IOException

	{
		
		FileInputStream fStream=new FileInputStream("Z:\\����Ѹ - ʮ��.mp3");
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
		System.err.println("��ʱ��"+(endTime-startTime));
	}
	public static void method2() throws IOException
	{
		FileInputStream fis=new FileInputStream("Z:\\����Ѹ - ʮ��.mp3");
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
		System.err.println("��ʱ��"+(endTime-startTime));	
		
	}
	//����3��ͨ�� ʹ��Buffered����������
	//�ֽڴ洢���ļ�ֻ�����ֽ������洢
	public static void method3() throws IOException
	{
		//BufferedReader bReader=new BufferedReader(new FileOutputStream("Z:\\����Ѹ - ʮ��.mp3"));
		//BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\ee.mp3"));
		BufferedInputStream bReader=new BufferedInputStream(new FileInputStream("Z:\\����Ѹ - ʮ��.mp3"));
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
		System.err.println("��ʱ��"+(endTime-startTime));	
	}
	//����4����Ч�ķ���
	public static  void method4() throws IOException
	{
//		BufferedReader reader=new BufferedReader(new FileReader("Z:\\����Ѹ - ʮ��.mp3"));
//		BufferedWriter writer=new BufferedWriter(new FileWriter("x:\\ff.mp3"));
		//Buffered�ǻ���������߸������ַ����������ֽ�����
		BufferedInputStream reader=new BufferedInputStream(new FileInputStream("Z:\\����Ѹ - ʮ��.mp3"));
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
		System.err.println("��ʱ��"+(endTime-startTime));	
		
		
	}
}
