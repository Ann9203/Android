package demo7;

public class Test {
	public static void main(String[] args) {
		MyRunnable m1=new MyRunnable();
		Thread t1=new Thread(m1);
		Thread t2=new Thread(m1);
		t1.setName("С��");
		t2.setName("Сѩ");
		t1.start();
		t2.start();
		
	}
}
