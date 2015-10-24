package fuxi;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo {
		/*
		 * ����ѧ���ķ�������,�������ͯЬ��ǰ��,
		 * ���������ͬ,��ô����С����ǰ��,  
		 * ����������䶼��ͬ, ��������(Ӣ�ĵļ���)���ֵ�˳������.
		 * 
		 * */
	public static void main(String[] args) {
		TreeSet<Student> treeSet=new TreeSet<Student>();
		treeSet.add(new Student("�ܽ���", 23, 44));
		treeSet.add(new Student("����", 33, 88));
		treeSet.add(new Student("������", 34, 78));
		treeSet.add(new Student("����÷", 77, 33));
		treeSet.add(new Student("����", 66, 99));
		treeSet.add(new Student("����", 66, 99));
		//����һ����Student��ʵ��Comparble�ӿ�
		for (Student student : treeSet) {
			System.out.println(student.getName()+"\t"+student.getAge()+"\t"+student.getScore());
		}
		System.out.println("***********************************************");
		//�������������ڲ���
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
			treeSet2.add(new Student("�ܽ���", 23, 44));
			treeSet2.add(new Student("����", 33, 88));
			treeSet2.add(new Student("������", 34, 78));
			treeSet2.add(new Student("����÷", 77, 33));
			treeSet2.add(new Student("����", 66, 99));
			treeSet2.add(new Student("����", 66, 99));
			for (Student student : treeSet2) {
				System.out.println(student.getName()+"\t"+student.getAge()+"\t"+student.getScore());
			}
			
			//��������
			//����һ���ⲿ�࣬���ⲿ��ȥʵ��compartor����ӿ�
			//TreeSet< Student> treeSet3=new TreeSet<Student>(new OutImpl());
			
	}
}
