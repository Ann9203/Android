package demo6;

public class XianCheng extends Thread {

/*	
 * ��ȡ�̵߳�����
 * ʵ���̵߳Ķ����ַ�����
 * 		һ���Ǽ̳�Tread��
 * 		��һ������ʵ��Runnable�ӿ�
 * 	��Ҫ��Thread�е�run����������д
 * 
 * */
	//ֻ���������̳�Thread��Ȼ����дrun��������test���н��в���
	public void run()
	{
		for (int i = 0; i < 100; i++) {
			System.out.println(getName()+"**********"+i);
		}
	}
	
	
}
