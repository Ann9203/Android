package fuxi;

import java.util.HashSet;

public class HashSetDemo {
 /*
  * hashSet特点：
  *   元素唯一性，无序
  *   怎么存储对象类型的数据
  *   要重写equals 和HashCode方法
  * */
	public static void main(String[] args) {
			HashSet<StudentDemo> hSet=new HashSet<StudentDemo>();
			hSet.add(new StudentDemo("小李", 12, 99));
			hSet.add(new StudentDemo("小熊", 14, 56));
			hSet.add(new StudentDemo("小周", 15, 88));
			hSet.add(new StudentDemo("小王", 13, 77));
			hSet.add(new StudentDemo("小李", 12, 99));
			hSet.add(new StudentDemo("小同", 14, 55));
			hSet.add(new StudentDemo("小李", 16, 77));
			for (StudentDemo studentDemo : hSet) {
				System.out.println(studentDemo.toString());
			}
			
			HashSet<WorkerDemo>hashSet=new HashSet<WorkerDemo>();
			hashSet.add(new WorkerDemo("小李",13,"1234856"));
			hashSet.add(new WorkerDemo("小明",16,"12348"));
			hashSet.add(new WorkerDemo("小红",18,"23454"));
			hashSet.add(new WorkerDemo("小丽",19,"123234"));
			hashSet.add(new WorkerDemo("小李",13,"12348"));
			for (WorkerDemo workerDemo : hashSet) {
				System.out.println(workerDemo.toString());
			}
		
	}
}
