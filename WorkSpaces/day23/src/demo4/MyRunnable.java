package demo4;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class MyRunnable implements Runnable{

	/*
	 * 为了解决抢夺资源的问题出现负票  出现重复的票
	 * 线程具有随机性和延迟性要上锁
	 * */
	private static int ticket=200;
	public Object object=new Object();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//循环一直是true
		while(true)
		{
			//但是个线程都进来的时候，就要判断是否票数大于o
			synchronized (object) {
				if(ticket>0)
				{
					System.out.println(Thread.currentThread().getName()+"----正在出票----"+ticket--);
				}
			}
		}
		
	}

}
