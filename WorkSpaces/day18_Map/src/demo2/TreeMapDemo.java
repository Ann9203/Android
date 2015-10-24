package demo2;

import java.util.Set;
import java.util.TreeMap;

public class TreeMapDemo {

		public static void main(String[] args) {
			TreeMap<StudentDemo,Integer >treeMap=new TreeMap<StudentDemo, Integer>();
			treeMap.put(new StudentDemo("小红", 32), 1230);
			treeMap.put(new StudentDemo("小兰", 22), 345345);
			treeMap.put(new StudentDemo("小红", 32), 1230);
			treeMap.put(new StudentDemo("小红", 44), 23423);
			Set<StudentDemo>studentDemos=treeMap.keySet();
			for (StudentDemo studentDemo : studentDemos) {
				Integer valueInteger=treeMap.get(studentDemo);
				System.out.println(studentDemo.getName()+"****"+studentDemo.getAge()+"****"+valueInteger);
			}
			
		}
}
