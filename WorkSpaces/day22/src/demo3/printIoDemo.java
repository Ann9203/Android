package demo3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class printIoDemo {
	/*
	 * printStream:�ֽڴ�ӡ��
	 * printWriter���ַ���ӡ��
	 * ��ӡ�����ص㣺
	 * 		A:����д���������͵�����
	 * 		B:�����Զ�ˢ�£�����������������ʹ��println,printf�Լ�format������
	 * 			����ʹ���Զ�ˢ��Ŷ���ܹ���
	 		C:����ֱ�Ӷ��ļ�����д��
	 				��Щ���������ֱ�Ӷ��ļ����в�����
	 					�����췽�����Ƿ���ͬ�½���File��String���͵Ĳ���
	 				ע�⣺��ӡ��ֻд���ݣ�����ȡ����
	 * */
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter pWriter=new PrintWriter("ww.txt");
		pWriter.println("Hello");
		pWriter.print("nihao");
		pWriter.flush();
		pWriter.close();
		
	}

}
