package day15;

public class MaxStrMinStrDemo {
	/*
	 * ����һ���ַ�����Ȼ���ڸ���һ��С���ַ���
	 * �ڴ��ַ����л�ȡС�ַ������ֵĴ����Ƕ���
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		
		String s="uuusshshshsuusdfuuusdfsdfsdfsuu";
		//System.out.println(s);
		int i=getCount(s, "uu");
		System.out.println(i);
		
	}
	/*
	 * �������ơ�����ȡ������
	 * ���裺
	 * 	�����������ַ����а������ٸ�uu
	 * 		���� ��indexOf�������ص�һ�γ��ֵ�λ��
	 * 		Ȼ��Ѱ�����һ�γ��ֵģ��ͽ�ȡ��indexOf����ֵ+Ҫ�ҵ��ַ����Ķ��
	 *      Ȼ�����Ѱ�ң�֪��indexOf����-1
	 *      Ҳ����û���ҵ�
	 *      
	 * */
	public static int getCount(String maxstr,String minstr)
	{
		//����һ�������ı���count
		int count=0;
		//System.out.println(count);
		//indexOf����maxstr�ַ����У���һ�γ��ֵ�minstr������ֵ
		int index=maxstr.indexOf(minstr);
		System.out.println(index);
		//�������ֵ������-1��˵������ͬ���ַ���
		while(index!=-1)
		{
			//�ҵ�һ���ͼ�һ
			count++;
			//System.out.println(count);
			//��ȡ�ַ���֮�󣬼�������index
			maxstr=maxstr.substring(index+minstr .length(),maxstr.length());
			//System.out.println(maxstr);
			index=maxstr.indexOf(minstr);
			//System.out.println(index);
		}
		//����count
		return count;
	}

}
