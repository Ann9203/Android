package HomeWork;

import java.util.ArrayList;
import java.util.Iterator;

public class Array_LinkDemo {
	/*
	 * 分别用ArrayList,LinkedList存储字符串和自定义对象并遍历。
	 * 并用泛型改进代码的写法
	 * */
	public static void main(String[] args) {
		//在使用泛型之前
		
		ArrayList arrayList=new ArrayList();
		arrayList.add("hello");
		arrayList.add("world");
		arrayList.add("java");
		arrayList.add("andriod");
		//第一种获取的方式迭代器
		Iterator iterator=arrayList.iterator();
		while (iterator.hasNext()) {
			String string=(String)iterator.next();
			System.out.println(string );
		}
		System.out.println("********************************************");
		//第二种获取的方法：普通for循环
		for (int i = 0; i < arrayList.size(); i++) {
			String string=(String)arrayList.get(i);
			System.out.println(string);
			
		}
		System.out.println("********************************************");
		//第三种获取方式：增强for循环
		//因为没有使用泛型，所以在集合元素的类型上只能是Object，不能进行强制转换
		for(Object object :arrayList)
		{
			System.out.println(object);
		}
		System.out.println("********************************************");
		//linkedList同上是一样的，只不过LinkedList的在存取增加了几个新的方式
		//存取对象如何存取的。
		System.out.println("********************************************");
		//一下要使用泛型进行存取
		ArrayList<String> arrayList2=new ArrayList<String>();
		arrayList2.add("How");
		arrayList2.add("are");
		arrayList2.add("you");
		Iterator<String>iterator2=arrayList2.iterator();
		while (iterator2.hasNext()) {
			//因为使用了泛型，指定的类型就是String类型的了，所以就不用强制转型了
			String string=iterator2.next();
			System.out.println(string);
			
		}
		
	}

}
