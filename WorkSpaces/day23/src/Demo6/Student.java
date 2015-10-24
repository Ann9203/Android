package Demo6;

public class Student {
	//因为我想要一个刘雪一个雪儿的这样去打印
	//这就设计到了PV操作，
	//可以是当set中的方法为true的时候就证命有资源，然后没有资源之后就添加，然后
	
	//然后添加完资源之后就唤醒get
	private String name;
	private int age;
	boolean flag=false;
	public synchronized void set(String name, int age) 
	{
		//如果flag为true证明线程中有资源然后就进行等待
		if(flag)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.name=name;
		this.age=age;
		//如果不声明是this的flag那么flag就永远为true
		this.flag=true;
		notify();
	}
	public synchronized void get()
	{
		if(!flag)
		{
			//如果没有资源的话就进行等待
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(this.name+"*******"+this.age);
		//资源拿走就I为空了所以就改变状态为false
		this.flag=false;
		//释放完了资源就要进行notify
		notify();
	}
}
