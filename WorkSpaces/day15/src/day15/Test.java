package day15;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {
	public static void main(String[] args) throws ParseException {

		Date date=new Date();
		//��ҪĬ�ϵĸ�ʽ���Լ�дһ��ָ���ĸ�ʽ
		//��Date---String ���� ��Ҫformat��������һ��String����
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		String s=dateFormat.format(date);
		System.out.println(s);
		System.out.println("********************************");
		
		//��ҪĬ�ϸ�ʽ�Լ�ָ��һ����ʽ
		//��Date--String����  ��Ҫformat����һ���ַ���
		
		Date d3=new Date();
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s3=sm.format(d3);
		System.out.println(s3);
		System.out.println("_________________________________________________");
		
		
		//�����ַ���  String---date ʹ��parse����������һ��Date����
		String s4="2014-9-10 12:30:34";
		SimpleDateFormat smmm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d4=new Date();
		d4=smmm.parse(s4);
		System.out.println(d4);
	
		 
		System.out.println("******************************************************");
		//�����ַ���  String---Date ʹ��parse����������һ��Date����
		//ͬ��simpleDateFormat�Ĺ��췽����Ҫ��String�ĸ�ʽ��һ���ģ�����ͻ��׳��쳣
		String d="1999-3-12 00:00:00";
		SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2=new Date();
		//������׳�һ��һ�쳣��
		date2=ss.parse(d);
		//ss.parse(d);
		System.out.println(date2);
		
				
		
		}

	
}
