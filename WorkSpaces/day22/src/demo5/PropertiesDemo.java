package demo5;

import java.util.Properties;
import java.util.Set;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class PropertiesDemo {
	/*
	 * proper����һ����ʾ���Եļ��ϣ����Դ����м������ݻ����ǰ����е����ݴ洢������ֵ�����ַ���
	 * 	��һ��Ψһһ�����Ժ�IO�����н��ʹ�õļ�����
	 * Properties�ĸ�����HasTa���ˣ���������֪������һ��Map������
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		
		//����properties�Ķ���
//		Properties prop=new Properties();
//		prop.put("001", "��ѩ");
//		prop.put("002", "֣��");
//		//��ȡMap�е�����
//		//��ȡ��ֵ�ļ���
//		Set<Object> set=prop.keySet();
//		for (Object object : set) {
//			Object value=prop.get(object);
//			System.out.println(object+"*****"+value);
//		}
		Properties properties=new Properties();
		properties.put("001", "snow");
		properties.put("002","windy");
		Set<Object> set=properties.keySet();
		for (Object object : set) {
			Object value=properties.get(object);
			System.out.println(object+"********"+value);
		}
	}

}
