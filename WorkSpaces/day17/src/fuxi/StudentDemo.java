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
	//�ڴ���HashSet�ļ����У�Ҫ������дhashCode��Equals����
	//Ԫ���ڱȽϵ�ʱ���xian�Ƚ�hashCode�����Ƕ���ÿ��������˵��hashCode��ֵ�϶��ǲ�һ���ģ����Լ�ʹ����
	//��������ͬ��Ҳ����Ϊ���������ǲ�ͬ��
	//hashCode��Ҫ��comparble�ķ���������
	//hashCode��ֵ���ǿ��Է���һ������ֵ���
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		//return super.hashCode();
		//String���͵����������ͣ���������hashCodeֵҲ�Ƿ��ص�int����
		return this.age*16+this.score*14+this.name.hashCode();
		
		
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		//return super.equals(obj);
		//��Ч��
		if(this==obj)
		{
			return true;
		}
		//��׳��
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
