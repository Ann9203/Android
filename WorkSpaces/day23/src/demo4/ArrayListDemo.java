`package demo4;

import java.util.ArrayList;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

public class ArrayListDemo {
/*
 * ��Ŀ��
 * 	��д��������5��1��10֮������������
 * 	����һ��List���ϣ���д������List���Ͻ������� * ���Զ��������㷨������Collections.sort������TreeSet����
 * 	Ȼ��������������
 * */
	public static void main(String[] args) {
		ArrayList<Integer> arrayList=new ArrayList<Integer>();
			while(arrayList.size()<5)
			{
				arrayList.add((int)(Math.random()*10+1));
			}
			//System.out.println(arrayList);
			int leng=arrayList.size();
			//ѡ���������������г���С�����֣�һֱ�����������Ϊ1��Ԫ��
			for(int x=0;x<leng;x++)
			{
				for(int y=x;y<leng;y++)
				{
					if(arrayList.get(x)>arrayList.get(y))
					{
						int temp=arrayList.get(x);
						arrayList.set(x, arrayList.get(y));
						arrayList.set(y, temp);
					}
				}
			}
			System.out.println(arrayList);
//			for (Integer integer : arrayList) {
//				System.out.printl(integer);
//			}
		
//		int [] array=new int[arrayList.size()];
//		int leng=arrayList.size();
//		for (int i = 0; i < leng; i++) {
//			array[i]=arrayList.get(i);
//		}
//		Bubble(array);
		
		
	}
	
}
