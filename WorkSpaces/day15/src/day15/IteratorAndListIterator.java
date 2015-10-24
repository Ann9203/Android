package day15;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.sun.javafx.scene.control.skin.ListViewSkin;

public class IteratorAndListIterator {
	
	/*
	 * 遍历数组查找
	 * 三种方式
	 * 针对的是list集合
	 * 1.iterator
	 * 2.listIterator
	 * 3.get（）方法获取元素，然后for循环
	 * */
	public static void main(String[] args) {
		List list=new ArrayList();
		list.add("Hello");
		list.add("World");
		list.add("Java");
		list.add("best");
		//测试修改的功能返回的是修改之前的元素
		//System.out.println(list.set(2, "Andrio"));
		//用itretor进行遍历
		Iterator iterator=list.iterator();
		while (iterator .hasNext())
		{
			String string=(String)iterator.next();
			System.out.println(string);
			
		}
		for (int i = 0; i < list.size(); i++) {
			String string=(String)list .get(i);
			System.out.println(string);
		}
		ListIterator listt=list.listIterator();
		while (listt.hasNext()) {
			String string=(String )listt .next();
			System.out.println(string);
			
		}
		
	}

}
