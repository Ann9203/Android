package Demo6;

public class GetStudent extends Thread{
	
	private Student student;
	public GetStudent(Student student)
	{
		this.student=student;
	}

	//��ȡѧ���������࣬ͨ�����߳�����ȡѧ��������
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
