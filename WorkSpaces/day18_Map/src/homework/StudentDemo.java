package homework;

public class StudentDemo implements Comparable<StudentDemo>{
	private String name;
	private int age;
	
	

	public StudentDemo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDemo other = (StudentDemo) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(StudentDemo stu) {
		// TODO Auto-generated method stub
		//return 0;
		int num=this.age-stu.age;
		int num2=num==0?this.name.compareTo(stu.name):num;
		return num2 ;
				
	}

}
