package day15;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;



public class ListDemo {
	/*
	 * 创建集合ArrayList，添加hello，world，java三个元素，然后遍历集合，
	 * 判断集合中是否有元素hello，如果有的话就添加一个新元素ios进去。
	 * 然后再遍历集合

	 * */
	public static void main(String[] args) {
		
		List list=new ArrayList();
		list.add("Hello");
		list.add("World");
		list.add("Java");
		ListIterator iterator=list.listIterator();
		//Iterator iterator =list .iterator();
		while(iterator.hasNext())
		{
			Object object=iterator.next();
			if(object .equals("Hello"))
			{
				//如果使用集合的话，应该都要使用集合
				//使用集合去添加元素
				iterator .add("iio");
			}
			
			
		}
////			if("Hello".equals(string))
////			{
////				list.add("ios");
////				//System.out.println("hahha");
////			}
//			
//		}
//		for (int i = 0; i < list.size(); i++) {
//			String string =(String)list .get(i);
//			if("Hello".equals(string))
//			{
//				list .add("ios");
//			}
//			
//		}
		
		System.out.println("lis:"+list);
	}
}
