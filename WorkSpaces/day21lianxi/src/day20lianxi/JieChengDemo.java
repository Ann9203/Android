package day20lianxi;

public class JieChengDemo {
	/*
	 * 求出5的阶乘
	 * 通过递归这个方法
	 * 首先：
	 * 		找到出口 ：出口就是传入的数据是1的时候
	 * 		找到规律：不为1的时候就是5*4*3*2*1
	 * 
	 * */
		public static void main(String[] args) {
			int num=getCount(5);
			System.out.println(num);
			
		}
		public static int getCount(int num)
		{
			//通过递归求出5的阶乘
			if(num==1)
			{
				return 1;
			}else
			{
				//如果不为1的话
				//就用上一个数字得到的结果乘以这次的数值
				return num*getCount(num-1);
			}
			
		}
	

}
