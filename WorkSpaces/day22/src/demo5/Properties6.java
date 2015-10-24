package demo5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;

public class Properties6 {
	/*
	 * ����������ļ�user.txt���Ƿ���lisi�����������У����޸���ֵΪ50
	 * ˼·��
	 * 		A:��ʾ���ı��е����ݶ�������Ȼ����뵽������
	 * 		B:������������ҵ��Ļ��ͽ����޸�
	 * 		C:�޸�����֮����ٷ����ı������±���
	 * */
	public static void main(String[] args) throws IOException {
		//method1();
		method2();
		
	}
	public static void method1() throws IOException
	{
		//��������Դ����
		FileReader fReader=new FileReader("p.txt");
		//��������
//		Properties properties=new Properties();
//		properties.load(fReader);
//		Set<String> keySet=properties.stringPropertyNames();
//		for (String string : keySet) {
//			//������ڵĻ������޸�Ԫ��
//			if("8".equals(properties.getProperty(string)))
//			{
//				//�޸�Ԫ��
//				properties.setProperty(string, "45");
//			}
//		}
//		//�޸���Ԫ��֮�󣬻�Ҫ��Ԫ�ش��뵽���ļ���
//		//FileWriter fWrit=new FileWriter("p.txt");
//		//ʹ��list����������ı���ȥ
//		PrintWriter pWriter=new PrintWriter("p.txt");
//		properties.list(pWriter);
//		pWriter.close();
		
		//����Դ����������
		FileReader frFileReader=new FileReader("p.txt");
		//�������ζ���
		Properties properties=new Properties();
		//���ı����ݴ��뵽������load����������˼��Ҳ���ǰѼ����е�������������
		properties.load(frFileReader);
		if(properties.containsKey("����"))
		{
			properties.setProperty("����", "50");
		}
//		Set<String>keySet=properties.stringPropertyNames();
//		for (String string : keySet) {
//			if("8".equals(properties.getProperty(string)))
//			{
//				//�޸�Ԫ��
//				properties.setProperty(string, "45");
//			}
//		}
		//�޸�������֮�󣬴����ı���
		PrintWriter pWriter=new PrintWriter("p.txt");
		//list���ǰѼ��ϵ����ݷ��ظ��ı�
		properties.list(pWriter);
		pWriter.close();
		fReader.close();
		
	}
	//�������������ı�����һ������store
	public static void method2() throws IOException
	{
		//��������Դ������
		FileReader fReader=new FileReader("p.txt");
		//�������϶���
		Properties properties=new Properties();
		//���ı��е����ݶ��뵽������
		properties.load(fReader);
		//��������
		Set<String>key=properties.stringPropertyNames();
		for (String string : key) {
			if("4".equals(properties.getProperty(string)))
			{
				properties.setProperty(string, "23");
			}
		}
		//�޸ĺ���ı�д�뵽������
		FileWriter fwFileWriter=new FileWriter("p.txt");
		properties.store(fwFileWriter, "С����");
		fwFileWriter.close();
	}
	

}
