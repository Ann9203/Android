package homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {
	/*
	 *2.HashMap存储自定义对象并遍历键：Student值：String
	 *需求：如果对象的成员变量值都相同，我们则认为是同一个对象。
	 * 
	 * */
	public static void main(String[] args) {
		HashMap<StudentDemo, String> hashMap=new HashMap<>();
		hashMap.put(new StudentDemo("小雪", 56), "12345");
		hashMap.put(new StudentDemo("小云", 23), "12389");
		hashMap.put(new StudentDemo("朵朵", 34), "12345");
		hashMap.put(new StudentDemo("小雪", 56), "12345");
		hashMap.put(new StudentDemo("小雪", 22), "12345");
		
		
		Set<StudentDemo>key=hashMap.keySet();
		for (StudentDemo studentDemo : key) {
			String valueString=hashMap.get(studentDemo);
			System.out.println(studentDemo.getName()+"\t"+studentDemo.getAge()+"\t"+valueString);
		}
		
		
	}

}
