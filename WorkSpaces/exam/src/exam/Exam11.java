package exam;

import java.util.ArrayList;
import java.util.ListIterator;

public class Exam11 {
	/*
	 * 12、一个ArrayList对象aList中存有若干个字符串元素，
	 * 现欲遍历该ArrayList对象，删除其中所有值为"abc"的字符串元素，
	 * 请用代码实现。
	 * 思路：
	 * 		首先定义一个Arraylist集合，然后添加元素，
	 * 		之后就是遍历集合
	 * 	B:因为除去冲入的元素，这样的做法就是用ListIterator迭代器
	 * */
	public static void main(String[] args) {
		
		ArrayList<String>al=new ArrayList<String>();
		al.add("abc");
		al.add("adc");
		al.add("aaa");
		al.add("Hell");
		ListIterator<String>listIterator=al.listIterator();
		while(listIterator.hasNext())
		{
			String value=listIterator.next();
			if("abc".equals(value))
			{
				listIterator.remove();
				
			}
		}
		System.out.println(al);
		
		
		
		
	}
	

}
