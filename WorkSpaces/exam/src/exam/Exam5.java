package exam;

import java.util.TreeSet;

public class Exam5 {
/*
 * ��Math.random����1��2��3��4��5��6��7��8��9��10
 * */
	public static void main(String[] args) {
		/*
		 * �����ͨ��Math.random����һ�����������
		 * ˼·��
		 * 		A:��ȻMath.random����10�������,�������ɵ��������
		 * 			Ҳ��֪���ǲ��ǻ��ظ������԰����������TreeSet��
		 * 		B:����TreeSet����
		 * */
		//����һ��TreeSet����
//		TreeSet<Integer>tSet=new TreeSet<Integer>();	
//		while(tSet.size()<10)
//		{
//			tSet.add((int)Math.random()*10+1);
//		}
//		System.out.println(tSet);
		TreeSet<Integer>te=new TreeSet<>();
		int leng=te.size();
		while(leng<10)
		{
			te.add((int)Math.random()*10+1);
			//System.out.println("Hello");
			int num=(int)Math.random()*10+1;
			System.out.println(num);
			leng++;
		}
		System.out.println(te);
		
	}
}
