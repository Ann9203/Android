package demo2;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class reflect3 {
	//ͨ�����췽����ȡ������Ȼ����ݶԱ�������ֵ��
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, InvocationTargetException {
//		Class class1=Class.forName("demo2.Person");
//		
//		//��ȡ���еı���
//		
//			Field[] field=class1.getFields();
//			for (Field field2 : field) {
//				System.out.println(field2);
//			}
//		//��ȡ�޲εĹ��췽��
//		Constructor constructor=class1.getConstructor();
//		Object object=constructor.newInstance();
////		//��ȡ����
////		//Field f=class1.getField("name");
////		//Field field=class1.getField("name");
//	`
//		field2.set(object, 23);
////		//field.set(object, "ѩ");
//		System.out.println(object);
		
		//��ȡ˽�еı���
//		Class  class1=Class.forName("demo2.Person");
//		//��ȡ�������
//		Constructor constructor=class1.getConstructor();
//		Object object=constructor.newInstance();
//		//��ȡ���еı���
//		Field field=class1.getDeclaredField("name");
//		Field field2=class1.getDeclaredField("age");
//		field2.setAccessible(true);
//		field.setAccessible(true);
//		field.set(object,"��ѩ");
//		field2.set(object, 23);
//		System.out.println(object);
		//��ȡ��Ա����
		Class class1=Class.forName("demo2.Person");
		//��ȡ����η���
		Constructor constructor=class1.getConstructor();
		Object object=constructor.newInstance();
		Method method=class1.getDeclaredMethod("show", null);
		//System.out.println(method);
		//�в����ʹ��ݲ����Ϳ�����
		method.invoke(object, null);
		
		
		
		
		
		
		
	}

}
