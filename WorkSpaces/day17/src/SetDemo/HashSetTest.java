package SetDemo;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {
	 public static void main(String[] args) {
		 HashSet<Student> hs=new HashSet<Student>();
		 hs.add(new Student("小红", 15,34));
		 hs.add(new Student("小lu", 15,34));
		 hs.add(new Student("小白", 15,45));
		 hs.add(new Student("小蓝", 15,67));
		 hs.add(new Student("小红", 15,55));
		 //第一种排序的方式
		 Iterator<Student> iterator=hs.iterator();
		 while (iterator.hasNext()) {
			Student student=(Student)iterator.next();
			System.out.println(student.getName()+"*********************"+student.getAge());
			
			
		}
		 System.out.println("-----------------------------------------------------------------");
		// 第二种排序的方式：
		 for(Student student:hs)
		 {
			 System.out.println(student.getName()+"**************************"+student.getAge());
		 }

	}		
}
