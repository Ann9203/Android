package exam;
import java.util.ArrayList;
import java.util.Collections;
public class Exam7 {
	/*
	 * //����λ����������ֻ�ܱ� �����1����,�������
	 * ˼·��
	 * 		���ȵľ���forѭ��
	 * 		B:�������е����ֶ�����������һ��boolean����
	 * 		C:�������ǿ��Ա�1�������������������������鱻2�Լ��������-1�������������Ļ�
	 * 			�Ͳ�������
	 * 			�ж���󣬾ͽ�flag=false
	 * */
	public static void main(String[] args) {
//		boolean falge;
//		//����������Collections�Ĺ����࣬����ʹ������
//		ArrayList<Integer>arrayList=new ArrayList<Integer>();
//		for (int a=100;a<1000;a++) {
//			falge=true;
//			for (int b = 2; b < a; b++) {
//				if(a%b==0)
//				{
//					falge=false;
//					//���Ϊfalse֤����Ϊ������Ȼ��ֱ������ѭ����������һ�����ֵ��ж�
//					break;
//					
//				}
//				
//			}
//			if(falge)
//			{
//				arrayList.add(a);
//			}
//			
//		}
//		java.util.Collections.reverse(arrayList);
//		System.out.println(arrayList);
		//��Ϊ�ǵ����������Զ���һ��Arraylist���ϣ�Ȼ��ʹ��Collections������
		ArrayList< Integer>arrayList=new ArrayList<Integer>();
		//����flag���ж��Ƿ�������
		boolean flag;
		//����λ���֣�������һ������
		for(int a=100;a<1000;a++)
		{
			flag=true;
			for(int b=2;b<a;b++)
			{
				if(a%b==0)
				{
					flag=false;
					//����������Ҫ��������
					break;
				}
			}
			//ѭ���ж����˺󣬷���������aֵ��ӡ����
			if(flag)
			{
				arrayList.add(a);
			}
		}
		//�ڼ����������Ԫ�غ��Ҫʹ��Collections����Ԫ�ط�ת
		Collections.reverse(arrayList);
		System.out.println(arrayList);
	}

}
