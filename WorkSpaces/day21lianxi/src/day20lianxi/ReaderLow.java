package day20lianxi;
import java.io.FileReader;
import java.io.IOException;

import sun.misc.FDBigInteger;

public class ReaderLow {
	/*
	 * �ַ��������ĵ�Ч�ķ���
	 * 	����һ���ַ�һ���ַ�������ȡ
	 * */
	public static void main(String[] args) throws IOException {
		FileReader rd=new FileReader("Z:\\aa.txt");
		//��Ч�ʵķ�ʽ��ȡ�ļ�
		int leng=0;
		//int leng=rd.read();SSSSS
		//�����ȡ�ļ�rd.read���ص���int���͵�ֵ
		//���Զ�ȡ���ֱ�Ӱ�int���͵ĵ�ֵת����char���;Ϳ���
		//����Ҫ�ٴζ�ȡ�������Ļ������ȱ������
//		while ((leng=rd.read())!=-1) {
//			//System.out.println(rd.read));
//			//char ch=(char)rd.read();
//		System.out.print((char)leng);
//			
//		}
//		rd.close();
//		while((leng=rd.read())!=-1)
//		{
//			System.out.println((char)leng);
//		}
		while((leng=rd.read())!=-1)
		{
			System.out.println((char)leng);
		}
		rd.close();
		
	}

}
