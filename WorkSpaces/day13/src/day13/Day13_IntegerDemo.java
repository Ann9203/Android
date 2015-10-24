package day13;

import java.util.Arrays;

public class Day13_IntegerDemo {
	/*
	 * 需求：
	 * 		将一个“23 45 67 89 ”这样的数组，通过排序打印出来
	 * 		打印出来的也是一个字符串格式：“89 67 45 23”
	 * 思路：
	 * 		分割
	 * 		转化成int类型
	 * 		然后转换成int 数组
	 *      sort 排序
	 *      把int类型的数组转换成StringBuffer
	 *      然后Arrays工具类打印出来
	 * */
	public static void main(String[] args) {
		String s="23 12 22 45 34 67 88";
		String [] s1=s.split(" ");
		int [] in=new int[s1.length];
		
		for (int i = 0; i < s1.length; i++) {
			
				in[i]=Integer.parseInt(s1[i]);
			
		};
		Arrays.sort(in);
		//System.out.println(Arrays.toString(in));
		//定义一个StringBuffer，向里边添加元素
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < in.length; i++) {
			sb.append(in[i]).append(" ");
		}
		//stringBuffer转换成String类型用.ToString，
		//用trim去掉两边的空格
		String str=sb.toString().trim();
		System.out.println(str);
	}

}
