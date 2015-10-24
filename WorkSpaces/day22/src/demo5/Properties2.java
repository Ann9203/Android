package demo5;

import java.util.Properties;
import java.util.Set;

public class Properties2 {
	
	/*
	 * 修改功能
	 * 		
	 * public object setProperty(string key,string value)
	 * 
	 * 获取功能
	 * public String getProperty(string key);
	 * public string getProperty(string key,string default)
	 * public set<string> stringPropertyname()
	 * 	
	 * */
	public static void main(String[] args) {
		//创建properties对象
		Properties properties=new Properties();
		//修改功能
		//如果之前没有这个键，就返回null，然后添加这个键
		System.out.println(properties.setProperty("张飞", "关羽"));
		
		properties.setProperty("杨过", "小龙女");
		properties.setProperty("郭靖", "黄蓉");
		//根据键的值，获取值
		System.out.println(properties.getProperty("小龙女"));
		//
		System.out.println(properties.getProperty("杨过", "小女"));
		
		//获取的是键集合
		Set<String> namSet=properties.stringPropertyNames();
		for (String string : namSet) {
			System.out.println(string);
		}
	}

}
