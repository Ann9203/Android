package day13;

/*
 * 
 * 3：编程练习
	A:数组遍历
		int[] arr = {23,23,16,35,72};
	  写一个对象数组操作的功能，实现把数组中的数据按照如下格式返回：
		"[98, 23, 16, 35, 72]"    //  arr[i]+","

	B:查找数组中指定元素第一次出现的索引值。
		int[] arr = {98,23,16,35,72};  arr[i]   if(arr[i]==23){syso(i);}
		查找23在数组中的索引值。
	代码用Eclipse实现。
*/
public class ArrayDemo {
	public static void main(String[] args) {
		//A:遍历数组
		//代码要进行修改，健壮行，判断arra是否为空或者是否是没有元素
		int[] arr={23,23,16,35,72};
		for (int i = 0; i < arr.length; i++) {
			if (i==0) {
				System.out.print("["+arr[i]+",");
				
			}else if (i==arr.length-1) {
				System.out.println(arr[i]+"]");
			}
			else {
				System.out.print(arr[i]+",");
			}
		}
		//B查找数组中指定元素第一次出现的索引值
		int count =0;	
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]==23)
			{
				count++;
				if(count==1)
				{
					System.out.println(i);
				}
			}
		}
		
	}

}
