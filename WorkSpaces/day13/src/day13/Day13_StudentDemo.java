package day13;

public class Day13_StudentDemo {
	/*
	 * 编程题
	定义一个标准学生类(name,age)
	定义一个学生数组。
	存储5个学生，然后遍历。
	String[]
	Student[]
	 * */
	private String name;
	private int age;
	

	public Day13_StudentDemo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Day13_StudentDemo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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
	
}
