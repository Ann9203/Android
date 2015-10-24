package demo2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QuChuChongfuDemo {
	/*
	 * 取出重复
	 * */
	public static void main(String[] args) {
		Map<Student, Integer> stuMap=new HashMap<Student, Integer>();
		stuMap.put(new Student("小白", 13, 66),12345);
		stuMap.put(new Student("小菜", 15, 64),12348);
		stuMap.put(new Student("小菜", 15, 64),12348);
		stuMap.put(new Student("小明", 14, 99),12349);
		stuMap.put(new Student("小菜", 19, 77),12348);
		Set<Student>students=stuMap.keySet();
		for (Student student : students) {
			Integer value=stuMap.get(student);
			System.out.println(student.getName()+" "+student.getAge()+" "+value);
		}
		
		
	}

}
