import java.util.Scanner;
class StudentArray 
{
	public static void main(String[] args) 
	{
		/* 键盘录入
		
		*/
		Student[] stu=new Student[5];
		System.out.println("开始录入：");
		for(int i=0;i<stu.length;i++)
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("请输入第"+(i+1)+"个学生的姓名");
			String name=sc.nextLine();
			System.out.println("请输入第"+(i+1)+"个学生的年龄");
			int age=sc.nextInt();
			stu[i]=new Student(name,age);
		}
		System.out.println("输入的结果显示：");
		System.out.println("    \t姓名  \t年龄 ");
		for(int i=0;i<stu.length;i++)
		{
			
			
			System.out.println("    \t"+stu[i].getName()+"  \t年龄 "+stu[i].getAge());
			
		}
	}
}
class Student
{
	private String name;
	private int age;
	public Student()
	{}
	public Student(String name,int age)
	{
		this.name=name;
		this.age=age;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return name;

	}
	public void setAge()
	{
		this.age=age;
	}
	public int getAge()
	{
		return age;
	}
}
