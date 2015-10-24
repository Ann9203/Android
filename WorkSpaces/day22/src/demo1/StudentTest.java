package demo1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class StudentTest {
	/*
	 * 需求：创建一个学生类，然后再控制台上输入5个学生的姓名以及成绩
	 * 		然后按照自己定义的格式存储到文本文件中。这些成绩按照高低进行排序
	 * 思路：
	 * 		A:创建一个学生类
	 * 		B:因为按照诚意的高低进行排序，所以使用TreeSet集合来装载数据
	 * 		C:根据自己定义的格式存储到文本文件中，所以呢，要用到输出流来处理并且写入到文本文件文件中
	 * 		D:成绩要按照高低进行排序：TreeSet本身就有一些自然排序，需要类实现Comparable接口，或者实现内部类
	 * 			compartor的的方法
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		
		//首先定义一个TreeSet集合
		//因为要按照成绩进行排序，所以通过内部类实现Comparator的方法
		TreeSet<Student>tSet=new TreeSet<>(new Comparator<Student>() {

			@Override
			public int compare(Student stu1, Student stu2) {
				// TODO Auto-generated method stub
				/*
				 * 比较的条件：
				 * 		A:总成绩先对比，总成绩高的放在前边
				 * 		B:总成绩相同就对比语文成绩，语文成绩搞的在前边
				 * 		C:语文成绩相同就对比数学成绩，总成绩搞的放在前边
				 * 		D:总成绩相同，语文成绩相同，数学成绩相同，所以英语成绩也相同，这时候就比较一下姓名，如果姓名
				 * 			相同的话，就是同一个人了
				 * 
				 * */
					int num1=stu2.getSum()-stu1.getSum();
					int num2=num1==0?(stu1.getChinese()-stu2.getChinese()):num1;
					int num3=num2==0?(stu1.getMath()-stu2.getMath()):num2;
					int num4=num3==0?(stu1.getName().compareTo(stu2.getName())):num3;
				return num4;
			}
		});
		System.out.println("开始录入数据：");
		//输入五个对象
		for(int x=0;x<5;x++)
		{
			//因为键盘的录入数据，String类型和int类型，如果录入String类型在录入int类型可以，但是int类型之后在录入String就
			//会报错。所以把初始化对象的代码写在for循环里边
			//在控制台输入数据
			Scanner scanner=new Scanner(System.in);
			System.out.println("请输入学生的姓名：");
			String name=scanner.nextLine();
			System.out.println("请输入语文成绩：");
			int chinese=scanner.nextInt();
			System.out.println("请输入数学成绩：");
			int math=scanner.nextInt();
			System.out.println("请输入英语成绩：");
			int english =scanner.nextInt();
			//录入完毕之后传入对象中
			Student  student=new Student(name, chinese, math, english);
			tSet.add(student);
			//scanner.close();
		
		}
		System.out.println("数据录入完毕");
		//集合中装载数据，接下来就是写入数据
		//创建一个目的元
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("sutdent.txt"));
		//现在文档中写入一个表头
		bWriter.write("姓名\t语文成绩\t数学成绩\t英语成绩");
		bWriter.newLine();
		bWriter.flush();
		
		for (Student student : tSet) {
			//遍历treeSet集合中的元素
			//如果定义在外边的话，就是每次循环的数据都会有，导致导引的时候，会成为梯状进行打印
			//定义在里边就是每次循环完一个条数据就重新粗昂见一个StringBuilder
			StringBuilder sBuilder=new StringBuilder();
			//首先把获取到的元素存入StringBuilder中
			sBuilder.append(student.getName()).append("\t").append(student.getChinese()).append("\t").append(student.getMath()).append("\t").append(student.getEnglish());
			bWriter.write(sBuilder.toString());
			bWriter.newLine();
			bWriter.flush();
		}
		bWriter.close();
		
	}

}
