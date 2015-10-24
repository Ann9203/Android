`package demo4;

import java.util.ArrayList;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

public class ArrayListDemo {
/*
 * 题目：
 * 	编写程序，生成5个1至10之间的随机整数，
 * 	存入一个List集合，编写方法对List集合进行排序 * （自定义排序算法，禁用Collections.sort方法和TreeSet），
 * 	然后遍历集合输出。
 * */
	public static void main(String[] args) {
		ArrayList<Integer> arrayList=new ArrayList<Integer>();
			while(arrayList.size()<5)
			{
				arrayList.add((int)(Math.random()*10+1));
			}
			//System.out.println(arrayList);
			int leng=arrayList.size();
			//选择排序，最终是排列出最小的数字，一直排序的是索引为1的元素
			for(int x=0;x<leng;x++)
			{
				for(int y=x;y<leng;y++)
				{
					if(arrayList.get(x)>arrayList.get(y))
					{
						int temp=arrayList.get(x);
						arrayList.set(x, arrayList.get(y));
						arrayList.set(y, temp);
					}
				}
			}
			System.out.println(arrayList);
//			for (Integer integer : arrayList) {
//				System.out.printl(integer);
//			}
		
//		int [] array=new int[arrayList.size()];
//		int leng=arrayList.size();
//		for (int i = 0; i < leng; i++) {
//			array[i]=arrayList.get(i);
//		}
//		Bubble(array);
		
		
	}
	
}
