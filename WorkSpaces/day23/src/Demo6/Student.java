package Demo6;

public class Student {
	//��Ϊ����Ҫһ����ѩһ��ѩ��������ȥ��ӡ
	//�����Ƶ���PV������
	//�����ǵ�set�еķ���Ϊtrue��ʱ���֤������Դ��Ȼ��û����Դ֮�����ӣ�Ȼ��
	
	//Ȼ���������Դ֮��ͻ���get
	private String name;
	private int age;
	boolean flag=false;
	public synchronized void set(String name, int age) 
	{
		//���flagΪtrue֤���߳�������ԴȻ��ͽ��еȴ�
		if(flag)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.name=name;
		this.age=age;
		//�����������this��flag��ôflag����ԶΪtrue
		this.flag=true;
		notify();
	}
	public synchronized void get()
	{
		if(!flag)
		{
			//���û����Դ�Ļ��ͽ��еȴ�
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(this.name+"*******"+this.age);
		//��Դ���߾�IΪ�������Ծ͸ı�״̬Ϊfalse
		this.flag=false;
		//�ͷ�������Դ��Ҫ����notify
		notify();
	}
}
