package demo9;

public class Test {
	public static void main(String[] args) {
/*		MyTR myTR=new MyTR();
		MyTR myTR2=new MyTR();
		MyTR myTR3=new MyTR();
		MyTR myTR4=new MyTR();
		myTR.setName("��Ʊ1�Ŵ���");
		myTR2.setName("��Ʊ2�Ŵ���");
		myTR3.setName("��Ʊ3�Ŵ���");
		myTR4.setName("��Ʊ4�Ŵ���");
		myTR.start();
		myTR2.start();
		myTR3.start();
		myTR4.start();*/
		
		MyRunnable myRunnable=new MyRunnable();
		Thread t1=new Thread(myRunnable);
		Thread t2=new Thread(myRunnable);
		Thread t3=new Thread(myRunnable);
		Thread t4=new Thread(myRunnable);
		
		t1.setName("һ�Ŵ���");
		t2.setName("���Ŵ���");
		t3.setName("���Ŵ���");
		t4.setName("�ĺŴ���");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
