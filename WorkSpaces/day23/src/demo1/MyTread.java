package demo1;

public class MyTread extends Thread {
	public void run()
	{
		for(int x=0;x<100;x++)
		{
			System.out.println(getName()+"*****"+ x);
		}
	}

}
