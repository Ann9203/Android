package day20lianxi;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFile {
	/*
	 * Copy �ļ��ĸ�Ч�ķ�ʽ
	 * 		A:����ͨ��������д���ļ���
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		//����һ����ȡ���ļ�
		FileReader rd=new FileReader("Z:\\aa.txt");
		//����һ��д����ļ�
		FileWriter fw=new FileWriter("X:\\c.txt");
		//����һ������char���͵�
//		char[] ch=new char[1024];
//		int length=0;
//		while ((length=rd.read(ch))!=-1)
//		{
//			//��ȡ���ļ�Ҫд�뵽�µ�.txt��
//			fw.write(ch, 0, length);
//		}
//		rd.close();
//		fw.close();
		char[] ch=new char[1024];
		int length=0;
		while((length=rd.read(ch))!=-1)
		{
			fw.write(ch, 0, length);
		}
		fw.close();
		rd.close();
		
		
	}

}
