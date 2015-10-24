package day20lianxi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StringCount {
	/*
	 * 有一个字符串"wweeyusdfaald"
	 * 计算出每个字符串出现的次数
	 * 思路：
	 * 		A:有字符对应出现的次数，也就是键值的关系
	 * 		B:先把字符串转换成字符数组
	 * 		C:然后遍历集合，把集合遍历出来，然后通过StringBuilder打印出来
	 *                              
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		String string="wwwweeyusdfaald";
		char[] ch=string.toCharArray();
		//Integer num=1;
		//定义一个集合
		Map<Character,Integer> mp=new HashMap<Character, Integer>();
		for (Character c : ch) {
			//如果把他复制了固定的值，每次循环都会变回原来的值1
			//Integer num=1;
			Integer num=mp.get(c);
			//判断集合中是否包含关键字如果不包含关键字
			//就添加 Value值为1
			if(!(mp.containsKey(c)))
			{
				mp.put(c, 1);
			}else  {
				num++;
				mp.put(c, num);
			}
			//System.out.println(c);
			
		}
		//在集合中添加完元素后，就要进行StringBuilder，打印出来
		StringBuilder sBuilder=new StringBuilder();
		//遍历Map集合
		
		Set<Character>key=mp.keySet();
		
		for (Character character : key) {
			sBuilder.append(character).append("(").append(mp.get(character)).append(")");
			//System.out.println(character+"*************"+mp.get(character));
			
		}
		String result=sBuilder.toString();
		System.out.println(result);
//		String result=sBuilder.toString();
//		System.out.println(result);
		
	}

}
