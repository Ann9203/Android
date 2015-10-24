package demo2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorDemo {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class class1=Class.forName("demo2.Person");
		
		//�����޲ι��췽��
		Constructor<Person> constructor=class1.getConstructor();
		Object object=constructor.newInstance();
		System.out.println(object);
		//�����вι��췽��
		Constructor constructor2=class1.getDeclaredConstructor(String.class,int.class);
		Object object2=constructor2.newInstance("��ѩ",24);
		System.out.println(object2);
		
	}

}
