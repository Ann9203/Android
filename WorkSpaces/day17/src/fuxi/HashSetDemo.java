package fuxi;

import java.util.HashSet;

public class HashSetDemo {
 /*
  * hashSet�ص㣺
  *   Ԫ��Ψһ�ԣ�����
  *   ��ô�洢�������͵�����
  *   Ҫ��дequals ��HashCode����
  * */
	public static void main(String[] args) {
			HashSet<StudentDemo> hSet=new HashSet<StudentDemo>();
			hSet.add(new StudentDemo("С��", 12, 99));
			hSet.add(new StudentDemo("С��", 14, 56));
			hSet.add(new StudentDemo("С��", 15, 88));
			hSet.add(new StudentDemo("С��", 13, 77));
			hSet.add(new StudentDemo("С��", 12, 99));
			hSet.add(new StudentDemo("Сͬ", 14, 55));
			hSet.add(new StudentDemo("С��", 16, 77));
			for (StudentDemo studentDemo : hSet) {
				System.out.println(studentDemo.toString());
			}
			
			HashSet<WorkerDemo>hashSet=new HashSet<WorkerDemo>();
			hashSet.add(new WorkerDemo("С��",13,"1234856"));
			hashSet.add(new WorkerDemo("С��",16,"12348"));
			hashSet.add(new WorkerDemo("С��",18,"23454"));
			hashSet.add(new WorkerDemo("С��",19,"123234"));
			hashSet.add(new WorkerDemo("С��",13,"12348"));
			for (WorkerDemo workerDemo : hashSet) {
				System.out.println(workerDemo.toString());
			}
		
	}
}
