package demo5;

import java.util.Properties;
import java.util.Set;

public class Properties2 {
	
	/*
	 * �޸Ĺ���
	 * 		
	 * public object setProperty(string key,string value)
	 * 
	 * ��ȡ����
	 * public String getProperty(string key);
	 * public string getProperty(string key,string default)
	 * public set<string> stringPropertyname()
	 * 	
	 * */
	public static void main(String[] args) {
		//����properties����
		Properties properties=new Properties();
		//�޸Ĺ���
		//���֮ǰû����������ͷ���null��Ȼ����������
		System.out.println(properties.setProperty("�ŷ�", "����"));
		
		properties.setProperty("���", "С��Ů");
		properties.setProperty("����", "����");
		//���ݼ���ֵ����ȡֵ
		System.out.println(properties.getProperty("С��Ů"));
		//
		System.out.println(properties.getProperty("���", "СŮ"));
		
		//��ȡ���Ǽ�����
		Set<String> namSet=properties.stringPropertyNames();
		for (String string : namSet) {
			System.out.println(string);
		}
	}

}
