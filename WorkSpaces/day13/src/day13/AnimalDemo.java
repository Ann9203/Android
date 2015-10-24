package day13;

/*
 * 
 * 2：动物园里有很多种动物：
	比如说，狗，猫等。
	狗有姓名和年龄，猫也有姓名和年龄。
	
	狗有跑步的方法，猫也有跑步的方法。而且都仅仅是跑步。
	狗有吃饭的方法，猫也有吃饭的方法。只不过，狗吃骨头，猫吃鱼。
    请用所学知识，对这个问题进行解决。
    代码用Eclipse实现。*/
public class AnimalDemo {
	private String name;
	private int age;
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
	public void run()
	{
		System.out.println("我们在慢慢的跑着");
	}
	public void eat()
	{	
	}
}
class Dog extends AnimalDemo
{
	public void eat()
	{
		System.out.println("小狗儿喜欢吃骨头");
		
	}
}
class Cat extends AnimalDemo
{
	public void eat()
	{
		System.out.println("小花猫喜欢吃鱼");
	}
}
