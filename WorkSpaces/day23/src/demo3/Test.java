package demo3;

public class Test {
	public static void main(String[] args) {
		MyRunnable myRunnable=new MyRunnable();
		Thread thread=new Thread(myRunnable);
		Thread thread2=new Thread(myRunnable);
		Thread thread3=new Thread(myRunnable);
		Thread thread4=new Thread(myRunnable);
		thread.setName("1����Ʊ��");
		thread2.setName("2����Ʊ��");
		thread3.setName("3����Ʊ��");
		thread4.setName("4����Ʊ��");
		thread.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
	}

}
