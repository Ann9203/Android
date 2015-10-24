package demo2;

public class MyRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int x=0;x<100;x++)
		{
			System.out.println(Thread.currentThread().getName()+"´ó¹úáÈÆð"+x);
		}
		
	}
	

}
