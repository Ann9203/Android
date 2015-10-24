package exam;

public class Exam3 {
	/*
	 * 21、九九乘法表
		22、冒泡排序
		23、懒汉式
	 * */
	public static void main(String[] args) {
//		JiuJiui();
//		int[] arr={56,77,33,88,34,12};
//		Bubble(arr);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i]);
//		}
		System.out.println(SingleDemo.getInstance());
		System.out.println(SingleDemo.getInstance());
		
		
	}
//	public static void JiuJiui()
//	{
//		for(int x=1;x<=9;x++)
//		{
//			for(int y=1;y<=x;y++)
//			{
//				System.out.print(y+"*"+x+"="+x*y+"\t\t");
//			}
//			System.out.println();
//			
//		}
//	}

	private static void JiuJiui() {
		// TODO Auto-generated method stub
		for(int x=1;x<=9;x++)
		{
			//内循环，y要小于等于x
			for(int y=1;y<=x;y++)
			{
				//打印同一组的数据，是不需要进行换行的
				System.out.print(x+"*"+y+"="+x*y+"\t");
			}
			//循环完一组就要进行换行，打印下一组
			System.out.println();
		}
		
	}
	public static void Bubble(int[] arr)
	{
		/*
		 * 实现冒泡排序
		 * 		思路：
		 * 			冒泡排序主要就是两两进行对比，每循环一次就会选择出最大来
		 * */
		
		//循环数组
		//外循环控制循环的次数
		for (int i = 0; i < arr.length-1; i++) {
			//每次循环出来的大的数字排列在最后变，所以每次都要少循环i个元素
			for(int j=0;j<arr.length-1-i;j++)
			{
				//调用比较方法
				compare(arr, j, j+1);
			}
		}
	}
	public  static void compare(int[] arr,int a,int b)
	{
		//首先要明确数据不为空
		if (arr!=null)
		{
			//进行判断
			if(arr[a]>arr[b])
			{
				//通过位移来进行交换元素
				arr[a]=arr[a]^arr[b];
				arr[b]=arr[a]^arr[b];
				arr[a]=arr[a]^arr[b];
			}
		}
	}
	
	
}
