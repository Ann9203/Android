package demo5;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Properties5 {
	/*
	 * �ɼ����е����ݱ��浽�ı���
	 * �õķ�����store
	 * */
public static void main(String[] args) throws IOException {
//	Properties properties=new Properties();
//	properties.setProperty("��ѩ", "22");
//	properties.setProperty("����", "23");
//	//����Ŀ�ĵ�
//	FileWriter fwFileWriter=new FileWriter("ZZ.txt");
//	//�Ѽ����е����ݱ��浽�ı��У�ͬʱ�����������ݵĹ�ͬ��
//	//properties.save(fwFileWriter, "love me");
//	properties.store(fwFileWriter, "love");
//	fwFileWriter.close();
	
	Properties properties=new Properties();
	properties.setProperty("����", "3");
	properties.setProperty("С��", "4");
	properties.setProperty("С��", "6");
	properties.setProperty("С��", "8");
	
	//��������Դ��Ŀ�ĵ�
	FileWriter fWriter=new FileWriter("p.txt");
	properties.store(fWriter, "");
	fWriter.close();
	
}
}
