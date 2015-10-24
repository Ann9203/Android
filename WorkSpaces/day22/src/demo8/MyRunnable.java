package demo8;
/*
 * 实现的第二种方式就是实现Runnable接口
 * */
public class MyRunnable implements Runnable  {
	private static int ticket=200;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		sold();
	}
	public synchronized void sold()
	{
		while(true)
		{
			if(ticket>0)
			{
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread()+"---出售车票---"+ticket);
				ticket--;
			}
		}
	}

}
