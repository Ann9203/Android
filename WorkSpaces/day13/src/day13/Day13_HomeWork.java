package day13;

public class Day13_HomeWork {

		/*
		 * 自学选择排序
		 * 选择排序就是拿第一个元素一次和后边的元素进行对比，
		 * 然后选择出最小值
		 * */
	public static void main(String[] args) {
		int[] in={23,56,13,45,78,34};
		select(in);
		for (int i = 0; i < in.length; i++) {
			System.out.print(in[i]+" ");
		}
	}
	//Select 排序
	public static void select(int[] arr)
	{
		for (int x = 0; x < arr.length; x++) {
			for(int y=x;y<arr.length;y++)
			{
				compare(arr, x, y);
			}
		}
	}
	public static void compare(int[] arr,int a,int b)
	{
		
		if(arr[a]>arr[b])
		{
			arr[a]=arr[a]^arr[b];
			arr[b]=arr[a]^arr[b];
			arr[a]=arr[a]^arr[b];
		}
	}
}
