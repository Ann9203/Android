package demo24;



/*
 * ͨ���̻߳�ȡѧ��������
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
				//���booleanΪfalse��ʱ��֤��û����Դ
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
				//�����߳������Դ
				student.notify();
			}
		}
	}
}
