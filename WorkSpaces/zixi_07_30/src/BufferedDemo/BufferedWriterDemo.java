package BufferedDemo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterDemo {
/*
 * BufferedWriter��������֮Ϊ����������ֻ�ṩ���ݵĻ��壬���Ƕ�д�����ݻ�Ҫ������
 * ����൱��һ��װ��ģʽ���ṩ���ӹ���
 * ������ֻ�ṩ����Ĺ��ܣ�û�������Ķ�д
 * ����������
 * 	����ֱ�ӽ��ж�д����
 * �߼�����
 * 	վ�ڻ������Ļ����ϣ��ṩһЩ����Ĺ���
 * 
 * */
	public static void main(String[] args) throws IOException {
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("u.txt"));
		bWriter.write("Hello,beijing");
		bWriter.flush();
		bWriter.close();
	}
}
