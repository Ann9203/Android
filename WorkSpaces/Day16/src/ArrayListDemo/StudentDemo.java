package ArrayListDemo;

public class StudentDemo {
	private String  name;
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
	//要是存储的对象姓名和年龄如果相同就证明，存入的是同一个人
	//所以就要重写equals方法
	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		//return super.equals(arg0);
		//高效率
		if(this==object)
		{
			return true;
			
		}
		//健壮行
		if(!(object instanceof StudentDemo))
		{
			return false;
		}
		StudentDemo studentDemo=(StudentDemo)object;
		return this.age==studentDemo.age && this.name.equals(studentDemo.name);
	}
	

}
