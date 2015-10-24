package LinkedListDemo;

import java.util.LinkedList;

public class StatcDemo {
	//创建一个能实现栈功能的对象
	//虽然是实现栈功能，但是他内部是封装了LinkedList功能
	//私有化一个LinkedList变量，然后初始化一个对象
	private LinkedList linkedList;
	public StatcDemo()
	{
		
		linkedList=new LinkedList();
	}
	//添加功能因为栈是先进后出
	//在添加功能中，就封装了LinkedList的addFirst方法
	public void add(Object obj)
	{
		linkedList.addFirst(obj);
	}
	//因为每次添加都是addFirst所以在取出元素的时候，直接取出来就OK了
	//给一个索引值
	public Object  get(int index)
	{
		return linkedList.get(index);
		
	}
	//第一：从集合中取出数组，要根据集合的长度来取出集合
	//第二：要想把集合中的所有的数组取出来的话，就要使用的是for循环
	//for循环这个类的话，这个类就应该有长度
	public int size()
	{
		return linkedList.size();
		
	}

}
