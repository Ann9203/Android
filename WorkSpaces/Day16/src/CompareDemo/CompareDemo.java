package CompareDemo;


public class CompareDemo {
	/*
	 * 1����������ÿ��Ԫ�ض���50��
	 * 2����������ÿ��Ԫ�ض���50С��
	 * 3��������������е�Ԫ�ر�50���е�Ԫ�ر�50С�أ�
	* 
	 * */
	public static void main(String[] args) {
		int[] in={1,2,3,4,5,6,9,8};
		PaiXu(in);
		compare(in);
		System.out.println("***************************");
		int[] in2={65,78,90,23,45,55,44};
		PaiXu(in2);
		compare(in2);
		System.out.println("***************************");
		int[] in3={56,57,58,59,79,66,78,79};
		PaiXu(in3);
		compare(in3);
		//print(in);
	}
	//��Ϊ������һ�����飬�д���С�����Ծ�������
	public static void PaiXu(int[] in)
	{
		//�ж������Ƿ�Ϊ��
		if(in==null || in.length==0)
		{
			System.out.println("������û��Ԫ�أ������Ԫ��");			
		}
		//��ѡ������
		for (int i = 0; i < in.length; i++) {
			for (int j = i+1; j < in.length; j++) {
				compare1(in, i, j);
			}
		}
		
	}
	//�Ƚϼ���������Ԫ�صĴ�С
	public static void compare1(int[] in,int a,int b)
	{
		if(in[a]>in[b])
		{
			in[a]=in[a]^in[b];
			in[b]=in[a]^in[b];
			in[a]=in[a]^in[b];
		}
	}
	//���¶���һ������Ƚϴ�С�ķ���
	public static void  compare(int[] in)
	{
		
/*		for (int i = 0; i < in.length; i++) {
			if(in[i]>50)
			{
				for (int j = in.length-1; j >=0; j--) {
					in[i]=in[j];
					
				}
			}else if(in[i]<=50) {
				return in;
			}else {
				
			}
		}
		
		return in;*/
		//����������У���Ĭ�����ݴ���������һ�����������
		/*
		 * if�ж���Ҫ���Ƿ�Ϊ���¼������
		 * 1.ǰ�������������
		 *    if:
		 *    	1.����жϵ���������������һ��������50����֤���������ݶ���50��
		 *      2.������������һ��Ԫ�ض���50С��֤���������ݶ���50С
		 *      3.�������50���ң��б�����ģ�Ҳ�б�50С�£�����ѡ�����򣬻�����ð������
		 *      	�ж�����Ԫ�ؼ�ȥ50�ľ���ֵ
		 * */
		if(in[0]>50)
		{
			print(in);
		}else if(in[in.length-1]<50) {
			for (int i = in.length-1; i >=0; i--) {
				System.out.println(in[i]);
			}
		
			}else {
				for (int i= 0; i < in.length; i++) {
					for (int j= i+1; j < in.length; j++) {
						if(Math.abs(in[i]-50)>Math.abs(in[j])-50)
						{
							in[i]=in[i]^in[j];
							in[j]=in[i]^in[j];
							in[i]=in[i]^in[j];
						}
					}
				}
				print(in);
			}
		
		
	}
	//��ӡ�����е�Ԫ��
	public static void print(int[] in)
	{
		for (int i = 0; i < in.length; i++) {
			System.out.println(in[i]);
		}
	}
}
