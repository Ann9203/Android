package demo4;

public class Test {
	public static void main(String[] args) {
		MyRunnable mRunnable=new  MyRunnable();
		Thread tr1=new Thread(mRunnable);
		Thread tr2=new Thread(mRunnable);
		Thread tr3=new Thread(mRunnable);
		tr1.setName("����1");
		tr2.setName("����2");
		tr3.setName("����3");
		tr1.start();
		tr2.start();
		tr3.start();
	}

}
