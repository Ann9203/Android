package homework;

import java.util.HashMap;
import java.util.Set;

public class SearchChar {
	/*
	 * 1. 统计字符串中每个字符出现的次数。"
	 * cbxzbvavdvgd"获取字符串中，
	 * 每一个字母出现次数：a(1)b(2)c(1)d(2)g(1)v(3)x(1)z(1)
	 * 
	 * */
	
	public static void main(String[] args) {
		
		String string="cbxzbvavdvgd";
		//首先要把字符串转换成字符数组
		char[] ch=string.toCharArray();
		//因为要有值，和数字，这时候就可以用Map，集合，以chr数组为键，出现的次数为值
		HashMap<Character, Integer> hashMap=new HashMap<>();
		//b遍历ch数组，以ch的元素作为HashMap中的键，得到键查找是否有值
		//如果没有值就在hashMap中将值存入1
		//如果有值就加1
		for (int i = 0; i < ch.length; i++) {
			Integer integer=hashMap.get(ch[i]);
			if(integer==null)
			{
				hashMap.put(ch[i], 1);
			}else {
				integer++;
				hashMap.put(ch[i], integer);
			}
		}
		
		//吧元素都存入Map中之后
		//就通过遍历，将元素存入StringBuilderz中
		StringBuilder sbBuilder=new StringBuilder();
		Set<Character>set=hashMap.keySet();
		for (Character character : set) {
			Integer integer=hashMap.get(character);
			sbBuilder.append(character).append("(").append(integer).append(")");
		}
		String result=sbBuilder.toString();
		System.out.println(result);
		
	}

}
