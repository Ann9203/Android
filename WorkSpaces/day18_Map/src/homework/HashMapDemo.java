package homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {
	/*
	 *2.HashMap�洢�Զ�����󲢱�������Studentֵ��String
	 *�����������ĳ�Ա����ֵ����ͬ����������Ϊ��ͬһ������
	 * 
	 * */
	public static void main(String[] args) {
		HashMap<StudentDemo, String> hashMap=new HashMap<>();
		hashMap.put(new StudentDemo("Сѩ", 56), "12345");
		hashMap.put(new StudentDemo("С��", 23), "12389");
		hashMap.put(new StudentDemo("���", 34), "12345");
		hashMap.put(new StudentDemo("Сѩ", 56), "12345");
		hashMap.put(new StudentDemo("Сѩ", 22), "12345");
		
		
		Set<StudentDemo>key=hashMap.keySet();
		for (StudentDemo studentDemo : key) {
			String valueString=hashMap.get(studentDemo);
			System.out.println(studentDemo.getName()+"\t"+studentDemo.getAge()+"\t"+valueString);
		}
		
		
	}

}
