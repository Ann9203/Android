package Collection;

import java.util.ArrayList;
import java.util.Iterator;
public class ArrayListDemo {
	 /**
	  * ����ArrayList��С����
	  * ��ArrayList�д��벻�ظ��Ķ���Ԫ��
	  * ���裺
	  * 	A:����дһ������Ԫ�ص�
	  *       �������ͬ��д���µ�Ԫ�ؼ�����
	  *     B����һ������
	  * 
	  * **/
	public static void main(String[] args) {
		ArrayList aList=new ArrayList();
		aList.add(new StudentDemo("С��",12));
		aList.add(new StudentDemo("С��", 13));
		aList.add(new StudentDemo("С��", 12));
		ArrayList aList2=compare(aList);
		Iterator iterator=aList2.iterator();
		while(iterator.hasNext())
		{
			StudentDemo studentDemo=(StudentDemo)iterator.next();
			System.out.println(studentDemo.getName()+"*********"+studentDemo.getAge());
			
		}
		
	}
	//����һ���µ�������װ�²��ظ��Ķ���
	public static ArrayList compare(ArrayList al)
	{
		ArrayList  newArrayList=new ArrayList();
		Iterator iterator=al.iterator();
		while(iterator.hasNext())
		{
			Object object=iterator.next();
			if(!newArrayList .contains(object))
			{
				newArrayList .add(object);
			}
		}
		return newArrayList;
	}
}
