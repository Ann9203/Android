package HomeWork;

import java.util.ArrayList;
import java.util.Iterator;

public class Array_LinkDemo {
	/*
	 * �ֱ���ArrayList,LinkedList�洢�ַ������Զ�����󲢱�����
	 * ���÷��͸Ľ������д��
	 * */
	public static void main(String[] args) {
		//��ʹ�÷���֮ǰ
		
		ArrayList arrayList=new ArrayList();
		arrayList.add("hello");
		arrayList.add("world");
		arrayList.add("java");
		arrayList.add("andriod");
		//��һ�ֻ�ȡ�ķ�ʽ������
		Iterator iterator=arrayList.iterator();
		while (iterator.hasNext()) {
			String string=(String)iterator.next();
			System.out.println(string );
		}
		System.out.println("********************************************");
		//�ڶ��ֻ�ȡ�ķ�������ͨforѭ��
		for (int i = 0; i < arrayList.size(); i++) {
			String string=(String)arrayList.get(i);
			System.out.println(string);
			
		}
		System.out.println("********************************************");
		//�����ֻ�ȡ��ʽ����ǿforѭ��
		//��Ϊû��ʹ�÷��ͣ������ڼ���Ԫ�ص�������ֻ����Object�����ܽ���ǿ��ת��
		for(Object object :arrayList)
		{
			System.out.println(object);
		}
		System.out.println("********************************************");
		//linkedListͬ����һ���ģ�ֻ����LinkedList���ڴ�ȡ�����˼����µķ�ʽ
		//��ȡ������δ�ȡ�ġ�
		System.out.println("********************************************");
		//һ��Ҫʹ�÷��ͽ��д�ȡ
		ArrayList<String> arrayList2=new ArrayList<String>();
		arrayList2.add("How");
		arrayList2.add("are");
		arrayList2.add("you");
		Iterator<String>iterator2=arrayList2.iterator();
		while (iterator2.hasNext()) {
			//��Ϊʹ���˷��ͣ�ָ�������;���String���͵��ˣ����ԾͲ���ǿ��ת����
			String string=iterator2.next();
			System.out.println(string);
			
		}
		
	}

}
