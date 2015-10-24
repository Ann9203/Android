package day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionDemo {
	/*
	 * 练习Collection的所有的方法
	 * */
	public static void main(String[] args) {
		
		//添加功能 
		//add  addall
		//因为Collection是一个接口所以不能实例化
		Collection cll=new ArrayList();
		cll.add("Hello");
		cll.add("World");
		System.out.println(cll.add("java"));
		System.out.println(cll);
		//删除功能
		//clear是全部删除
//		cll.clear();
//		System.out.println(cll);
		//remove 从集合中移除一个元素
//		cll.remove("Hello");
//		System.out.println(cll);
		//判断功能
		System.out.println(cll.contains("Hello"));
		//这个只能是判断一次，如果有两个Hello，。。
		if(cll.contains("Hello"))
		{
			cll.add("ios");
		}
		System.out.println(cll);
		//isEmpty判断是否为空
		//遍历的功能
		//Iterator
		Iterator it=cll.iterator();
		while (it.hasNext()) {
			//Object object=it.next();
			//也可以通过强制转换，转换成String类型的
			String string=(String)it.next();
			System.out.println(string);
			//System.out.println(object);			
		}	
		System.out.println(cll.size());
		//toArray返回值是一个Object数组 所以转换成String要进行
		//强制转换
//		String[] strings=(String[])cll.toArray();
		
		//所有All的功能
		//addAll（）向集合中添加一个集合的元素
		Collection cl=new ArrayList();
		cl.add("Hello");
		cl.add("nihao");
		cl.add("better");
		//cll.addAll(cl);
		System.out.println(cll);
		System.out.println(cl);
		//主要就是删除cll中和cl相同的所有的元素
//		cll.removeAll(cl);
//		System.out.println(cll);
//		System.out.println(cl);
		//containsAll
		//判断cll是否全部包含cl中的元素，只有全部包含才返回true
//		System.out.println(cll.containsAll(cl));
//		System.out.println(cll);
//		System.out.println(cl);
		//cll中保存的是交集中的元素
		//cl中的元素是不发生变化的
		System.out.println(cll.retainAll(cl));
		System.out.println(cll);
		System.out.println(cl);
		
		//
	}
	
	
	 
	
}
