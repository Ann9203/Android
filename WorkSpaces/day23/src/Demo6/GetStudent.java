package Demo6;

public class GetStudent extends Thread{
	
	private Student student;
	public GetStudent(Student student)
	{
		this.student=student;
	}

	//获取学生的属性类，通过多线程来获取学生的属性
	public void run()
	{
		while(true)
		{
			synchronized (student) {
				//System.out.println(student.name +"***********"+student.age);
				student.get();
			}
			
		}
	}
}
