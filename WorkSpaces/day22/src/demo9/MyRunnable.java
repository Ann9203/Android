package demo9;
public class MyRunnable extends Thread {
	/*
	 * ʵ������
	 * */
	private static int ticket=200;
	Object object= new Object();
	public void run()
	{
		while(true)
			
			//���е����ݽ����ˣ�
				//��ʱ���Ҫ��������˭�ȵ�
				synchronized (object) {
					if(ticket>0)
					{
						try {
							//˯��
							Thread.sleep(200);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						//��ӡ��Ʊ
						System.out.println(Thread.currentThread().getName()+"---���۳�Ʊ"+ticket--);
						
					}
				
			}
				
				
			
	}

}
