package demo1;

public class getStudent extends Thread {
	private Student student;
	public getStudent(Student student)
	{
		this.student=student ;
	}
	public void run()
	{
		while(true)
		{
			synchronized (student) {
				student.get();
			}
			
		}
	}
}
