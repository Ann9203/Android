package day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class HomeWork {
	public static void main(String[] args) {
		//Collection集合中存储自定义对象,并遍历。
		Collection coll=new ArrayList();
		//添加元素
		coll.add(new Student("小王", 12));
		coll.add(new Student("小李",13));
		coll .add(new Student("小赵", 14));
		//定义迭代器
		Iterator iterator =coll .iterator();
		//迭代器对象进行遍历集合中的元素
		while(iterator.hasNext())
		{
			//强制转型
			Student student=(Student)iterator .next();
			System.out.println(student .getName()+"*************************"+student .getAge());
			
		}
		//定义list集合，在list集合中存储自定义对象，然后进行遍历
		List list=new ArrayList();
		list.add("How");
		list.add("are");
		list.add("you");
		list.add("?");
		//定义iterator迭代器对象
		Iterator iterator2=list .iterator();
		while (iterator2.hasNext()) {
			//遍历对象
			//使用迭代器进行遍历
			
			String string =(String)iterator2.next();
			System.out.print(string+" ");
			
			
		}
		System.out.println("\n"+"*******************************");
		//遍历方式二
		//因为list集合独特的就是get方法，可以取得其中的元素
		//也就是说list集合可以进行for循环遍历
		for (int i = 0; i < list .size(); i++) {
			//向下转型
			String string=(String )list .get(i);
			System.out.print(string +" ");
			
		}
		System.out.println("\n"+"*******************************");
		//第三种遍历就是通过listIteraotr，这个迭代器可以解决iteraotor带来的并发修改异常
		ListIterator listIterator =list.listIterator();
		while (listIterator.hasNext()) {
			String string=(String)listIterator.next();
			System.out.print(string+" ");
			
		}
		
		
	}

}
