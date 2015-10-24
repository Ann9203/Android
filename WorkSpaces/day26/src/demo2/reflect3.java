package demo2;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class reflect3 {
	//通过构造方法获取变量，然后根据对变量设置值，
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, InvocationTargetException {
//		Class class1=Class.forName("demo2.Person");
//		
//		//获取所有的变量
//		
//			Field[] field=class1.getFields();
//			for (Field field2 : field) {
//				System.out.println(field2);
//			}
//		//获取无参的构造方法
//		Constructor constructor=class1.getConstructor();
//		Object object=constructor.newInstance();
////		//获取变量
////		//Field f=class1.getField("name");
////		//Field field=class1.getField("name");
//	`
//		field2.set(object, 23);
////		//field.set(object, "雪");
//		System.out.println(object);
		
		//获取私有的变量
//		Class  class1=Class.forName("demo2.Person");
//		//获取构造参数
//		Constructor constructor=class1.getConstructor();
//		Object object=constructor.newInstance();
//		//获取所有的变量
//		Field field=class1.getDeclaredField("name");
//		Field field2=class1.getDeclaredField("age");
//		field2.setAccessible(true);
//		field.setAccessible(true);
//		field.set(object,"李雪");
//		field2.set(object, 23);
//		System.out.println(object);
		//获取成员方法
		Class class1=Class.forName("demo2.Person");
		//获取构造参方法
		Constructor constructor=class1.getConstructor();
		Object object=constructor.newInstance();
		Method method=class1.getDeclaredMethod("show", null);
		//System.out.println(method);
		//有参数就传递参数就可以了
		method.invoke(object, null);
		
		
		
		
		
		
		
	}

}
