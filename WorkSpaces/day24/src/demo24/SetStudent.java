package demo24;


/*
 * �߳������ѧ��������
 * */
public class SetStudent extends Thread {
	
	private Student student;
	public SetStudent(Student student){
		this.student=student;
	}
	//��ѧ�����������Ӧ��ֵ
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		int x=0;
		while(true)
		{
			synchronized (student) {
				//���booleanΪture�Ļ�����֤������Դ�Ͳ��������
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
					student.name="ѩ��";
					student.age=24;
				}
				else {
					student.name="��ѩ";
					student.age=23;
				}
				student.falg=true;
				student.notify();
				x++;
			}
			
		}
	}
}
