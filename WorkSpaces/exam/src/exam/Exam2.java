package exam;

public class Exam2 {
	/*
	 * ��1-100 �и�λ��ʮλ���� 7 ����
	 * ˼·��
	 * 		A:ʹ��forѭ����
	 * 		B:�ֽ�1-100�е���λ����
	 * 		C:�����ж��Ƿ�ÿ��������7���
	 * 		D:��ӡ������ȵ�����
	 * */
	public static void main(String[] args) {
		
		//�ֽ�shiλ��geλ
		
		int shi=0;
		int ge=0;
		for (int i = 1; i < 100; i++) {
			 shi=i/10;			
			 ge=i%10;
			 //��Ҫ��һ������7�Ͳ�������
			if(ge!=7 && shi!=7)
			{
				System.out.println(i);
			}
			
		}
	}
}
