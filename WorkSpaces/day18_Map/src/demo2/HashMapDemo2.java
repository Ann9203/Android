package demo2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapDemo2 {

	/*
	 * ����һ��HashMap����,Ȼ�����3���ɶԵ�Ԫ��,
	 * Ҫ��key��String����,value��Student���ͣ�������
	 * */
	public static void main(String[] args) {
		Map<String, Student>map=new HashMap<>();
		map.put("12345", new Student("ѩ", 22, 34));
		map.put("12346", new Student("��", 23, 45));
		map.put("12348", new Student("��", 23, 88));
		Set<String> key=map.keySet();
		for (String string:key) {
			Student student=map.get(string);
			System.out.println(string+"  "+student.getName()+"  "+student.getAge()+"  "+student.getScore());
		}
		System.out.println("***********************************");
		//�ڶ��ֱ����ķ�ʽ
		Set<Map.Entry<String, Student>>entries=map.entrySet();
		Iterator<Map.Entry<String, Student>>iterator=entries.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Student> stuEntry=iterator.next();
			String keyString=stuEntry.getKey();
			Student student=stuEntry.getValue();
			System.out.println(keyString+"  "+student.getName()+"  "+student.getAge()+"  "+student.getScore());
			
			
		}
	
		
	}
}
