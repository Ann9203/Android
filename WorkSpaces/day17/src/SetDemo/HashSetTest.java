package SetDemo;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {
	 public static void main(String[] args) {
		 HashSet<Student> hs=new HashSet<Student>();
		 hs.add(new Student("С��", 15,34));
		 hs.add(new Student("Сlu", 15,34));
		 hs.add(new Student("С��", 15,45));
		 hs.add(new Student("С��", 15,67));
		 hs.add(new Student("С��", 15,55));
		 //��һ������ķ�ʽ
		 Iterator<Student> iterator=hs.iterator();
		 while (iterator.hasNext()) {
			Student student=(Student)iterator.next();
			System.out.println(student.getName()+"*********************"+student.getAge());
			
			
		}
		 System.out.println("-----------------------------------------------------------------");
		// �ڶ�������ķ�ʽ��
		 for(Student student:hs)
		 {
			 System.out.println(student.getName()+"**************************"+student.getAge());
		 }

	}		
}
