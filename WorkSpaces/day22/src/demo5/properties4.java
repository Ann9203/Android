package demo5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;

import demo3.printIoDemo;

public class properties4 {
	/*
	 * ��ϵķ�ʽ���ǣ���������IO������
	 * 	��ôʹ�ã�
	 * 		public void  list(PrintStream out); //�����ֽ������
	 * 		public void lis(PrintWriter out); //�����ַ������
	 * */
	public static void main(String[] args) throws IOException {
		//����ϵͳ���ļ�
		Properties properties=System.getProperties();
		//д���ļ���
//		BufferedWriter bWriter=new BufferedWriter(new FileWriter("system.txt"));
//		//��ȡProperties�е�Ԫ�أ�Ȼ������д���ļ���
//		Set<String>key=properties.stringPropertyNames();
//		for (String string : key) {
//			String  vlue=properties.getProperty(string);
//			bWriter.write(string);
//			bWriter.write("=");
//			bWriter.write(vlue);
//			bWriter.newLine();
//		}
//		bWriter.close();
		//ʹ��list����
//		PrintWriter pWriter=new PrintWriter("aa.txt");
//		//list�������ǽ������б������ָ������
//		properties.list(pWriter);
//		pWriter.close();
		PrintWriter pw=new PrintWriter("bb.txt");
		properties.list(pw);
		pw.close();
	}
}
