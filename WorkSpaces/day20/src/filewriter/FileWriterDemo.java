package filewriter;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
	
	/*
	 * Io��
	 * 		�ַ���
	 * �ٴ������ļ��У�д��һ������
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		//����������
		//����������Զ�����ϵͳ���ܴ�������
		FileWriter fileWriter=new FileWriter("a.txt");
		//������д������
		fileWriter.write("Hello,EveryBody");
		fileWriter.flush();
		fileWriter.close();
		
		
	}
	
}
