package ArrayListDemo;

import java.util.ArrayList;
import java.util.Iterator;

public class ArraylistDemo {
	/*
	 * 
	 * 一个字符串集合ArrayList中含有如下元素：
	 * hello, world, java, hello, .net, java, php, ios, java, android，world。
	 * 要求编写程序，获得一个没有重复元素的新集合。

	 * */
	//步骤：写两个集合
	//写一个判断是否包含重复元素的集合，然后吧不重复的元素写入另一个新的集合中，然后返回新的集合
	
	public static void main(String[] args) {
		ArrayList arrayList=new ArrayList();
		
		arrayList.add("hello");
		arrayList.add("world");
		arrayList.add("java");
		arrayList.add("hello");
		arrayList.add(".net");
		arrayList.add("java");
		arrayList.add("php");
		arrayList.add("ios");
		arrayList.add("java");
		arrayList.add("android");
		arrayList.add("world");
		
//	ArrayList arrayList2=compare(arrayList);
//		Iterator iterator=arrayList2.iterator();
//		while (iterator.hasNext()) {
//			String string=(String)iterator.next();
//			System.out.println(string);
			
			//方法二：选择排序
			int length=arrayList.size();
			for(int x=0;x<length;x++)
			{
				for(int y=x+1;y<length ;y++)
				{
					if(arrayList.get(x).equals(arrayList.get(y)))
					{
						arrayList.remove(y);
						length--;
					}
				}
			}
			Iterator iterator=arrayList.iterator();
			while (iterator.hasNext()) {
				String string=(String)iterator.next();
				System.out.println(string);
			}
			
		}
	
	//方法一就是新创建一个对象
	public static ArrayList compare(ArrayList arl)
	{
		ArrayList newArrayList=new ArrayList();
		Iterator iterator=arl.iterator();
		while(iterator.hasNext())
		{
			Object object=iterator .next();
			if(!newArrayList.contains(object))
			{
				newArrayList.add(object);
			}
		}
		return newArrayList;
		
	}

}
