package day20lianxi;

public class JieChengDemo {
	/*
	 * ���5�Ľ׳�
	 * ͨ���ݹ��������
	 * ���ȣ�
	 * 		�ҵ����� �����ھ��Ǵ����������1��ʱ��
	 * 		�ҵ����ɣ���Ϊ1��ʱ�����5*4*3*2*1
	 * 
	 * */
		public static void main(String[] args) {
			int num=getCount(5);
			System.out.println(num);
			
		}
		public static int getCount(int num)
		{
			//ͨ���ݹ����5�Ľ׳�
			if(num==1)
			{
				return 1;
			}else
			{
				//�����Ϊ1�Ļ�
				//������һ�����ֵõ��Ľ��������ε���ֵ
				return num*getCount(num-1);
			}
			
		}
	

}
