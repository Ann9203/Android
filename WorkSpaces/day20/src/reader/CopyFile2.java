package reader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import sun.security.util.Length;

public class CopyFile2 {
	/*
	 * ���������е�ð��
	 * �����ļ��ĸ�Ч�ʵ�д��
	 * */
	public static void main(String[] args) throws IOException {
		FileReader fd=new FileReader("Z:\\aa.txt");
		FileWriter fw=new FileWriter("X:\\u.txt");
		char[]ch=new char[1024];
		int leng=0;
		//������Ƕ���һ���ַ����飬Ȼ������ĳ��ȶ���Ϊ1024�ı���
		//�ڶ�ȡ��ʱ�򣬾��ǰ���һ������ĳ�������ȡ�����ص������ַ��ĳ���
		while((leng=fd.read(ch))!=-1)
		{
			fw.write(ch, 0, leng);
		}
		//�������Ҫ�ر����ļ�
		fd.close();
		fw.close();
		
	}
	

}
