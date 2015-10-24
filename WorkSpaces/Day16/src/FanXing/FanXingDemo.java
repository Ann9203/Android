package FanXing;

import java.util.ArrayList;
import java.util.Iterator;

public class FanXingDemo {
	/*
	 * 现在我们学习了泛型，我们就用泛型改进一下之前一直做的遍历String字符串的例子。
	 * 提示集合采用ArrayList, 然后存储"hello", "world", "java" 三个字符串,然后遍历它们.

	 * */
	public static void main(String[] args) {
		
			ArrayList<String>arrayList=new ArrayList<String>();
			ArrayList<String>arrayList2=new ArrayList<>();
			//FanXingClass<String> fu=new FanXingClass<>();
			//fu.show();
			//arrayList2.add("Hello");
		
			
			arrayList.add("hello");
			arrayList.add("world");
			arrayList.add("java");
			arrayList.add(".");
			for(String str:arrayList)
			{
				System.out.println(str);
			}
			Iterator<String>iterator=arrayList.iterator();
			while (iterator.hasNext()) {
				String string=iterator.next();
				System.out.println(string);
				
			}
			
			Integer integer=1;
			Integer inte=2;
			if (inte>integer) {
				
			}
			
	}
}
