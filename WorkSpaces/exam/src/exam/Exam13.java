package exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;





public class Exam13 {
/*
 *10、编写一个类，在main方法中定义一个Map对象（采用泛型），
 *加入若干个对象，然后遍历并打印出各元素的key和value。 
 * */
	public static void main(String[] args) {
		HashMap< String, Student>hashMap=new HashMap<String, Student>();
		hashMap.put("1002", new Student("雪", 12));
		hashMap.put("1003", new Student("雪儿", 12));
		hashMap.put("1004", new Student("小雪", 12));
		hashMap.put("1007", new Student("六雪", 12));
		//遍历集合
//		Set<Map.Entry<String, Student>>set=hashMap.entrySet();
//		for (Entry<String, Student> entry : set) {
//			String keyString=entry.getKey();
//			Student student=entry.getValue();
//			System.out.println(keyString+"****"+student.getName()+"***"+student.getAge());
//		}
		Set<String>set=hashMap.keySet();
		for (String string : set) {
			Student student=hashMap.get(string);
			System.out.println(string+"***"+student.getName()+"***"+student.getAge());
		}
		
		
	}
}
