package exam;

import java.util.Arrays;
import java.util.Scanner;

public class Exam4 {
/*
 * ����¼��5�������洢��һ�������У�ȡ���ֵ����Сֵ
 * */
	public static void main(String[] args) {
		//����һ������
//		int[] arr=new int[5];
//		//��ΪҪ�������������forѭ����ѭ���������
//		//��ÿһ�������ֵ��������
//		for (int i = 0; i <5; i++) {
//			Scanner scanner=new Scanner(System.in);			
//			System.out.println("�������"+(i+1)+"�����֣�");
//				
//			arr[i]=scanner.nextInt();
			
//			System.out.println("������ڶ������֣�");
//				int two=scanner.nextInt();
//			System.out.println("��������������֣�");
//				int three=scanner.nextInt();
//			System.out.println("��������ĸ����֣�");23
//				int four=scanner.nextInt();
//			System.out.println("�������������֣�");
//				int five=scanner.nextInt();
				
		//}
		//�ȶ��������
		int[] arr=new int[5];
		//ѭ�������������
		for(int x=0;x<5;x++)
		{
			System.out.println("�������"+x+"������");
			Scanner scanner=new Scanner(System.in);
			//����������ִ��ݸ�����
			arr[x]=scanner.nextInt();
			
		}
		
		getMax(arr);
	}
//	public static void getMax(int[] arr)
//	{
//		//ʹ�ù�����������������
//		Arrays.sort(arr);
//		System.out.println("��С����ֵ"+arr[0]);
//		System.out.println("������ֵ"+arr[arr.length-1]);
//	}

	private static void getMax(int[] arr) {
		// TODO Auto-generated method stub
		//���ù�������и������������
		Arrays.sort(arr);
		System.out.println("�������֣�"+arr[arr.length-1]);
		System.out.println("��С�����֣�"+arr[0]);
	}
}
