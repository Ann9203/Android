package demo5;

public class Test {
	public static void main(String[] args) {
		DiedLock diedLock=new DiedLock(true);
		DiedLock diedLock2=new DiedLock(false);
		diedLock.start();
		diedLock2.start();
	}

}
