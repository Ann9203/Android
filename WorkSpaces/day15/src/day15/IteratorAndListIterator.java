package day15;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.sun.javafx.scene.control.skin.ListViewSkin;

public class IteratorAndListIterator {
	
	/*
	 * �����������
	 * ���ַ�ʽ
	 * ��Ե���list����
	 * 1.iterator
	 * 2.listIterator
	 * 3.get����������ȡԪ�أ�Ȼ��forѭ��
	 * */
	public static void main(String[] args) {
		List list=new ArrayList();
		list.add("Hello");
		list.add("World");
		list.add("Java");
		list.add("best");
		//�����޸ĵĹ��ܷ��ص����޸�֮ǰ��Ԫ��
		//System.out.println(list.set(2, "Andrio"));
		//��itretor���б���
		Iterator iterator=list.iterator();
		while (iterator .hasNext())
		{
			String string=(String)iterator.next();
			System.out.println(string);
			
		}
		for (int i = 0; i < list.size(); i++) {
			String string=(String)list .get(i);
			System.out.println(string);
		}
		ListIterator listt=list.listIterator();
		while (listt.hasNext()) {
			String string=(String )listt .next();
			System.out.println(string);
			
		}
		
	}

}
