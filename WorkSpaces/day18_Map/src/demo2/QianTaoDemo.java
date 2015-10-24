package demo2;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;


public class QianTaoDemo {
	/*
	 *   用HashMap的key来存预热班和就业班,用value来存ArrayList集合, 
	 *   ArrayList集合中再来存Student对象.Student对象中再来存数据. 
	 * 
	 * */
	public static void main(String[] args) {
		
		//首先创建一个大的Map集合
		TreeMap<String , ArrayList<StudentDemo>>tMap=new TreeMap<>();
		//其次就是创建一个预热半的ArrayList集合
		ArrayList<StudentDemo>yure=new ArrayList<>();
		yure.add(new  StudentDemo("小雪", 12345));
		yure.add(new  StudentDemo("小风", 12346));
		yure.add(new  StudentDemo("小累", 12348));
		yure.add(new  StudentDemo("小雨", 12349));
		//其次就是创建一个就业班的ArrayList集合
		ArrayList<StudentDemo>jiuye=new ArrayList<>();
		jiuye.add(new  StudentDemo("小云", 12347));
		jiuye.add(new  StudentDemo("小朵", 12342));
		jiuye.add(new  StudentDemo("小化", 12343));
		jiuye.add(new  StudentDemo("小草", 12341));
		
		//在整个大集合中添加元素
		tMap.put("JY", jiuye);
		tMap.put("YR", yure);
		//循环整个大的集合
		Set<String >key=tMap.keySet();
		for (String string : key) {
			//第一层循环，循环的主要就是根据key值得到Value值
			ArrayList<StudentDemo> stu=tMap.get(string);
			System.out.println(string);
			//第二层循环，循环Value值，也就是循环Arraylist集合
			for (StudentDemo studentDemo : stu) {
				System.out.println("\t"+studentDemo.getName()+"\t"+studentDemo.getAge());
			}
			
		}
		
	}

}
