package BufferedDemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class BufferedReaderDemo {
	/*
	 * ������������
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		BufferedReader bReader=new BufferedReader(new FileReader("Z:\\aa.txt"));
//		int b=0;
//		//��Чһ��һ���ֽڵ�ȥ��
//		//���������ֽڣ�һ��һ���Ķ�����b
//		while((b=bReader.read())!=-1)
//		{
//			System.out.print((char)b);
//		}
//		bReader.close();
		//��Ч�Ķ�ȡ��ʽ������ʹ������
		char[] ch=new char[1024];
		int b=0;
		while((b=bReader.read(ch))!=-1)
		{
			System.out.println(new String(ch,0,b));
		}
		bReader.close();
	}

}
