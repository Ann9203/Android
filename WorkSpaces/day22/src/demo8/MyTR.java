package demo8;

/*
 * ʵ����Ʊ�Ĺ���
 * 	����һ��Thread
 * 		���Ⱦ��Ƕ���һ�������̳�Thread��
 * 		Ʊ�������ǹ̶���200�ţ�����Ϊ����
 * 		��дrun����������Ʊ��ʱ��Ҫ����0��Ȼ�����ÿ�ζ�Ҫ˯��200���룬�����̳߳�ͻ
 * 		
 * */
public class MyTR extends Thread{
	private static int tiket=200;
	public void run()
	{
		while(true)
		{
			if(tiket>0)
			{
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				System.out.println(getName()+"----��Ʊ-----"+tiket);
				tiket--;
			}
		}
	}	
		

}
