package demo4;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class MyRunnable implements Runnable{

	/*
	 * Ϊ�˽��������Դ��������ָ�Ʊ  �����ظ���Ʊ
	 * �߳̾�������Ժ��ӳ���Ҫ����
	 * */
	private static int ticket=200;
	public Object object=new Object();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//ѭ��һֱ��true
		while(true)
		{
			//���Ǹ��̶߳�������ʱ�򣬾�Ҫ�ж��Ƿ�Ʊ������o
			synchronized (object) {
				if(ticket>0)
				{
					System.out.println(Thread.currentThread().getName()+"----���ڳ�Ʊ----"+ticket--);
				}
			}
		}
		
	}

}
