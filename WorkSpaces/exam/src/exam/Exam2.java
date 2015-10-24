package exam;

public class Exam2 {
	/*
	 * 、1-100 中个位和十位不含 7 的数
	 * 思路：
	 * 		A:使用for循环，
	 * 		B:分解1-100中的两位数字
	 * 		C:进行判断是否每个数字与7相等
	 * 		D:打印出不相等的数字
	 * */
	public static void main(String[] args) {
		
		//分解shi位和ge位
		
		int shi=0;
		int ge=0;
		for (int i = 1; i < 100; i++) {
			 shi=i/10;			
			 ge=i%10;
			 //是要有一个等于7就不可以了
			if(ge!=7 && shi!=7)
			{
				System.out.println(i);
			}
			
		}
	}
}
