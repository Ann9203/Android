package demo1;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;
public class StudentTest2 {
	public static void main(String[] args) throws IOException {
		TreeSet<Student> tSet=new TreeSet<>(new Comparator<Student>() {

			@Override
			public int compare(Student s1, Student s2) {
				// TODO Auto-generated method stub
				//return 0;
				int num1=s2.getSum()-s1.getSum();
				int num2=num1==0?(s1.getChinese()-s2.getChinese()):num1;
				int num3=num2==0?(s1.getMath()-s2.getMath()):num2;
				int num4=num3==0?(s1.getName().compareTo(s2.getName())):num3;
				return num4;
			}
		});
		for(int x=0;x<5;x++)
		{
			Scanner scanner=new Scanner(System.in);
			System.out.println("请输入第"+x+"个的学生的姓名：");
			String name=scanner.nextLine();
			System.out.println("请输入语文成绩：");
			int  chinese=scanner.nextInt();
			System.out.println("请输入数学成绩：");
			int meth=scanner.nextInt();
			System.out.println("请输入英语成绩");
			int english=scanner.nextInt();
			
			Student student=new Student(name,chinese,meth,english);
			tSet.add(student);
		}
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("student.txt"));
		//对于缓冲区的数据一定要写一行，就进行刷新
		bWriter.write("姓名\t语文成绩\t数学成绩\t英语成绩");
		bWriter.newLine();
		bWriter.flush();
		for (Student student : tSet) {
			StringBuilder sb=new StringBuilder();
			sb.append(student.getName()).append("\t").append(student.getChinese()).append("\t").append(student.getMath()).append("\t").append(student.getEnglish());
			bWriter.write(sb.toString());
			bWriter.newLine();
			bWriter.flush();
		}
		bWriter.close();
		
		
	}

}
