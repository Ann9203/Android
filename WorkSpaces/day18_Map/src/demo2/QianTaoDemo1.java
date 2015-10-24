package demo2;

import java.util.HashMap;
import java.util.Set;

public class QianTaoDemo1 {
	//嵌套循环Map
	public static void main(String[] args) {
		//Map集合
		//创建一个大的集合
		HashMap<String , HashMap<String, StudentDemo>>chuanzhi=new HashMap<>();
		//定义一个预热半集合
		HashMap<String, StudentDemo>ry=new HashMap<>();
		//在预热半中添加相应的元素
		ry.put("20145", new StudentDemo("小雪", 23));
		ry.put("20146", new StudentDemo("小名", 22));
		ry.put("20142", new StudentDemo("小云", 24));
		ry.put("20141", new StudentDemo("小雨", 22));
		ry.put("20147", new StudentDemo("小雷", 21));
		//定义一个就业班的集合
		HashMap<String, StudentDemo>jy=new HashMap<>();
		//在就业班中添加相应的元素
		jy.put("20145", new StudentDemo("小雪", 23));
		jy.put("20146", new StudentDemo("小名", 22));
		jy.put("20142", new StudentDemo("小云", 24));
		jy.put("20141", new StudentDemo("小雨", 22));
		jy.put("20147", new StudentDemo("小雷", 21));
		
		//在整个学院中添加元素
		chuanzhi.put("rure", ry);
		chuanzhi.put("jiuye", jy);
		//遍历整个学院的元素
		Set<String>keySet=chuanzhi.keySet();
		for (String string : keySet) {
			System.out.println(string);
			HashMap<String, StudentDemo>xuesheng=chuanzhi.get(string);
			Set<String>xue=xuesheng.keySet();
			for (String string2 : xue) {
				StudentDemo stu=xuesheng.get(string2);
				System.out.println(string2+"\t"+stu.getName()+"\t"+stu.getAge());
				}
		}
		
	}

}
