package fuxi;

public class WorkerDemo {
	private  String name;
	private int age;
	private String workid;
	public WorkerDemo(String name, int age, String workid) {
		super();
		this.name = name;
		this.age = age;
		this.workid = workid;
	}
	public WorkerDemo() {
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
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	
	//重写hash方法
	//不能进行排序，只是保证元素的唯一性
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		//return super.hashCode();
		return this.age*14+this.name.hashCode()*23+this.workid.hashCode()*12;
	}
	//重写equals方法
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
		if(!(obj instanceof WorkerDemo))
		{
			return false;
		}
		WorkerDemo wDemo=(WorkerDemo)obj;
		return this.name.equals(wDemo.name) && this.age==wDemo.age && this.workid.equals(wDemo.workid);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return this.name+"***************"+this.age+"**************"+this.workid;
	}

}
