package SetDemo;

//重写comparable方法，让Student实现这个方法，主要就是想要排除相同的元素。

public class Student implements Comparable<Student> {
	private String name;
	private int age;
	private int score;
	
	public Student(String name, int age, int score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}



	public Student() {
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

	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public int hashCode() {
		// TODO Auto-generated method
		return this.name.hashCode()*13+this.age*15;
	}
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		//return super.equals(arg0);
		if(this==obj)
		{
			return true;
		}
		if(!(obj instanceof Student))
		{
			return false;
		}
		Student student=(Student)obj;
		return this.name.equals(student.name) && this.age==student.age;
		
	}

	@Override
	
	public int compareTo(Student student) {
		// TODO Auto-generated method stub
		//return 0;
		int num=this.age-student.age;
		//comparTo的方法需要返回的是int类型的，String类中compareTo方法是比较字符串  这个返回的额是int类型
		//但是equals返回的是boolean类型的
		int num2=num==0?(this.name.compareTo(student.name)):num;
		return num2;
		
	}
		

}
