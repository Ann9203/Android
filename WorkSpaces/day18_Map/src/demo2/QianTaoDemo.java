package demo2;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;


public class QianTaoDemo {
	/*
	 *   ��HashMap��key����Ԥ�Ȱ�;�ҵ��,��value����ArrayList����, 
	 *   ArrayList������������Student����.Student����������������. 
	 * 
	 * */
	public static void main(String[] args) {
		
		//���ȴ���һ�����Map����
		TreeMap<String , ArrayList<StudentDemo>>tMap=new TreeMap<>();
		//��ξ��Ǵ���һ��Ԥ�Ȱ��ArrayList����
		ArrayList<StudentDemo>yure=new ArrayList<>();
		yure.add(new  StudentDemo("Сѩ", 12345));
		yure.add(new  StudentDemo("С��", 12346));
		yure.add(new  StudentDemo("С��", 12348));
		yure.add(new  StudentDemo("С��", 12349));
		//��ξ��Ǵ���һ����ҵ���ArrayList����
		ArrayList<StudentDemo>jiuye=new ArrayList<>();
		jiuye.add(new  StudentDemo("С��", 12347));
		jiuye.add(new  StudentDemo("С��", 12342));
		jiuye.add(new  StudentDemo("С��", 12343));
		jiuye.add(new  StudentDemo("С��", 12341));
		
		//�������󼯺������Ԫ��
		tMap.put("JY", jiuye);
		tMap.put("YR", yure);
		//ѭ��������ļ���
		Set<String >key=tMap.keySet();
		for (String string : key) {
			//��һ��ѭ����ѭ������Ҫ���Ǹ���keyֵ�õ�Valueֵ
			ArrayList<StudentDemo> stu=tMap.get(string);
			System.out.println(string);
			//�ڶ���ѭ����ѭ��Valueֵ��Ҳ����ѭ��Arraylist����
			for (StudentDemo studentDemo : stu) {
				System.out.println("\t"+studentDemo.getName()+"\t"+studentDemo.getAge());
			}
			
		}
		
	}

}
