package fuxi;

public class StudentDemo {
	private String name;
	private int age;
	private int score;
	public StudentDemo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentDemo(String name, int age, int score) {
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
	//在存入HashSet的集合中，要进行重写hashCode和Equals方法
	//元素在比较的时候会xian比较hashCode，但是对于每个对象来说，hashCode的值肯定是不一样的，所以即使名称
	//和年龄相同，也会认为两个对象是不同的
	//hashCode不要和comparble的方法混淆了
	//hashCode的值我们可以返回一个属性值相加
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		//return super.hashCode();
		//String类型的是引用类型，调用他的hashCode值也是返回的int类型
		return this.age*16+this.score*14+this.name.hashCode();
		
		
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		//return super.equals(obj);
		//高效率
		if(this==obj)
		{
			return true;
		}
		//健壮性
		if(!(obj instanceof StudentDemo))
		{
			return false;
		}
		StudentDemo studentDemo=(StudentDemo)obj;
		return this.name.equals(studentDemo.name) && this.age==studentDemo.age;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+"***************"+this.age+"***************"+this.score;
	}
	
}
