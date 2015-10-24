package fuxi;
/*
 *��ѧ�������TreeSet�У�TreeSet�ĵײ�ṹ�Ƕ������Ľṹ�����Զ�Ԫ�ؽ�������
 *�����Ԫ�ػ���һ����Ȼ����
 *���Ǹ���Comparbleʵ������ӿڣ�ʵ��compareTo�ķ��������԰����Լ���д���������������
 * 
 * */
public class Student implements Comparable<Student> {
	private String name;
	private int age;
	private int score;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String name, int age, int score) {
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

	
	//ʹ��Treeset���д洢���ݵĻ������Զ�Ԫ�ؽ�������
	//������дComparble�ķ���
	/*
	 *  ����ѧ���ķ�������,�������ͯЬ��ǰ��,�ú����߽��бȽ�ǰ��
	 *  ���������ͬ,��ô����С����ǰ��,
	 *  ����������䶼��ͬ, ��������(Ӣ�ĵļ���)���ֵ�˳������.
	 * */
	@Override
	public int compareTo(Student stu) {
		// TODO Auto-generated method stub
		//return 0;
		int num=stu.score-this.score;
		int num2=num==0?(this.age-stu.age):num;
		int num3=num2==0?(this.name.compareTo(stu.name)):num2;
		return num3;
	}
}
