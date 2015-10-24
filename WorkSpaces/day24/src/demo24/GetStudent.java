package demo24;



/*
 * 通过线程获取学生的属性
 * */
public class GetStudent extends Thread{
	private Student student;
	public GetStudent(Student student)
	{
		this.student=student;
	}
	
	
	public void run()
	{
		while(true)
		{
			synchronized (student) {
				//如果boolean为false的时候证明没有资源
				if(!student.falg)
				{
					try {
						student.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(student.name+"*******"+student.age);
				student.falg=false;
				//唤醒线程添加资源
				student.notify();
			}
		}
	}
}
