package day20lianxi;

import java.io.FileWriter;
import java.io.IOException;

public class IOFileWriterDemo {
	/*
	 * д���ļ�Ҳ���ֽڷ������
	 * */
	public static void main(String[] args) throws IOException {
		FileWriter fWriter=new FileWriter("a.txt");
		fWriter.write("Hello");
		fWriter.write("java");
		fWriter.flush();
		fWriter.close();
		//�����Ҫ���ļ���д��������ô�죿�� ʹ���µĹ��췽��
		//FileWriter(string,boolean)
		FileWriter fw2=new FileWriter("a.txt",true);
		fw2.write("Hello\t\n");
		fw2.write("java\t\n");
		fw2.write("IO\t\n");
		fw2.write("Bayby\t\n");
		fw2.flush();
		fw2.close();
		
		
	}

}
