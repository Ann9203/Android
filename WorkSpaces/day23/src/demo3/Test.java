package demo3;

public class Test {
	public static void main(String[] args) {
		MyRunnable myRunnable=new MyRunnable();
		Thread thread=new Thread(myRunnable);
		Thread thread2=new Thread(myRunnable);
		Thread thread3=new Thread(myRunnable);
		Thread thread4=new Thread(myRunnable);
		thread.setName("1号售票口");
		thread2.setName("2号售票口");
		thread3.setName("3号售票口");
		thread4.setName("4号售票口");
		thread.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
	}

}
