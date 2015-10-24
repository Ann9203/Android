package Collection;

import java.util.HashSet;
import java.util.Iterator;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class HashSetDemo {
	//set的特点就是：元素是不可以重复的，而且元素的排列是无需的
   //set有两个实现类 hasSet TreeSet
	public static void main(String[] args) {
		//本案例主要讲的是hasSet
		//hasCode是元素的存储位置
		//equals方法来判断元素是否是重复的
		 HashSet hSet=new HashSet();
		 hSet.add("java");
		 hSet.add("Andriod");
		 hSet.add("Hello");
		 //存储一个相同的元素
		 hSet.add("java");
		 Iterator iterator =hSet.iterator();
		 while(iterator.hasNext())
		 {
			 //HashSet中并没有把相同的元素打印出来
			 String string =(String)iterator .next();
			 System.out.println(string.hashCode()+"***********"+string.equals(string));
		 }
		 //以上是HashSet存入的是字符串，在存入String类的字符串之前，已经对HashCode和equals进行了重写
		 //如果存入的是String类的对象。。
		 HashSet hashSet  =new HashSet();
		 //但hashSet存储对象的时候，相同的对象确实可以能够存进去，之所以两个重复的对象没有被省略掉一个
		 //是因为这Student类没有重写HashCode值和equals方法。
		 hashSet .add(new StudentDemo("小李",12));
		 hashSet .add(new StudentDemo("小明", 12));
		 hashSet .add(new StudentDemo("小李", 12));
		 Iterator iterator2=hashSet .iterator();
		 while(iterator2.hasNext())
		 {
			 StudentDemo stu=(StudentDemo)iterator2 .next();
			 System.out.println(stu.getName()+"**************"+stu.getAge());
			 System.out.println(stu.hashCode()+"********************"+stu.equals(stu));
		 }
	}

}
