package demo9;
public class MyRunnable extends Thread {
	/*
	 * 实现上锁
	 * */
	private static int ticket=200;
	Object object= new Object();
	public void run()
	{
		while(true)
			
			//所有的数据进来了，
				//这时候就要上锁，看谁先到
				synchronized (object) {
					if(ticket>0)
					{
						try {
							//睡眠
							Thread.sleep(200);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						//打印车票
						System.out.println(Thread.currentThread().getName()+"---出售车票"+ticket--);
						
					}
				
			}
				
				
			
	}

}
