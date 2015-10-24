package Collection;

import java.util.ArrayList;
import java.util.Iterator;
public class ArrayListDemo {
	 /**
	  * 关于ArrayList的小案例
	  * 在ArrayList中存入不重复的对象元素
	  * 步骤：
	  * 	A:就是写一个遍历元素的
	  *       如果不相同就写入新的元素集合中
	  *     B创建一个对象
	  * 
	  * **/
	public static void main(String[] args) {
		ArrayList aList=new ArrayList();
		aList.add(new StudentDemo("小明",12));
		aList.add(new StudentDemo("小红", 13));
		aList.add(new StudentDemo("小明", 12));
		ArrayList aList2=compare(aList);
		Iterator iterator=aList2.iterator();
		while(iterator.hasNext())
		{
			StudentDemo studentDemo=(StudentDemo)iterator.next();
			System.out.println(studentDemo.getName()+"*********"+studentDemo.getAge());
			
		}
		
	}
	//创建一个新的容器，装下不重复的对象
	public static ArrayList compare(ArrayList al)
	{
		ArrayList  newArrayList=new ArrayList();
		Iterator iterator=al.iterator();
		while(iterator.hasNext())
		{
			Object object=iterator.next();
			if(!newArrayList .contains(object))
			{
				newArrayList .add(object);
			}
		}
		return newArrayList;
	}
}
