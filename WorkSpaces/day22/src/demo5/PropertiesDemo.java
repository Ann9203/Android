package demo5;

import java.util.Properties;
import java.util.Set;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class PropertiesDemo {
	/*
	 * proper：是一个表示属性的集合，可以从流中加载数据或者是把流中的数据存储，键和值都是字符串
	 * 	是一个唯一一个可以和IO流进行结合使用的集合类
	 * Properties的父亲是HasTa不了，所以我们知道他是一个Map的体现
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		
		//创建properties的对象
//		Properties prop=new Properties();
//		prop.put("001", "李雪");
//		prop.put("002", "郑博");
//		//读取Map中的数据
//		//获取键值的集合
//		Set<Object> set=prop.keySet();
//		for (Object object : set) {
//			Object value=prop.get(object);
//			System.out.println(object+"*****"+value);
//		}
		Properties properties=new Properties();
		properties.put("001", "snow");
		properties.put("002","windy");
		Set<Object> set=properties.keySet();
		for (Object object : set) {
			Object value=properties.get(object);
			System.out.println(object+"********"+value);
		}
	}

}
