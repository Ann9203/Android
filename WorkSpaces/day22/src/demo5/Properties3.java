package demo5;

import java.util.Properties;
import java.util.Set;

public class Properties3 {
/*
 * ���Ϻ�Io���ļ��ϣ�
 * 		
 * */
	public static void main(String[] args) {
//		Properties properties=System.getProperties();
//		Set<String>key=properties.stringPropertyNames();
//		for (String string : key) {
//			String value=properties.getProperty(string);
//			System.out.println(string +"*******"+key);
//			
//		}
		Properties properties=System.getProperties();
		Set<String>key=properties.stringPropertyNames();
		for (String string : key) {
			String value=properties.getProperty(string);
			System.out.println(string+"*******"+value);
			
		}
		
	}
}
