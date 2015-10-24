package fuxi;

import java.util.Comparator;

public class OutImpl implements Comparator<Student> {
	
//	public OutImpl() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public int compare(Student stu1, Student stu2) {
		int num=stu2.getScore()-stu1.getScore();
		int num2=num==0?(stu1.getAge()-stu2.getAge()):num;
		int num3=num2==0?(stu1.getName().compareTo(stu2.getName())):num2;
		return num3;
	}

}
