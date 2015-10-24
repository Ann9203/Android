package demo2;

import java.util.HashMap;
import java.util.Set;

import sun.awt.image.PNGImageDecoder.Chromaticities;

public class CharCount {
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
		//把字符串转换成数组
		char[] ch=string.toCharArray();
		//创建一个集合，可以装载字符数组和count数的Map集合
		HashMap<Character, Integer>charcount=new HashMap<>();
		//遍历数组，然后在Map集合中添加元素
		//直接强转成Character类型的
		for (Character c : ch) {
			Integer integer=charcount.get(c);
			//如果键值为Null值
			if(integer==null)
			{
				charcount.put(c, 1);
			}else {
				integer++;
				charcount.put(c, integer);
			}
		}
		//添加好元素，
		//就要把Map集合中的元素转换成String类型的打印出来
		StringBuilder sBuilder=new StringBuilder();
		Set<Character>characters=charcount.keySet();
		for (Character character : characters) {
			Integer integer=charcount.get(character);
			sBuilder.append(character).append("(").append(integer).append(")");
		}
		String result=sBuilder.toString();
		System.out.println(result);
		
	}
}
