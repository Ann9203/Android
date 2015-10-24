package demo5;

public class DiedLock extends Thread { 
	private boolean flag;
	public DiedLock(boolean flag)
	{
		this.flag=flag;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		//不要给flag赋值，因为赋值了就不管是true还是false都会是他赋给的值
		if (flag) {
			
			synchronized (MyLock.objA) {
				System.out.println("true objA");
				synchronized (MyLock.objB) {
					System.out.println("true objB");
				}
			}
		}else {
			synchronized (MyLock.objB) {
				System.out.println("false objB");
				synchronized (MyLock.objA) {
					System.out.println("false objA");
				}
				
			}
		}
	}

}
