package homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class QianTaoDemo {

		/*
		 * 2. HashMapǶ��ArrayList��ʹ�á�
		 * (�ر�����)czbk��
		 * 
		 * Ԥ�Ȱ�,��ҵ��Ԥ�Ȱ�:01 zhangsan��02 lisi��ҵ��:01 wangwu��02 zhaoliu
		 * 
		 * 
		 * */
	
	public static void main(String[] args) {
			
		new QianTaoDemo().test();
        ArrayList<String> al = new ArrayList<>();
		al.add("s");
		al.add("ddd");
		al.add("true");
	
		System.out.println(al);
		

		 ArrayList<String> list = new ArrayList<>();
			list.add("java");
			list.add("aaa");
			list.add("java");
			list.add("java");
		     list.add("bbb");
		for (int i =0; i < list.size(); i++) {
			if("java".equals(list.get(i))){
				list.remove(i);
			}
	

	}
		// list.remove("java");
		//list.removeAll("java");
System.out.println(list);
	

		
	}
	public QianTaoDemo() {
		super();
		// TODO Auto-generated constructor stub
	}
	private void test() {
		String aStr = "_One_";
		String bStr = aStr;
		bStr = aStr.trim();
		System.out.println("[" + aStr + "," + bStr + "]");
	}

}
