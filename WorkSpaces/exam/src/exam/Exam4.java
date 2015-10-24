package exam;

import java.util.Arrays;
import java.util.Scanner;

public class Exam4 {
/*
 * 键盘录入5个数，存储到一个数组中，取最大值和最小值
 * */
	public static void main(String[] args) {
		//定义一个数组
//		int[] arr=new int[5];
//		//因为要输入五次所以用for循环，循环输入五次
//		//把每一次输入的值传给数组
//		for (int i = 0; i <5; i++) {
//			Scanner scanner=new Scanner(System.in);			
//			System.out.println("请输入第"+(i+1)+"个数字：");
//				
//			arr[i]=scanner.nextInt();
			
//			System.out.println("请输入第二个数字：");
//				int two=scanner.nextInt();
//			System.out.println("请输入第三个数字：");
//				int three=scanner.nextInt();
//			System.out.println("请输入第四个数字：");23
//				int four=scanner.nextInt();
//			System.out.println("请输入第五个数字：");
//				int five=scanner.nextInt();
				
		//}
		//先定义个数组
		int[] arr=new int[5];
		//循环输入五个数字
		for(int x=0;x<5;x++)
		{
			System.out.println("请输入第"+x+"个数：");
			Scanner scanner=new Scanner(System.in);
			//把输入的数字传递给数组
			arr[x]=scanner.nextInt();
			
		}
		
		getMax(arr);
	}
//	public static void getMax(int[] arr)
//	{
//		//使用工具类对数组进行排序
//		Arrays.sort(arr);
//		System.out.println("最小的数值"+arr[0]);
//		System.out.println("最大的数值"+arr[arr.length-1]);
//	}

	private static void getMax(int[] arr) {
		// TODO Auto-generated method stub
		//调用工具类进行给数组进行排序
		Arrays.sort(arr);
		System.out.println("最大的数字："+arr[arr.length-1]);
		System.out.println("最小的数字："+arr[0]);
	}
}
