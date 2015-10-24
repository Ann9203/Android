package fuxi;
/*
 *吧学生类存入TreeSet中，TreeSet的底层结构是二叉树的结构，可以对元素进行排序
 *存入的元素会有一种自然排序
 *就是根据Comparble实现这个接口，实现compareTo的方法，可以按照自己重写的条件进行排序的
 * 
 * */
public class Student implements Comparable<Student> {
	private String name;
	private int age;
	private int score;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String name, int age, int score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
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

	
	//使用Treeset进行存储数据的话，可以对元素进行排序
	//让类重写Comparble的方法
	/*
	 *  按照学生的分数排序,分数大的童鞋在前面,用后来者进行比较前者
	 *  如果分数相同,那么年龄小的在前面,
	 *  如果分数年龄都相同, 则按照姓名(英文的即可)的字典顺序排序.
	 * */
	@Override
	public int compareTo(Student stu) {
		// TODO Auto-generated method stub
		//return 0;
		int num=stu.score-this.score;
		int num2=num==0?(this.age-stu.age):num;
		int num3=num2==0?(this.name.compareTo(stu.name)):num2;
		return num3;
	}
}
