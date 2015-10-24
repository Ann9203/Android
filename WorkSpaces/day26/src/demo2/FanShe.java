package demo2;

public class FanShe {
	public static void main(String[] args) throws ClassNotFoundException {
		
		//方式一：
		Person person=new Person();
		Class class1=person.getClass();
		//方式二；
		Class c3=Person.class;
		//方式三     获取字节码文件
		Class c4=Class.forName("demo2.Person");
		System.out.println(c4==class1);
		
		
		
	}

}
