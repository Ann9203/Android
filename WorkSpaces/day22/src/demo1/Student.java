package demo1;

public class Student {
	/*
	 * Student类主要就是
	 * 		创建一个名称属性
	 * 		创建一个英语，数学，语文，成绩属性	
	 * 		因为要比较成绩所以要有一个判断总成绩的方法
	 * 		
	 * */
	private String name;
	private int english;
	private int math;
	private int chinese;
	public Student(String name, int english, int math, int chinese) {
		super();
		this.name = name;
		this.english = english;
		this.math = math;
		this.chinese = chinese;
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
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getChinese() {
		return chinese;
	}
	public void setChinese(int chinese) {
		this.chinese = chinese;
	}
	public int getSum()
	{
		return this.chinese+this.math+this.english;
	}
	
	
	

}
