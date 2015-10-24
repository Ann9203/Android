package HomeWork;

import java.util.ArrayList;
import java.util.Iterator;

public class Array_LinkDemo1 {
//主要就是存储对象
	public static void main(String[] args) {
		//使用泛型进行存储
		ArrayList<UserDemo> userDemos=new ArrayList<UserDemo>();
		userDemos.add(new UserDemo("小明",12));
		userDemos.add(new UserDemo("小红",13));
		userDemos.add(new UserDemo("小朵",12));
		userDemos.add(new UserDemo("小明",14));
		userDemos.add(new UserDemo("小明",12));
		Iterator<UserDemo> iterator=userDemos.iterator();
		while (iterator.hasNext()) {
			UserDemo userDemo=iterator.next();
			System.out.println(userDemo.getName()+"***************"+userDemo.getAge());
			
		}
		
		System.out.println("********************************************");
		//不使用泛型进行判断
		ArrayList arrayList=new ArrayList<>();
		arrayList.add(new UserDemo("小明",12));
		arrayList.add(new UserDemo("小红",13));
		arrayList.add(new UserDemo("小朵",12));
		arrayList.add(new UserDemo("小明",14));
		arrayList.add(new UserDemo("小明",12));
		Iterator iterator2=arrayList.iterator();
		while (iterator2.hasNext()) {
			//进行强转
			UserDemo userDemo=(UserDemo)iterator2.next();
			System.out.println(userDemo.getName()+"****************"+userDemo.getAge());
			
		}
		
	}
}
