package demo2;

public class FanShe {
	public static void main(String[] args) throws ClassNotFoundException {
		
		//��ʽһ��
		Person person=new Person();
		Class class1=person.getClass();
		//��ʽ����
		Class c3=Person.class;
		//��ʽ��     ��ȡ�ֽ����ļ�
		Class c4=Class.forName("demo2.Person");
		System.out.println(c4==class1);
		
		
		
	}

}
