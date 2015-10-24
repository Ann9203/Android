package demo2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapDemo2 {

	/*
	 * 创建一个HashMap集合,然后存入3个成对的元素,
	 * 要求key是String类型,value是Student类型，并遍历
	 * */
	public static void main(String[] args) {
		Map<String, Student>map=new HashMap<>();
		map.put("12345", new Student("雪", 22, 34));
		map.put("12346", new Student("雨", 23, 45));
		map.put("12348", new Student("风", 23, 88));
		Set<String> key=map.keySet();
		for (String string:key) {
			Student student=map.get(string);
			System.out.println(string+"  "+student.getName()+"  "+student.getAge()+"  "+student.getScore());
		}
		System.out.println("***********************************");
		//第二种遍历的方式
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
