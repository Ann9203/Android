package Collection;

import java.util.ArrayList;
import java.util.Collection;

public class ForeachDemo {
	public static void main(String[] args) {
		
		//JDK1.5֮������ص��ǣ�foreach
		//foreach���ص㣺 for(Ԫ���б������� ��ʱ��������������)
		//foreachѭ����һ�ָ��Ӽ���ѭ��
		Collection collection =new ArrayList();
		collection .add("Hello");
		collection .add("Every");
		collection .add("body");
		collection .add("How");
		collection .add("are");
		collection .add("you");
		//foreachѭ����������ת��
		
		for (Object obj:collection)
		{
			System.out.println(obj);
		}
		for(Object obj:collection )
		{
			System.out.println(obj);
		}
		for(Object object :collection )
		{
			System.out.println(object );
		}
		for (Object object :collection ) {
			System.out.println(object);
		}
	}

}
