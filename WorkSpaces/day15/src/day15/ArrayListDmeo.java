package day15;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDmeo {
	/*
	 * 创建一个集合，实体用ArrayList实现。然后向集合添加5个字符串元素。分别是：“hello”，“itcast”，
	 * “shuangyuan”，“ketang”，“best”。判断集合中是否存在"ketang"这个元素用
	 * “面向对象”和“面向过程”两种思路去做
	 * */
	static ArrayList aList=new ArrayList()	;
	public static void main(String[] args) {
		
		//ArrayList aList=new ArrayList()	;
		aList.add("hello");
		aList.add("itcast");
		aList.add("shuangyuan");
		aList.add("ketang");
		aList.add("best");
		
		//方法三：使用迭代器遍历集合
		Iterator iterator=aList.iterator();
		while (iterator.hasNext()) {
			//强制转换类型
			String string=(String)iterator.next();  //next方法其实返回的是Object类型，这里是强制转换
			System.out.println(string);
		}
		
		//add();
		System.out.println(aList.size());
		//判断是否包含用对象调用就是面向对象的思考方式
		System.out.println(aList.contains("ketang"));
		//判断是否包含的方法一：面向过程
		//首先就是要把list集合转换成数组
		//toArry返回的是Object[]数组向下转型强制转型
		String[] strings=(String[])aList.toArray();
		for (int i = 0; i < strings.length; i++) {
			//String str=strings[i];
			if("ketang".equals(strings[i]))
			{
				System.out.println("找到啦！！");
				
			}else {
				System.out.println("I'm sorry");
			}
		}
		
		
		
		
	}
	
	

}
