package exam;

import java.util.ArrayList;
import java.util.ListIterator;

public class Exam11 {
	/*
	 * 12��һ��ArrayList����aList�д������ɸ��ַ���Ԫ�أ�
	 * ����������ArrayList����ɾ����������ֵΪ"abc"���ַ���Ԫ�أ�
	 * ���ô���ʵ�֡�
	 * ˼·��
	 * 		���ȶ���һ��Arraylist���ϣ�Ȼ�����Ԫ�أ�
	 * 		֮����Ǳ�������
	 * 	B:��Ϊ��ȥ�����Ԫ�أ�����������������ListIterator������
	 * */
	public static void main(String[] args) {
		
		ArrayList<String>al=new ArrayList<String>();
		al.add("abc");
		al.add("adc");
		al.add("aaa");
		al.add("Hell");
		ListIterator<String>listIterator=al.listIterator();
		while(listIterator.hasNext())
		{
			String value=listIterator.next();
			if("abc".equals(value))
			{
				listIterator.remove();
				
			}
		}
		System.out.println(al);
		
		
		
		
	}
	

}
