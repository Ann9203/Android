package ArrayListDemo;

import java.util.ArrayList;
import java.util.Iterator;

public class ArraylistDemo {
	/*
	 * 
	 * һ���ַ�������ArrayList�к�������Ԫ�أ�
	 * hello, world, java, hello, .net, java, php, ios, java, android��world��
	 * Ҫ���д���򣬻��һ��û���ظ�Ԫ�ص��¼��ϡ�

	 * */
	//���裺д��������
	//дһ���ж��Ƿ�����ظ�Ԫ�صļ��ϣ�Ȼ��ɲ��ظ���Ԫ��д����һ���µļ����У�Ȼ�󷵻��µļ���
	
	public static void main(String[] args) {
		ArrayList arrayList=new ArrayList();
		
		arrayList.add("hello");
		arrayList.add("world");
		arrayList.add("java");
		arrayList.add("hello");
		arrayList.add(".net");
		arrayList.add("java");
		arrayList.add("php");
		arrayList.add("ios");
		arrayList.add("java");
		arrayList.add("android");
		arrayList.add("world");
		
//	ArrayList arrayList2=compare(arrayList);
//		Iterator iterator=arrayList2.iterator();
//		while (iterator.hasNext()) {
//			String string=(String)iterator.next();
//			System.out.println(string);
			
			//��������ѡ������
			int length=arrayList.size();
			for(int x=0;x<length;x++)
			{
				for(int y=x+1;y<length ;y++)
				{
					if(arrayList.get(x).equals(arrayList.get(y)))
					{
						arrayList.remove(y);
						length--;
					}
				}
			}
			Iterator iterator=arrayList.iterator();
			while (iterator.hasNext()) {
				String string=(String)iterator.next();
				System.out.println(string);
			}
			
		}
	
	//����һ�����´���һ������
	public static ArrayList compare(ArrayList arl)
	{
		ArrayList newArrayList=new ArrayList();
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
