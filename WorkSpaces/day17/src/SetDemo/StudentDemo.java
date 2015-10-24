package SetDemo;

public class StudentDemo implements Comparable<StudentDemo>{
	private String nameString;
	private int age;
	private int score;
	public StudentDemo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentDemo(String nameString, int age, int score) {
		super();
		this.nameString = nameString;
		this.age = age;
		this.score = score;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
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
	@Override
	/*
	 * 现有Student类,属性有name, age, score(int类型). 按照学生的分数排序,
	 * 分数大的童鞋在前面,如果分数相同,那么年龄小的在前面, 如果分数年龄都相同, 
	 * 则按照姓名(英文的即可)的字典顺序排序.
	 
	 * */
	public int compareTo(StudentDemo studentDemo) {
		// TODO Auto-generated method stub
		//return 0;
		int num=this.score-studentDemo.score;
		int num2=num==0?(this.age-studentDemo.age):num;
		int num3=num2==0?(this.nameString.compareTo(studentDemo.nameString)):num2;
		return num3;
	}
	

}
