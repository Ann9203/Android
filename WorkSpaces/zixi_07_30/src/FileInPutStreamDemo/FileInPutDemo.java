package FileInPutStreamDemo;

import java.io.FileInputStream;
import java.io.IOException;
public class FileInPutDemo {
	/*
	 * FileInPutStream��ר�Ų����ֽڵ���������ר�����ڶ�ȡ�����е��ļ�����Ҫ���ڶ�ȡԭʼ���ֽ�����
	 * ��ȡ�ַ�������Ҫ��FileReader
	 * */
	public static void main(String[] args) throws IOException {
		
		FileInputStream fp=new FileInputStream("Z:\\aa.txt");
		//��¼��ȡ�Ĵ���
//		int b=0;
//		while((b=fp.read())!=-1)
//		{
//			//�������Ķ���һ��һ�����ֽ�
//			//�������ĵĻ��Ͳ��ܶ������ˣ��ͻ�����
//			System.out.print((char)b);
//		}
		//��Ч�Ķ�ȡ��ʽ
		
//		byte[] by=new byte[1024];
//		int b=0;
//		while((b=fp.read(by))!=-1)
//		{
//			System.out.println(new String(by, 0,b));
//		}
//		
		byte[] by=new byte[1024];
		int b=0;
		while((b=fp.read(by))!=-1)
		{
			System.out.println(new String(by,0,b));
		}
		//�����������ͷ���Դ
	
		fp.close();
		
	}

}
