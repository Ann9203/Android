package day15;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDmeo {
	/*
	 * ����һ�����ϣ�ʵ����ArrayListʵ�֡�Ȼ���򼯺����5���ַ���Ԫ�ء��ֱ��ǣ���hello������itcast����
	 * ��shuangyuan������ketang������best�����жϼ������Ƿ����"ketang"���Ԫ����
	 * ��������󡱺͡�������̡�����˼·ȥ��
	 * */
	static ArrayList aList=new ArrayList()	;
	public static void main(String[] args) {
		
		//ArrayList aList=new ArrayList()	;
		aList.add("hello");
		aList.add("itcast");
		aList.add("shuangyuan");
		aList.add("ketang");
		aList.add("best");
		
		//��������ʹ�õ�������������
		Iterator iterator=aList.iterator();
		while (iterator.hasNext()) {
			//ǿ��ת������
			String string=(String)iterator.next();  //next������ʵ���ص���Object���ͣ�������ǿ��ת��
			System.out.println(string);
		}
		
		//add();
		System.out.println(aList.size());
		//�ж��Ƿ�����ö�����þ�����������˼����ʽ
		System.out.println(aList.contains("ketang"));
		//�ж��Ƿ�����ķ���һ���������
		//���Ⱦ���Ҫ��list����ת��������
		//toArry���ص���Object[]��������ת��ǿ��ת��
		String[] strings=(String[])aList.toArray();
		for (int i = 0; i < strings.length; i++) {
			//String str=strings[i];
			if("ketang".equals(strings[i]))
			{
				System.out.println("�ҵ�������");
				
			}else {
				System.out.println("I'm sorry");
			}
		}
		
		
		
		
	}
	
	

}
