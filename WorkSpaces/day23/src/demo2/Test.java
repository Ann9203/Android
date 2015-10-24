package demo2;

public class Test {
	public static void main(String[] args) {
		MyRunnable mRunnable=new MyRunnable();
		Thread tr1=new Thread(mRunnable);
		Thread tr2=new Thread(mRunnable);
		tr1.setName("中国");
		tr2.setName("小日本");
		tr1.start();
		tr2.start();
		
	}
}
