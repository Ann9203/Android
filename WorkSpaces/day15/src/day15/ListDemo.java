package day15;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;



public class ListDemo {
	/*
	 * ��������ArrayList�����hello��world��java����Ԫ�أ�Ȼ��������ϣ�
	 * �жϼ������Ƿ���Ԫ��hello������еĻ������һ����Ԫ��ios��ȥ��
	 * Ȼ���ٱ�������

	 * */
	public static void main(String[] args) {
		
		List list=new ArrayList();
		list.add("Hello");
		list.add("World");
		list.add("Java");
		ListIterator iterator=list.listIterator();
		//Iterator iterator =list .iterator();
		while(iterator.hasNext())
		{
			Object object=iterator.next();
			if(object .equals("Hello"))
			{
				//���ʹ�ü��ϵĻ���Ӧ�ö�Ҫʹ�ü���
				//ʹ�ü���ȥ���Ԫ��
				iterator .add("iio");
			}
			
			
		}
////			if("Hello".equals(string))
////			{
////				list.add("ios");
////				//System.out.println("hahha");
////			}
//			
//		}
//		for (int i = 0; i < list.size(); i++) {
//			String string =(String)list .get(i);
//			if("Hello".equals(string))
//			{
//				list .add("ios");
//			}
//			
//		}
		
		System.out.println("lis:"+list);
	}
}
