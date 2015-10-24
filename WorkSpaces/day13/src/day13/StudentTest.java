package day13;
public class StudentTest {
	public static void main(String[] args) {
		
		StudentDemo stu=new StudentDemo();
		stu.setAge(20);
		stu.setName("周公");	
		System.out.println(stu.toString());
		StudentDemo s=new StudentDemo();
		s.setAge(12);
		s.setName("小明");
		//第一版的compare，
		//boolean flag=stu.compare(stu.getAge(), s.getAge());
		//System.out.println(flag);
		//
		//boolean flag=stu.compare(stu,s);
		//System.out.println(flag);
		boolean flag=stu.compare(s);
		System.out.println(flag);
		boolean  flag1=stu.equals(s);
		System.out.print(flag1);
		
	}
}
