package exam;

import java.util.Set;
import java.util.TreeMap;

import sun.reflect.generics.tree.Tree;

public class Exam16 {
	/*
	 * 
	 * 7、取出一个字符串中字母出现的次数。如：字符串："abcdekka27qoq" ，输出格式为：a(2)b(1)k(2)...
	 * 
	 * 思路：
	 * 		A:将字符串转换为字符数组
	 * 		B:然后创建Map集合
	 * 		C:通过条件判断，把数组中的元素添加到Map集合中去
	 * 		D:然后通过BufferBuilder来进行打印出元素来
	 * 	
	 * */
	public static void main(String[] args) {
		String string="abcdekka27qoq";
		char[] chs=string.toCharArray();
		//定义map 集合
		TreeMap< Character,Integer> trm=new TreeMap<>();
		for (char c : chs) {
			Integer integer=trm.get(c);
			if(integer==null)
			{
				trm.put(c, 1);
			
			}else {
				//先加加在进行赋值
				integer++;
				trm.put(c, integer);
				
			}
		}
		StringBuilder sb=new StringBuilder();
		Set<Character>set=trm.keySet();
		for (Character character : set) {
			Integer integer=trm.get(character);
			sb.append(character).append("(").append(integer).append(")");
		}
		String result=sb.toString();
		System.out.println(result);
		
		
		
	}
}
