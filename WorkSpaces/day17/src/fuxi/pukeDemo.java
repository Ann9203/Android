package fuxi;

import java.util.ArrayList;
import java.util.Collections;

public class pukeDemo {
	/*
	 * ����������2�飩
	 * 
	 * */

	public static void main(String[] args) {
		String [] huase={"����","÷��","����","��Ƭ"};
		String [] shuzhi={"A","2","3","4","5","6","7","8","9","10","J","Q","K"}; 
		//����һ�����ϣ���װ��
		ArrayList<String> pai=new ArrayList<String>();
		//��������Ӵ�����С��
		pai.add("����");
		pai.add("С��");
		//���ƣ���װ��52���ƣ�Ȼ����ӵ�������ȥ
		for(int i=0;i<huase.length;i++)
		{
			for(int j=0;j<shuzhi.length;j++)
			{
				pai.add(huase[i].concat(shuzhi[j]));
			}
		}
		//ϴ�ƣ����Ƶ�˳�������
		Collections.shuffle(pai);
		/*
		 * ���ƣ�
		 * 		A:����Ҫ���Ǹ�����װ�����ƣ�Ҳ���Ƕ�������������
		 * 	    B:��ξ���ÿ������ȥצ�ƣ���%3���㷨
		 * 		C:ÿ��צ�ƶ�����������µ����ŵ���
		 */
		ArrayList<String >zhoujielun=new ArrayList<String>();
		ArrayList<String>zhourunfa=new ArrayList<String>();
		ArrayList<String>caicai=new ArrayList<String >();
		//����
		//��Ϊ���ѭ���Ļ�ÿ�ζ�Ҫ����size�����������أ��ͽ�size�������ص�ֵ����һ������
		//�����Ļ���������߳��������Ч��
		int length=pai.size();
		for (int i = 0; i < length-3; i++) {
			if(i%3==0)
			{
				zhoujielun.add(pai.get(i));
			}
			else if(i%3==1){
				zhourunfa.add(pai.get(i));
			}
			else if(i%3==2) {
				caicai.add(pai.get(i));
			}
		}
		System.out.println("�ܶ����ƣ�"+zhoujielun);
		System.out.println("������ƣ�"+zhourunfa);
		System.out.println("С�˵��ƣ�"+caicai);
		
		//����
		//��Ϊ��ϴ����֮���Ƶ�˳���һֱû�б����������
		//���ƿ϶������������
		//����һ�����ϣ�װ�µ���
		ArrayList<String>dipai=new ArrayList<String>();
		for (int i = length-3; i <length; i++) {
			dipai.add(pai.get(i));
		}
		System.out.println("������  ��"+dipai);
		
	}
}
