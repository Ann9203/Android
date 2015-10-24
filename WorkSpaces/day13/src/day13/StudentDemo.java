package day13;

public class StudentDemo {
	private String name;
	private int age;
	public StudentDemo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentDemo(String name, int age) {
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
	@Override
//	重写Object 中的toString方法
	public String toString() {
		return "StudentDemo [name=" + name + ", age=" + age + "]";
	}

	/*
	 * 第一版
	 * 对象之间的属性进行对比，第一版compare
	 * 首先分析：
	 * 	A:返回值类型 是boolean型
	 *  B:参数列表是   int age
	 * */
	/*public boolean compare(int age1,int age2)
	{
		
		//==用于进行基本类型的值比较
		return age1==age2;
	}*/
	/*
	 * 第二版
	 * 对象之间的属性进行对比，
	 * 面型对象的思想进行改进		
	 * */
	/*public boolean compare(StudentDemo s1,StudentDemo s2)
	{
		//面向对象的思想，得到了对象，就得到对象的一些属性
		return s1.age==s2.age;
		
		
	}
*/
	/*
	 * 第三版：
	 * 因为我把compare写在了类中，他用对象来进行调用，
	 * 对象调用一个方法的时候，方法中会有一个this来代表这个对象
	 * */
	public boolean compare(StudentDemo s)
	{
		//面向对象的思想，得到了对象，就得到对象的一些属性
		return this.age==s.age;
		
		
	}
	/*
	 * 第四版直接重写Object中继承下来的equals方法
	 * 需要注意的：
	 * 	String类型中有自己重写的Equals方法，主要就是比较字符串的内容
	 * */
	public boolean equals(Object obj)
	{
		if(obj instanceof StudentDemo)
		{
			StudentDemo sd=(StudentDemo) obj;
			return this.age==sd.age && this.name.equals(sd.name);		
		}
		else {
			return false;
		}
	
	}
}
