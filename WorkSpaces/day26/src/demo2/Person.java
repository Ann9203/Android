package demo2;

public class Person {
	private  String name;
	private  int age;
	public Person(String name, int age) {
		//super();
		this.name = name;
		this.age = age;
	}
	public Person() {
		//super();
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
	public void show()
	{
		System.out.println("Hello");
	}
	public void function(){
		System.out.println("java");
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return this.name+"********"+this.age;
	}
	
	

}
