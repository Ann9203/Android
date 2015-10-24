package exam;

import java.util.TreeSet;

public class Exam5 {
/*
 * 用Math.random生成1，2，3，4，5，6，7，8，9，10
 * */
	public static void main(String[] args) {
		/*
		 * 这个是通过Math.random生成一个有序的数列
		 * 思路：
		 * 		A:显然Math.random生成10个随机数,由于生成的是随机数
		 * 			也不知道是不是会重复，所以把随机数放在TreeSet中
		 * 		B:遍历TreeSet集合
		 * */
		//定义一个TreeSet集合
//		TreeSet<Integer>tSet=new TreeSet<Integer>();	
//		while(tSet.size()<10)
//		{
//			tSet.add((int)Math.random()*10+1);
//		}
//		System.out.println(tSet);
		TreeSet<Integer>te=new TreeSet<>();
		int leng=te.size();
		while(leng<10)
		{
			te.add((int)Math.random()*10+1);
			//System.out.println("Hello");
			int num=(int)Math.random()*10+1;
			System.out.println(num);
			leng++;
		}
		System.out.println(te);
		
	}
}
