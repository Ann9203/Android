package exam;

public class Exam3 {
	/*
	 * 21���žų˷���
		22��ð������
		23������ʽ
	 * */
	public static void main(String[] args) {
//		JiuJiui();
//		int[] arr={56,77,33,88,34,12};
//		Bubble(arr);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i]);
//		}
		System.out.println(SingleDemo.getInstance());
		System.out.println(SingleDemo.getInstance());
		
		
	}
//	public static void JiuJiui()
//	{
//		for(int x=1;x<=9;x++)
//		{
//			for(int y=1;y<=x;y++)
//			{
//				System.out.print(y+"*"+x+"="+x*y+"\t\t");
//			}
//			System.out.println();
//			
//		}
//	}

	private static void JiuJiui() {
		// TODO Auto-generated method stub
		for(int x=1;x<=9;x++)
		{
			//��ѭ����yҪС�ڵ���x
			for(int y=1;y<=x;y++)
			{
				//��ӡͬһ������ݣ��ǲ���Ҫ���л��е�
				System.out.print(x+"*"+y+"="+x*y+"\t");
			}
			//ѭ����һ���Ҫ���л��У���ӡ��һ��
			System.out.println();
		}
		
	}
	public static void Bubble(int[] arr)
	{
		/*
		 * ʵ��ð������
		 * 		˼·��
		 * 			ð��������Ҫ�����������жԱȣ�ÿѭ��һ�ξͻ�ѡ��������
		 * */
		
		//ѭ������
		//��ѭ������ѭ���Ĵ���
		for (int i = 0; i < arr.length-1; i++) {
			//ÿ��ѭ�������Ĵ���������������䣬����ÿ�ζ�Ҫ��ѭ��i��Ԫ��
			for(int j=0;j<arr.length-1-i;j++)
			{
				//���ñȽϷ���
				compare(arr, j, j+1);
			}
		}
	}
	public  static void compare(int[] arr,int a,int b)
	{
		//����Ҫ��ȷ���ݲ�Ϊ��
		if (arr!=null)
		{
			//�����ж�
			if(arr[a]>arr[b])
			{
				//ͨ��λ�������н���Ԫ��
				arr[a]=arr[a]^arr[b];
				arr[b]=arr[a]^arr[b];
				arr[a]=arr[a]^arr[b];
			}
		}
	}
	
	
}
