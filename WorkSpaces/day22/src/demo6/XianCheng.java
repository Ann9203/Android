package demo6;

public class XianCheng extends Thread {

/*	
 * 获取线程的名称
 * 实现线程的恶两种方法：
 * 		一个是继承Tread类
 * 		另一个就是实现Runnable接口
 * 	需要对Thread中的run方法进行重写
 * 
 * */
	//只是让这个类继承Thread类然后重写run方法，在test类中进行测试
	public void run()
	{
		for (int i = 0; i < 100; i++) {
			System.out.println(getName()+"**********"+i);
		}
	}
	
	
}
