package SetDemo;
import java.util.Comparator;
import java.util.TreeSet;
public class TreeSetDemo {
	//treeset的底层结构是二叉树
	//treeset再给元素进行排序的时候有两种
	//第一种：自然的排序
	//第二种：comparator
	public static void main(String[] args) {
		//TreeSet<StudentDemo>treeSet=new TreeSet<StudentDemo>();
		//TreeSet<StudentDemo>treeSet=new TreeSet<StudentDemo>(new MyTreeSet());
		TreeSet<StudentDemo>treeSet=new TreeSet<StudentDemo>( new Comparator<StudentDemo>() {

			@Override
			public int compare(StudentDemo stu1, StudentDemo stu2) {
				int num=stu1.getScore()-stu2.getScore();
				int num2=num==0?(stu1.getAge()-stu2.getAge()):num;
				int num3=num2==0?(stu1.getNameString().compareTo(stu2.getNameString())):num2;
				return num3;
			}
		});
		treeSet.add(new StudentDemo("雪", 15,34));
		treeSet.add(new StudentDemo("里", 15,34));
		treeSet.add(new StudentDemo("honglan",15,66));
		treeSet.add(new StudentDemo("honglan", 18,74));
		treeSet.add(new StudentDemo("lvlv", 15,74));
		
		for (StudentDemo student:treeSet) {
			System.out.println(student.getNameString()+"****"+student.getAge()+"*****"+student.getScore());
		}
	}

}
