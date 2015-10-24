package Demo6;

public class SetStudent extends Thread {
	//写入学生的属性，如果是2的倍数的话
	//就要写入的是刘雪，如果是别的就写入雪儿
	private Student student;
	public SetStudent(Student student)
	{
		this.student=student;
	}
	public void run()
	{
		int x=0;
		while(true)
		{
			synchronized (student) {
				if(x%2==0)
				{
//					student.name="刘雪";
//					student.age=23;
					student.set("刘雪", 21);
				}
				else {
//					student.name="雪儿";
//					student.age=23;
					student.set("雪儿", 12);
				}
				x++;
			}
			
		}
	}
	
}
