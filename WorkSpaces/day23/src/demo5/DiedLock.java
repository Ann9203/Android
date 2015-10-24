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
		//��Ҫ��flag��ֵ����Ϊ��ֵ�˾Ͳ�����true����false��������������ֵ
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
