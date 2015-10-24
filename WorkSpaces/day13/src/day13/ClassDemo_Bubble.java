package day13;

public class ClassDemo_Bubble {
	/*
	 * 实现一个冒泡排序方法
	 * 思路：冒泡排序就是两辆元素进行对比，如果大元素就往后排，每次都会找出这一轮
	 * 中最大的元素
	 * */
	public static void main(String[] args) {
		int[] arr={3,6,3,8,4,9,2};
		bubbleDemo(arr);
		print(arr);
		
	}
	//因为数组是引用类型的，改变了就是连最初的值都会发生改变，所以可以没有返回值
	//但是实际上是已经改变了
	public static void bubbleDemo(int[] arr)
	{
		//外层循环控制中的循环次数
		for (int x = 0; x < arr.length-1; x++) {
			//内层循环就是每次要循环要比较的元素的个数，因为每次比较都会有最大值出现
			//所以选出来的最值就不需要再进行比较了
			for (int y = 0; y < arr.length-1-x; y++) {
				compare(arr, y, y+1);
			}
		}
	}
	//比较元素的大小
	public static void compare(int[] arr,int a,int b)
	{
		if (arr[a]>arr[b]) {
			arr[a]=arr[a]^arr[b];
			arr[b]=arr[a]^arr[b];
			arr[a]=arr[a]^arr[b];
			
		}
	}
	//打印数组
	public static void print(int[] arr)
	{
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
