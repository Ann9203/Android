package demo6;

public class Test {
	public static void main(String[] args) {
		//һ���߳�����������е���
		//һ���߳������100�����֣�����������ʹ�����100������
		XianCheng xianCheng=new XianCheng();
		XianCheng xianCheng2=new XianCheng();
		xianCheng.setName("һ��");
		xianCheng2.setName("����");
		xianCheng.start();
		xianCheng2.start();
	}

}
