package day14;

import java.util.Arrays;

public class StudentTest {
	public static void main(String[] args) {
		/*
		 * 6、一个字符串“23 98 71 54 60”(数字之间通过空格分隔,并且是没有大小顺序的)，
      设计一个功能,把这个字符串排序后变成如下字符串："23 54 60 71 98"
		 * 
		 * */
		String str="23 98 71 54 60";
		//把str先转换成String数组
		String [] str1=str.split(" ");
		int[] in=new int[str1.length];
		for (int i = 0; i < str1.length; i++) {
			//Integer 的parseInt类
			in[i]=Integer.parseInt(str1[i]);
		}
		//工具类的sort方法 ，将转换过来的int型数组进行排序
		Arrays.sort(in);
		//排好序，降int类型的数组转换回去
		StringBuffer sb=new StringBuffer ();
		for (int i = 0; i < in.length; i++) {
			//想StringBuffer中添加数据元素
			sb.append(in[i]).append(" ");
		}
		//降StringBuffer转换成同String字符串，然后trim去掉两端的空格
		System.out.println(sb.toString().trim());
		
	}
}
