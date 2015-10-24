package digui;

public class DiGui {
   /*
    * 用递归求下列数列的第二十项的值：
 1，1，2，4，7，13，24...
    * 
    * */
	public static void main(String[] args) {
		
		System.out.println(getCount(20));
	}
	public static int getCount(int i)
	{
		if(i==1)
		{
			return 1;
		}else if (i==2){
			 return 1;
		}else if(i==3)
		{
			return 2;
		}else {
			return getCount(i-1)+getCount(i-2)+getCount(i-3);
		}
	}
}
