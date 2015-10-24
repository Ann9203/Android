package day13;

public class ClassDemo_Binnary {
	/*
	 * 二分查找：
	 * 		思路：首先是有序的数组
	 * 			  查找数组，从中间开始查找，然后就是比较关键字，如果比他大
	 * 			  那么就在中间值右边查找，start=mid+1;
	 * 			  如果小就在左边查找：end=mid-1
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		
		int[] arr={1,2,6,7,8,9};
		
		System.out.println(binnary(arr,7));
		
	}
	public static int binnary(int[] arr,int key)
	{
		int start=0;
		int end=arr.length-1;
		int mid=(start+end)/2;
		while(start<=end)
		{
			if (arr[mid]==key) {
				return mid;
			}
			else if (arr[mid]>key) {
				end=mid-1;
				mid=(start+end)/2;
			}
			else if (arr[mid]<key) {
				start=mid+1;
				mid=(start+end)/2;
			}
		}
		return -1;
		
	}

}
