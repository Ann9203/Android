package Demo6;

public class SetStudent extends Thread {
	//д��ѧ�������ԣ������2�ı����Ļ�
	//��Ҫд�������ѩ������Ǳ�ľ�д��ѩ��
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
//					student.name="��ѩ";
//					student.age=23;
					student.set("��ѩ", 21);
				}
				else {
//					student.name="ѩ��";
//					student.age=23;
					student.set("ѩ��", 12);
				}
				x++;
			}
			
		}
	}
	
}
