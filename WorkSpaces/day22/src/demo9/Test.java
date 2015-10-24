package demo9;

public class Test {
	public static void main(String[] args) {
/*		MyTR myTR=new MyTR();
		MyTR myTR2=new MyTR();
		MyTR myTR3=new MyTR();
		MyTR myTR4=new MyTR();
		myTR.setName("售票1号窗口");
		myTR2.setName("售票2号窗口");
		myTR3.setName("售票3号窗口");
		myTR4.setName("售票4号窗口");
		myTR.start();
		myTR2.start();
		myTR3.start();
		myTR4.start();*/
		
		MyRunnable myRunnable=new MyRunnable();
		Thread t1=new Thread(myRunnable);
		Thread t2=new Thread(myRunnable);
		Thread t3=new Thread(myRunnable);
		Thread t4=new Thread(myRunnable);
		
		t1.setName("一号窗口");
		t2.setName("二号窗口");
		t3.setName("三号窗口");
		t4.setName("四号窗口");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
