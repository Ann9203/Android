package day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class HomeWork {
	public static void main(String[] args) {
		//Collection�����д洢�Զ������,��������
		Collection coll=new ArrayList();
		//���Ԫ��
		coll.add(new Student("С��", 12));
		coll.add(new Student("С��",13));
		coll .add(new Student("С��", 14));
		//���������
		Iterator iterator =coll .iterator();
		//������������б��������е�Ԫ��
		while(iterator.hasNext())
		{
			//ǿ��ת��
			Student student=(Student)iterator .next();
			System.out.println(student .getName()+"*************************"+student .getAge());
			
		}
		//����list���ϣ���list�����д洢�Զ������Ȼ����б���
		List list=new ArrayList();
		list.add("How");
		list.add("are");
		list.add("you");
		list.add("?");
		//����iterator����������
		Iterator iterator2=list .iterator();
		while (iterator2.hasNext()) {
			//��������
			//ʹ�õ��������б���
			
			String string =(String)iterator2.next();
			System.out.print(string+" ");
			
			
		}
		System.out.println("\n"+"*******************************");
		//������ʽ��
		//��Ϊlist���϶��صľ���get����������ȡ�����е�Ԫ��
		//Ҳ����˵list���Ͽ��Խ���forѭ������
		for (int i = 0; i < list .size(); i++) {
			//����ת��
			String string=(String )list .get(i);
			System.out.print(string +" ");
			
		}
		System.out.println("\n"+"*******************************");
		//�����ֱ�������ͨ��listIteraotr��������������Խ��iteraotor�����Ĳ����޸��쳣
		ListIterator listIterator =list.listIterator();
		while (listIterator.hasNext()) {
			String string=(String)listIterator.next();
			System.out.print(string+" ");
			
		}
		
		
	}

}
