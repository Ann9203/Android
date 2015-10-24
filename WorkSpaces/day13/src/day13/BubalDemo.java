package day13;
/*
 * 关于冒泡排序
 * 思路：
 * 	A:冒泡排序主要就是一次向后对比1和2对比2和3对比，3和四对比
 * 	B:对比的次数就是元素个数减一
 * 	C:大循环控制的是循环的次数，小循环就是控制本次要循环的元素的个数
 *  D:可以单独封装几个方法
 * */
public class BubalDemo {
	public static void main(String[] args) {
		int[] arr={2,5,3,7,2,9};
		int [] arry=boll(arr);
//		for (int i = 0; i < arry.length; i++) {
//			System.out.print(arr[i]);
//		}
		bianli(arry);
	}

	public  static int[] boll(int[] arr)
	{
		for (int i = 0; i < arr.length-1; i++) {
			for (int y = 0; y < arr.length-1-i; y++) {
				//将封装成一个方法
//				if (arr[y]>arr[y+1]) {
//					arr[y]=arr[y]^arr[y+1];
//					arr[y+1]=arr[y]^arr[y+1];
//					arr[y]=arr[y]^arr[y+1];				
//				}
				compare(arr, y, y+1);
			}
			//System.out.print(arr[y+1]);			
		}
		return arr;
	}
	//即使没有返回值也会实现此对比的功能
	public static void compare(int[] arr,int x,int y)
	{
		if(arr[x]>arr[y])
		{
			arr[x]=arr[x]^arr[y];
			arr[y]=arr[x]^arr[y];
			arr[x]=arr[x]^arr[y];
		}
	}
	public static void bianli(int[] arr)
	{
		if (arr.length==0||arr==null) {
			System.out.println("数组为空，不能遍历，请放入元素！！");
		}else {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i]);
			}
			
		}
	}
	
}
