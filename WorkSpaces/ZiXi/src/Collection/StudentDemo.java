
package Collection;

public class StudentDemo {
	//����һ��Student����
	//����˽������
	//��HashSet�洢��ʱ�򣬿���������ͬ��Ҫ�ǱȽ�
	//Hashcodeֵ�������ֱͬ����ӣ������ͬ�ͱȽ�equalsֵ
	//�����ڶ����о���дhashCodeֵ
	
	private String name;
	private  int age;
	//�вεĹ��캯��
	public StudentDemo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	//�޲ι��캯��
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
	
	
	
	//��дequeals����
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		//���ж϶����ǲ��Ǳ�����
		if(this==obj)
		{
			return true;
		}
		if(obj instanceof StudentDemo)
		{
			
			StudentDemo studentDemo=(StudentDemo)obj;
			return this.age==studentDemo.age && this.name.equals(studentDemo.name);
		}else {
			System.out.println("�ܱ�Ǹ���ⲻ��ѧ���࣡��");
		}
		return super.equals(obj);
		//
	}
	
	//��дhashCodeֵ��
		//Hashcode���Բ�����д����Ϊÿ�δ������󶼻᷵�ز�ͬ��������
	//������Ϊÿ�δ����Ķ���ͬ����ʹ�Ƕ�����������䶼��ͬ���������ǵ�HashCodeֵ��
	//��ͬ�ģ������أ���������hashSet�л��Ǳ���Ϊ����ͬ�����ص�
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		//return super.hashCode();
		return 1;
	}
	
	
}
