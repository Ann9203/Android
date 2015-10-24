package day15;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class IteratorDemo {
	/*
	 * 1.首先定义一个标准学生类(name,age属性)
	 * 2.请用集合存储3个学生，然后遍历集合。打印出每个学生类的name和age。
	 * */
	public static void main(String[] args) {
		ArrayList aList =new ArrayList();
		aList.add(new Student("小力", 23));
		aList .add(new Student("小李",22));
		aList .add(new Student("小张", 12)) ;
		Iterator iterator=aList.iterator();
		while(iterator.hasNext())
		{
			Student student=(Student)iterator.next();
			System.out.println(student .getName()+"*************"+student.getAge());
		}
	}

}
