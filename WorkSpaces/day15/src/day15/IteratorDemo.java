package day15;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class IteratorDemo {
	/*
	 * 1.���ȶ���һ����׼ѧ����(name,age����)
	 * 2.���ü��ϴ洢3��ѧ����Ȼ��������ϡ���ӡ��ÿ��ѧ�����name��age��
	 * */
	public static void main(String[] args) {
		ArrayList aList =new ArrayList();
		aList.add(new Student("С��", 23));
		aList .add(new Student("С��",22));
		aList .add(new Student("С��", 12)) ;
		Iterator iterator=aList.iterator();
		while(iterator.hasNext())
		{
			Student student=(Student)iterator.next();
			System.out.println(student .getName()+"*************"+student.getAge());
		}
	}

}
