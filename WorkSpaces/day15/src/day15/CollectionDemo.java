package day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionDemo {
	/*
	 * ��ϰCollection�����еķ���
	 * */
	public static void main(String[] args) {
		
		//��ӹ��� 
		//add  addall
		//��ΪCollection��һ���ӿ����Բ���ʵ����
		Collection cll=new ArrayList();
		cll.add("Hello");
		cll.add("World");
		System.out.println(cll.add("java"));
		System.out.println(cll);
		//ɾ������
		//clear��ȫ��ɾ��
//		cll.clear();
//		System.out.println(cll);
		//remove �Ӽ������Ƴ�һ��Ԫ��
//		cll.remove("Hello");
//		System.out.println(cll);
		//�жϹ���
		System.out.println(cll.contains("Hello"));
		//���ֻ�����ж�һ�Σ����������Hello������
		if(cll.contains("Hello"))
		{
			cll.add("ios");
		}
		System.out.println(cll);
		//isEmpty�ж��Ƿ�Ϊ��
		//�����Ĺ���
		//Iterator
		Iterator it=cll.iterator();
		while (it.hasNext()) {
			//Object object=it.next();
			//Ҳ����ͨ��ǿ��ת����ת����String���͵�
			String string=(String)it.next();
			System.out.println(string);
			//System.out.println(object);			
		}	
		System.out.println(cll.size());
		//toArray����ֵ��һ��Object���� ����ת����StringҪ����
		//ǿ��ת��
//		String[] strings=(String[])cll.toArray();
		
		//����All�Ĺ���
		//addAll�����򼯺������һ�����ϵ�Ԫ��
		Collection cl=new ArrayList();
		cl.add("Hello");
		cl.add("nihao");
		cl.add("better");
		//cll.addAll(cl);
		System.out.println(cll);
		System.out.println(cl);
		//��Ҫ����ɾ��cll�к�cl��ͬ�����е�Ԫ��
//		cll.removeAll(cl);
//		System.out.println(cll);
//		System.out.println(cl);
		//containsAll
		//�ж�cll�Ƿ�ȫ������cl�е�Ԫ�أ�ֻ��ȫ�������ŷ���true
//		System.out.println(cll.containsAll(cl));
//		System.out.println(cll);
//		System.out.println(cl);
		//cll�б�����ǽ����е�Ԫ��
		//cl�е�Ԫ���ǲ������仯��
		System.out.println(cll.retainAll(cl));
		System.out.println(cll);
		System.out.println(cl);
		
		//
	}
	
	
	 
	
}
