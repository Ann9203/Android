package FanXing;

import java.util.ArrayList;
import java.util.Iterator;

public class FanXingDemo {
	/*
	 * ��������ѧϰ�˷��ͣ����Ǿ��÷��͸Ľ�һ��֮ǰһֱ���ı���String�ַ��������ӡ�
	 * ��ʾ���ϲ���ArrayList, Ȼ��洢"hello", "world", "java" �����ַ���,Ȼ���������.

	 * */
	public static void main(String[] args) {
		
			ArrayList<String>arrayList=new ArrayList<String>();
			ArrayList<String>arrayList2=new ArrayList<>();
			//FanXingClass<String> fu=new FanXingClass<>();
			//fu.show();
			//arrayList2.add("Hello");
		
			
			arrayList.add("hello");
			arrayList.add("world");
			arrayList.add("java");
			arrayList.add(".");
			for(String str:arrayList)
			{
				System.out.println(str);
			}
			Iterator<String>iterator=arrayList.iterator();
			while (iterator.hasNext()) {
				String string=iterator.next();
				System.out.println(string);
				
			}
			
			Integer integer=1;
			Integer inte=2;
			if (inte>integer) {
				
			}
			
	}
}
