import java.util.Scanner;
class StudentArray 
{
	public static void main(String[] args) 
	{
		/* ����¼��
		
		*/
		Student[] stu=new Student[5];
		System.out.println("��ʼ¼�룺");
		for(int i=0;i<stu.length;i++)
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("�������"+(i+1)+"��ѧ��������");
			String name=sc.nextLine();
			System.out.println("�������"+(i+1)+"��ѧ��������");
			int age=sc.nextInt();
			stu[i]=new Student(name,age);
		}
		System.out.println("����Ľ����ʾ��");
		System.out.println("    \t����  \t���� ");
		for(int i=0;i<stu.length;i++)
		{
			
			
			System.out.println("    \t"+stu[i].getName()+"  \t���� "+stu[i].getAge());
			
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
