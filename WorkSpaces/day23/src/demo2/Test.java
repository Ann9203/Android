package demo2;

public class Test {
	public static void main(String[] args) {
		MyRunnable mRunnable=new MyRunnable();
		Thread tr1=new Thread(mRunnable);
		Thread tr2=new Thread(mRunnable);
		tr1.setName("�й�");
		tr2.setName("С�ձ�");
		tr1.start();
		tr2.start();
		
	}
}
