package exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;





public class Exam13 {
/*
 *10����дһ���࣬��main�����ж���һ��Map���󣨲��÷��ͣ���
 *�������ɸ�����Ȼ���������ӡ����Ԫ�ص�key��value�� 
 * */
	public static void main(String[] args) {
		HashMap< String, Student>hashMap=new HashMap<String, Student>();
		hashMap.put("1002", new Student("ѩ", 12));
		hashMap.put("1003", new Student("ѩ��", 12));
		hashMap.put("1004", new Student("Сѩ", 12));
		hashMap.put("1007", new Student("��ѩ", 12));
		//��������
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
