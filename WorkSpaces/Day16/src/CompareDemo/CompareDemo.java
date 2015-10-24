package CompareDemo;


public class CompareDemo {
	/*
	 * 1：如果数组的每个元素都比50大？
	 * 2：如果数组的每个元素都比50小？
	 * 3：如果数组里面有的元素比50大？有的元素比50小呢？
	* 
	 * */
	public static void main(String[] args) {
		int[] in={1,2,3,4,5,6,9,8};
		PaiXu(in);
		compare(in);
		System.out.println("***************************");
		int[] in2={65,78,90,23,45,55,44};
		PaiXu(in2);
		compare(in2);
		System.out.println("***************************");
		int[] in3={56,57,58,59,79,66,78,79};
		PaiXu(in3);
		compare(in3);
		//print(in);
	}
	//因为给出的一个数组，有大有小，所以就先排序
	public static void PaiXu(int[] in)
	{
		//判断数组是否为空
		if(in==null || in.length==0)
		{
			System.out.println("数组中没有元素，请添加元素");			
		}
		//用选择排序
		for (int i = 0; i < in.length; i++) {
			for (int j = i+1; j < in.length; j++) {
				compare1(in, i, j);
			}
		}
		
	}
	//比较集合中两个元素的大小
	public static void compare1(int[] in,int a,int b)
	{
		if(in[a]>in[b])
		{
			in[a]=in[a]^in[b];
			in[b]=in[a]^in[b];
			in[a]=in[a]^in[b];
		}
	}
	//重新定义一个数组比较大小的方法
	public static void  compare(int[] in)
	{
		
/*		for (int i = 0; i < in.length; i++) {
			if(in[i]>50)
			{
				for (int j = in.length-1; j >=0; j--) {
					in[i]=in[j];
					
				}
			}else if(in[i]<=50) {
				return in;
			}else {
				
			}
		}
		
		return in;*/
		//在这个方法中，就默认数据传过来的是一个有序的数组
		/*
		 * if判断主要就是分为以下几种情况
		 * 1.前提是有序的数组
		 *    if:
		 *    	1.如果判断的条件，如果数组第一个数大于50，就证明整个数据都比50大
		 *      2.如果数组中最后一个元素都比50小，证明整个数据都比50小
		 *      3.如果介于50左右，有比他打的，也有比50小德，就用选择排序，或者是冒泡排序
		 *      	判断两个元素减去50的绝对值
		 * */
		if(in[0]>50)
		{
			print(in);
		}else if(in[in.length-1]<50) {
			for (int i = in.length-1; i >=0; i--) {
				System.out.println(in[i]);
			}
		
			}else {
				for (int i= 0; i < in.length; i++) {
					for (int j= i+1; j < in.length; j++) {
						if(Math.abs(in[i]-50)>Math.abs(in[j])-50)
						{
							in[i]=in[i]^in[j];
							in[j]=in[i]^in[j];
							in[i]=in[i]^in[j];
						}
					}
				}
				print(in);
			}
		
		
	}
	//打印数组中的元素
	public static void print(int[] in)
	{
		for (int i = 0; i < in.length; i++) {
			System.out.println(in[i]);
		}
	}
}
