package HomeWork;

import java.util.ArrayList;
import java.util.Iterator;

public class Array_LinkDemo1 {
//��Ҫ���Ǵ洢����
	public static void main(String[] args) {
		//ʹ�÷��ͽ��д洢
		ArrayList<UserDemo> userDemos=new ArrayList<UserDemo>();
		userDemos.add(new UserDemo("С��",12));
		userDemos.add(new UserDemo("С��",13));
		userDemos.add(new UserDemo("С��",12));
		userDemos.add(new UserDemo("С��",14));
		userDemos.add(new UserDemo("С��",12));
		Iterator<UserDemo> iterator=userDemos.iterator();
		while (iterator.hasNext()) {
			UserDemo userDemo=iterator.next();
			System.out.println(userDemo.getName()+"***************"+userDemo.getAge());
			
		}
		
		System.out.println("********************************************");
		//��ʹ�÷��ͽ����ж�
		ArrayList arrayList=new ArrayList<>();
		arrayList.add(new UserDemo("С��",12));
		arrayList.add(new UserDemo("С��",13));
		arrayList.add(new UserDemo("С��",12));
		arrayList.add(new UserDemo("С��",14));
		arrayList.add(new UserDemo("С��",12));
		Iterator iterator2=arrayList.iterator();
		while (iterator2.hasNext()) {
			//����ǿת
			UserDemo userDemo=(UserDemo)iterator2.next();
			System.out.println(userDemo.getName()+"****************"+userDemo.getAge());
			
		}
		
	}
}
