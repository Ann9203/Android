package demo24;


/*
 * 线程来添加学生的属性
 * */
public class SetStudent extends Thread {
	
	private Student student;
	public SetStudent(Student student){
		this.student=student;
	}
	//给学生属性添加相应的值
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		int x=0;
		while(true)
		{
			synchronized (student) {
				//如果boolean为ture的话，就证明有资源就不用添加呢
				if(student.falg)
				{
					try {
						student.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(x%2==0)
				{
					student.name="雪儿";
					student.age=24;
				}
				else {
					student.name="刘雪";
					student.age=23;
				}
				student.falg=true;
				student.notify();
				x++;
			}
			
		}
	}
}
