
package Collection;

public class StudentDemo {
	//创建一个Student对象
	//创建私有属性
	//用HashSet存储的时候，看对象是相同主要是比较
	//Hashcode值，如果不同直接添加，如果相同就比较equals值
	//所以在对象中就重写hashCode值
	
	private String name;
	private  int age;
	//有参的构造函数
	public StudentDemo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	//无参构造函数
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
	
	
	
	//重写equeals方法
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		//先判断对象是不是本对象
		if(this==obj)
		{
			return true;
		}
		if(obj instanceof StudentDemo)
		{
			
			StudentDemo studentDemo=(StudentDemo)obj;
			return this.age==studentDemo.age && this.name.equals(studentDemo.name);
		}else {
			System.out.println("很抱歉，这不是学生类！！");
		}
		return super.equals(obj);
		//
	}
	
	//重写hashCode值，
		//Hashcode可以不用重写，因为每次创建对象都会返回不同的整数。
	//正是因为每次创建的对象不同，即使是对象的姓名年龄都相同，但是他们的HashCode值是
	//不同的，所以呢，。。。在hashSet中还是被认为不相同的因素的
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		//return super.hashCode();
		return 1;
	}
	
	
}
