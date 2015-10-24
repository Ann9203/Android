package day13;

import java.util.Random;
public class StudentAnswer {
		public static void main(String[] args) {
			
			String[] name={"李雪","周爱丽","马丽梅","吕诗禹","李三","李四","王五","王六"};
			Random r=new Random();
			/*int x=r.nextInt(10);
			System.out.println(x);
			for (int i = 0; i < name.length; i++) {
				if(i==x)
				{
					
					System.out.println(name[i]);
				}
				else if (x>name.length-1) {
					System.out.println("班上没有此索引的人");
					
				}
			}*/
			//今天脑子进水了，方法二
			//因为随机数中需要传入一个长度值，直接把name的长度传给他，然后让随机数的值给name数组做索引
			int i=r.nextInt(name.length);
			System.out.println(name[i]);
		}

}
