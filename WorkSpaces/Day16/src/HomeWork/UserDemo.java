package HomeWork;

public class UserDemo {
	private String name;
	private int age;
	
	public UserDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDemo(String name, int age) {
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
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		//return super.equals(arg0);
		//高效率
		if(this==obj)
		{
			return true;
		}
		//健壮性
		if(!(obj instanceof UserDemo))
		{
			return false;
			
		}
		UserDemo userDemo=(UserDemo)obj;
		return this.age==userDemo.age && this.name.equals(userDemo.name);
	}
	

}
