package demo2;

public class StudentDemo implements Comparable<StudentDemo>{
	private String name;
	private int age;
	
	

	public StudentDemo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public StudentDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(StudentDemo stu) {
		// TODO Auto-generated method stub
		//return 0;
		int num=this.age-stu.age;
		int num2=num==0?this.name.compareTo(stu.name):num;
		return num2 ;
				
	}

}
