package demo8;

/*
 * 实现买票的功能
 * 	方法一：Thread
 * 		首先就是定义一个类来继承Thread类
 * 		票的数量是固定的200张，定义为常量
 * 		重写run方法，在买票的时候要大于0，然后就是每次都要睡眠200毫秒，避免线程冲突
 * 		
 * */
public class MyTR extends Thread{
	private static int tiket=200;
	public void run()
	{
		while(true)
		{
			if(tiket>0)
			{
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				System.out.println(getName()+"----出票-----"+tiket);
				tiket--;
			}
		}
	}	
		

}
