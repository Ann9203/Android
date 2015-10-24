package demo1;

public class SetStudent extends Thread  {
	private Student student;
	public SetStudent(Student student){
		
		this.student=student;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		int x=0;
		while(true)
		{
			synchronized (student) {
				if(x%2==0)
				{
					student.set("小小", 22);
				}else {
					student.set("大大", 100);
				}
				x++;
			}
			
		}
	}
}
