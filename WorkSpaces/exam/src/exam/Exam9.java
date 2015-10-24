
package exam;

import java.util.TreeSet;

public class Exam9 {
	/*
	 * 编写一个程序，获取10个1至20的随机数，要求随机数不能重复。
	 * 思路：
	 * 		A:获取10个1到20之间的随机数
	 * 			可以用Random类的nextln
	 * 		B:获取随机数补可以重复，这样可以使用TreeSet集合，是不重复的
	 * */
	
	public static void main(String[] args) {
		
		//定义一个TreeSet集合
		TreeSet<Integer> ts=new TreeSet<Integer>();
		//因为是获取10个随机数，所以呢set集合的长度要小于等于10
		while(ts.size()<10)
		{
			//将回去的随机数添加到set集合中去
			ts.add((int)(Math.random()*20+1));
		}
		//打印出这是个数值
		System.out.println(ts);
	}
	

}
