package ArrayListDemo;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.org.apache.bcel.internal.generic.NEW;

import LinkedListDemo.StatcDemo;

public class ArrayListDemo2 {
	//�жϴ���Ķ����Ƿ�����ͬ��
	public static void main(String[] args) {
	ArrayList arrayList=new ArrayList();
	arrayList.add(new StudentDemo("С��",12));
	arrayList.add(new StudentDemo("С��",13));
	arrayList.add(new StudentDemo("С��",12));
	arrayList.add(new StudentDemo("С��",12));
	arrayList.add(new StudentDemo("С��",12));
	arrayList.add(new StudentDemo("С��",12));
	ArrayList arrayList2=compare(arrayList);
	Iterator iterator=arrayList2.iterator();
	while(iterator.hasNext())
	{
		StudentDemo studentDemo=(StudentDemo)iterator.next();
		System.out.println(studentDemo.getAge()+"************"+studentDemo.getName());
	}
	}
	//����һ�����´���һ������
		public static ArrayList compare(ArrayList arl)
		{
			ArrayList newArrayList=new ArrayList<>();
			Iterator iterator=arl.iterator();
			while(iterator.hasNext())
			{
				Object object=iterator .next();
				if(!newArrayList.contains(object))
				{
					newArrayList.add(object);
				}
			}
			return newArrayList;
			
		}

}
