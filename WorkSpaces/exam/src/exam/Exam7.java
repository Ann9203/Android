package exam;
import java.util.ArrayList;
import java.util.Collections;
public class Exam7 {
	/*
	 * //求三位数的质数，只能被 本身和1整除,倒序输出
	 * 思路：
	 * 		首先的就是for循环
	 * 		B:假如所有的数字都是质数定义一个boolean类型
	 * 		C:质数就是可以被1和它本身整出的数，如果这个书被2以及到这个数-1的数可以整出的话
	 * 			就不是质数
	 * 			判断玩后，就将flag=false
	 * */
	public static void main(String[] args) {
//		boolean falge;
//		//倒序打出，用Collections的工具类，或者使用数组
//		ArrayList<Integer>arrayList=new ArrayList<Integer>();
//		for (int a=100;a<1000;a++) {
//			falge=true;
//			for (int b = 2; b < a; b++) {
//				if(a%b==0)
//				{
//					falge=false;
//					//如果为false证明不为质数，然后直接跳出循环，进行下一个数字的判断
//					break;
//					
//				}
//				
//			}
//			if(falge)
//			{
//				arrayList.add(a);
//			}
//			
//		}
//		java.util.Collections.reverse(arrayList);
//		System.out.println(arrayList);
		//因为是倒序打出，所以定义一个Arraylist集合，然后使用Collections工具类
		ArrayList< Integer>arrayList=new ArrayList<Integer>();
		//定义flag来判断是否是质数
		boolean flag;
		//是三位数字，，定义一个数组
		for(int a=100;a<1000;a++)
		{
			flag=true;
			for(int b=2;b<a;b++)
			{
				if(a%b==0)
				{
					flag=false;
					//不是质数就要跳出程序
					break;
				}
			}
			//循环判断完了后，符合条件的a值打印出来
			if(flag)
			{
				arrayList.add(a);
			}
		}
		//在集合中添加完元素后就要使用Collections进行元素反转
		Collections.reverse(arrayList);
		System.out.println(arrayList);
	}

}
