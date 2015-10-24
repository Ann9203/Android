package day15;

public class MaxStrMinStrDemo {
	/*
	 * 给定一个字符串，然后在给定一个小的字符串
	 * 在大字符串中获取小字符串出现的次数是多少
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		
		String s="uuusshshshsuusdfuuusdfsdfsdfsuu";
		//System.out.println(s);
		int i=getCount(s, "uu");
		System.out.println(i);
		
	}
	/*
	 * 方法名称　　获取总数和
	 * 步骤：
	 * 	　　好找与字符串中包含多少个uu
	 * 		包含 用indexOf（）返回第一次出现的位置
	 * 		然后寻找完第一次出现的，就截取用indexOf索引值+要找的字符串的额长度
	 *      然后继续寻找，知道indexOf返回-1
	 *      也就是没有找到
	 *      
	 * */
	public static int getCount(String maxstr,String minstr)
	{
		//定义一个数数的变量count
		int count=0;
		//System.out.println(count);
		//indexOf返回maxstr字符串中，第一次出现的minstr的索引值
		int index=maxstr.indexOf(minstr);
		System.out.println(index);
		//如果索引值不等于-1就说明有相同的字符串
		while(index!=-1)
		{
			//找到一个就加一
			count++;
			//System.out.println(count);
			//截取字符串之后，继续查找index
			maxstr=maxstr.substring(index+minstr .length(),maxstr.length());
			//System.out.println(maxstr);
			index=maxstr.indexOf(minstr);
			//System.out.println(index);
		}
		//返回count
		return count;
	}

}
