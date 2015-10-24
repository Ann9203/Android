package fuxi;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo {
		/*
		 * 按照学生的分数排序,分数大的童鞋在前面,
		 * 如果分数相同,那么年龄小的在前面,  
		 * 如果分数年龄都相同, 则按照姓名(英文的即可)的字典顺序排序.
		 * 
		 * */
	public static void main(String[] args) {
		TreeSet<Student> treeSet=new TreeSet<Student>();
		treeSet.add(new Student("周杰伦", 23, 44));
		treeSet.add(new Student("周润发", 33, 88));
		treeSet.add(new Student("珍视明", 34, 78));
		treeSet.add(new Student("马丽梅", 77, 33));
		treeSet.add(new Student("爱丽", 66, 99));
		treeSet.add(new Student("爱丽", 66, 99));
		//方法一：让Student类实现Comparble接口
		for (Student student : treeSet) {
			System.out.println(student.getName()+"\t"+student.getAge()+"\t"+student.getScore());
		}
		System.out.println("***********************************************");
		//方法二：定义内部类
		TreeSet<Student>treeSet2=new TreeSet<Student>(new Comparator<Student>() {

			@Override
			public int compare(Student stu1, Student stu2) {
				// TODO Auto-generated method stub
				//return 0;
				int num=stu2.getScore()-stu1.getScore();
				int num2=num==0?(stu1.getAge()-stu2.getAge()):num;
				int num3=num2==0?(stu1.getName().compareTo(stu2.getName())):num2;
				return num3;
			}
		});
			treeSet2.add(new Student("周杰伦", 23, 44));
			treeSet2.add(new Student("周润发", 33, 88));
			treeSet2.add(new Student("珍视明", 34, 78));
			treeSet2.add(new Student("马丽梅", 77, 33));
			treeSet2.add(new Student("爱丽", 66, 99));
			treeSet2.add(new Student("爱丽", 66, 99));
			for (Student student : treeSet2) {
				System.out.println(student.getName()+"\t"+student.getAge()+"\t"+student.getScore());
			}
			
			//方法三：
			//定义一个外部类，让外部类去实现compartor这个接口
			//TreeSet< Student> treeSet3=new TreeSet<Student>(new OutImpl());
			
	}
}
