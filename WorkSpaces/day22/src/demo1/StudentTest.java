package demo1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class StudentTest {
	/*
	 * ���󣺴���һ��ѧ���࣬Ȼ���ٿ���̨������5��ѧ���������Լ��ɼ�
	 * 		Ȼ�����Լ�����ĸ�ʽ�洢���ı��ļ��С���Щ�ɼ����ոߵͽ�������
	 * ˼·��
	 * 		A:����һ��ѧ����
	 * 		B:��Ϊ���ճ���ĸߵͽ�����������ʹ��TreeSet������װ������
	 * 		C:�����Լ�����ĸ�ʽ�洢���ı��ļ��У������أ�Ҫ�õ��������������д�뵽�ı��ļ��ļ���
	 * 		D:�ɼ�Ҫ���ոߵͽ�������TreeSet�������һЩ��Ȼ������Ҫ��ʵ��Comparable�ӿڣ�����ʵ���ڲ���
	 * 			compartor�ĵķ���
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		
		//���ȶ���һ��TreeSet����
		//��ΪҪ���ճɼ�������������ͨ���ڲ���ʵ��Comparator�ķ���
		TreeSet<Student>tSet=new TreeSet<>(new Comparator<Student>() {

			@Override
			public int compare(Student stu1, Student stu2) {
				// TODO Auto-generated method stub
				/*
				 * �Ƚϵ�������
				 * 		A:�ܳɼ��ȶԱȣ��ܳɼ��ߵķ���ǰ��
				 * 		B:�ܳɼ���ͬ�ͶԱ����ĳɼ������ĳɼ������ǰ��
				 * 		C:���ĳɼ���ͬ�ͶԱ���ѧ�ɼ����ܳɼ���ķ���ǰ��
				 * 		D:�ܳɼ���ͬ�����ĳɼ���ͬ����ѧ�ɼ���ͬ������Ӣ��ɼ�Ҳ��ͬ����ʱ��ͱȽ�һ���������������
				 * 			��ͬ�Ļ�������ͬһ������
				 * 
				 * */
					int num1=stu2.getSum()-stu1.getSum();
					int num2=num1==0?(stu1.getChinese()-stu2.getChinese()):num1;
					int num3=num2==0?(stu1.getMath()-stu2.getMath()):num2;
					int num4=num3==0?(stu1.getName().compareTo(stu2.getName())):num3;
				return num4;
			}
		});
		System.out.println("��ʼ¼�����ݣ�");
		//�����������
		for(int x=0;x<5;x++)
		{
			//��Ϊ���̵�¼�����ݣ�String���ͺ�int���ͣ����¼��String������¼��int���Ϳ��ԣ�����int����֮����¼��String��
			//�ᱨ�����԰ѳ�ʼ������Ĵ���д��forѭ�����
			//�ڿ���̨��������
			Scanner scanner=new Scanner(System.in);
			System.out.println("������ѧ����������");
			String name=scanner.nextLine();
			System.out.println("���������ĳɼ���");
			int chinese=scanner.nextInt();
			System.out.println("��������ѧ�ɼ���");
			int math=scanner.nextInt();
			System.out.println("������Ӣ��ɼ���");
			int english =scanner.nextInt();
			//¼�����֮���������
			Student  student=new Student(name, chinese, math, english);
			tSet.add(student);
			//scanner.close();
		
		}
		System.out.println("����¼�����");
		//������װ�����ݣ�����������д������
		//����һ��Ŀ��Ԫ
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("sutdent.txt"));
		//�����ĵ���д��һ����ͷ
		bWriter.write("����\t���ĳɼ�\t��ѧ�ɼ�\tӢ��ɼ�");
		bWriter.newLine();
		bWriter.flush();
		
		for (Student student : tSet) {
			//����treeSet�����е�Ԫ��
			//�����������ߵĻ�������ÿ��ѭ�������ݶ����У����µ�����ʱ�򣬻��Ϊ��״���д�ӡ
			//��������߾���ÿ��ѭ����һ�������ݾ����´ְ���һ��StringBuilder
			StringBuilder sBuilder=new StringBuilder();
			//���Ȱѻ�ȡ����Ԫ�ش���StringBuilder��
			sBuilder.append(student.getName()).append("\t").append(student.getChinese()).append("\t").append(student.getMath()).append("\t").append(student.getEnglish());
			bWriter.write(sBuilder.toString());
			bWriter.newLine();
			bWriter.flush();
		}
		bWriter.close();
		
	}

}
