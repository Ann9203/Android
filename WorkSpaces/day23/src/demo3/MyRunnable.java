package demo3;

public class MyRunnable implements Runnable{

	private static int ticket=200;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if(ticket>0)
			{
				System.out.println(Thread.currentThread().getName()+"���ڳ��۳�Ʊ��������"+ticket--);
			}	
			
		}
		
	}
	

}
