package fapai;

import java.util.ArrayList;
import java.util.Collections;

public class FaPaiDemo {
	/*
	 * ����������
	 * 	    A:����  д�������飬һ���ǻ�ɫ��һ��������
	 * 		B:ϴ��  ϴ�ƾ��ǽ��Ƶ�˳�������
	 * 		C:����  ���弸���˵ļ��ϣ�Ȼ��ÿ���˷���
	 * 		D:����  ����ʣ�µ�������
	 * 
	 * */
	
	public static void main(String[] args) {
		
		//�ѻ�ɫ�����ַֿ����ֱ�ŵ�����������
		//Ȼ����ͨ�����ߵĽ�ϣ����뵽������ȥ
		
		String [] huase={"����","����","÷��","��Ƭ"};
		String [] shuzi={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		ArrayList<String > pai=new ArrayList<String>();
		pai.add("����");
		pai.add("С��");
		//����forѭ����������������е�����
		//����
		for (int i = 0; i < huase.length; i++) {
			for (int j = 0; j < shuzi.length; j++) {
				pai.add(huase[i].concat(shuzi[j]));
			}
		}
		//ϴ��
		//����ͨ��shuff�������漴�û�
		//shuff������Collections�������еķ���
		Collections.shuffle(pai);
		
		/*
		 * ����
		 * 	����Ҫ���������������ֱ���������ˣ�
		 * Ȼ�������������漴����
		 * */
		//���������涷��������
		ArrayList<String >zhoujielun=new ArrayList<String>();
		ArrayList<String>zhourunfa=new ArrayList<String>();
		ArrayList<String>zhoutian=new ArrayList<String>();
		//�������˷���
		for (int x=0;x<pai.size()-3;x++) {
			if(x%3==0)
			{
				zhoujielun.add(pai.get(x));
				
			}
			else if(x%3==1)
			{
				zhourunfa.add(pai.get(x));
				
			}
			else if(x%3==2)
			{
				zhoutian.add(pai.get(x));
				
			}
		}
		System.out.println("�ܶ����ƣ�"+zhoujielun);
		System.out.println("������ƣ�"+zhourunfa);
		System.out.println("ũ����ƣ�"+zhoutian);
		//���ƻ������� ��Ҳдһ�����ϰ�
		//���ֻ�Ƿ��ƣ�����ȴû�д����Ƶ�˳��������ֱ�Ӻ������Ǵ���
		ArrayList< String > dipai=new ArrayList<String>();
		for(int x=pai.size()-3;x<pai.size();x++)
		{
			dipai.add(pai.get(x));
			
		}
		System.out.print("�����ǣ�"+dipai);
		
	}

}
