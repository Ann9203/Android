package demo8;
/*
 * ʵ�ֵĵڶ��ַ�ʽ����ʵ��Runnable�ӿ�
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
				System.out.println(Thread.currentThread()+"---���۳�Ʊ---"+ticket);
				ticket--;
			}
		}
	}

}
