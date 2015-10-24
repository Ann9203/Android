package day13;

import java.util.Arrays;

public class SystemDemo {
	public static void main(String[] args) {
		//exit 退出
		//System.exit(0);
		//System.out.println("HelloWorld");
//		long start=System.currentTimeMillis();
//		for (int i = 0; i <100000000; i++) {
//			System.out.println(i);
//			
//		}
//		long end=System.currentTimeMillis();
		//System.out.println((end-start)+"毫秒");
		int [] arr={2,4,65,8,9,4,3};
		int [] arr1=new int[10];
		System.arraycopy(arr, 1, arr1, 1, 3);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(arr1));
		
	}
}
