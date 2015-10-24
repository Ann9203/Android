package Collection;

import java.util.ArrayList;
import java.util.Collection;

public class ForeachDemo {
	public static void main(String[] args) {
		
		//JDK1.5之后的新特点是：foreach
		//foreach的特点： for(元素中变量类型 临时变量：容器变量)
		//foreach循环是一种更加简洁的循环
		Collection collection =new ArrayList();
		collection .add("Hello");
		collection .add("Every");
		collection .add("body");
		collection .add("How");
		collection .add("are");
		collection .add("you");
		//foreach循环不能向下转型
		
		for (Object obj:collection)
		{
			System.out.println(obj);
		}
		for(Object obj:collection )
		{
			System.out.println(obj);
		}
		for(Object object :collection )
		{
			System.out.println(object );
		}
		for (Object object :collection ) {
			System.out.println(object);
		}
	}

}
