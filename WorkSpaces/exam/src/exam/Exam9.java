
package exam;

import java.util.TreeSet;

public class Exam9 {
	/*
	 * ��дһ�����򣬻�ȡ10��1��20���������Ҫ������������ظ���
	 * ˼·��
	 * 		A:��ȡ10��1��20֮��������
	 * 			������Random���nextln
	 * 		B:��ȡ������������ظ�����������ʹ��TreeSet���ϣ��ǲ��ظ���
	 * */
	
	public static void main(String[] args) {
		
		//����һ��TreeSet����
		TreeSet<Integer> ts=new TreeSet<Integer>();
		//��Ϊ�ǻ�ȡ10���������������set���ϵĳ���ҪС�ڵ���10
		while(ts.size()<10)
		{
			//����ȥ���������ӵ�set������ȥ
			ts.add((int)(Math.random()*20+1));
		}
		//��ӡ�����Ǹ���ֵ
		System.out.println(ts);
	}
	

}
