package demo2;

import java.util.Set;
import java.util.TreeMap;

public class CharDemo {
	/*
	 * 有一个字符串:  "cbxzbvavdvgd"  要求获取字符串中，
	 * 每一个字母出现次数："a(1)b(2)c(1)d(2)g(1)v(3)x(1)z(1)"
	 * 大家想一下 思路 ！
	 * A：吧字符串转换成字符数组
	 * B:创建一个集合，以字符为主键，多少个为值
	 * C:遍历数组，根据字符得到值，看看Map集合中有没有值，如果有的话
	 * 就在原基础上加一，如果没有的话就为1
	 * D: 创建一个stringBulider，然后遍历Map集合，降值和建写入StringBuilder中
	 * 
	 * */  
	public static void main(String[] args) {
		
		String string="cbxzbvavdvgd";
		//创建一个字符数组
		char[] ch=string.toCharArray();
		//定义一个Map集合
		TreeMap<Character, Integer>map=new TreeMap<>();
		//遍历数组
		for (Character c : ch) {
			Integer integer=map.get(c);
			if(integer==null)
			{
				map.put(c, 1);
			}else {
				integer++;
				map.put(c, integer);
			}
		}
		//创建一个StringBuilder，因为比StringBuffer要高效率
		
		StringBuilder sBuilder=new StringBuilder();
		//循环Map集合，将集合中的元素添加到StringBuilder中去
		Set<Character> key=map.keySet();
		for (Character character : key) {
			Integer integer=map.get(character);
			sBuilder.append(character).append("(").append(integer).append(")");
		}
		String result=sBuilder.toString();
		System.out.println(result);
	}

}
