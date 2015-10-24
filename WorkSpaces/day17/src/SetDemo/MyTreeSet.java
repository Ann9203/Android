package SetDemo;

import java.util.Comparator;

public class MyTreeSet implements Comparator<StudentDemo> {

	
	public MyTreeSet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(StudentDemo stu1, StudentDemo stu2) {
		int num=stu1.getScore()-stu2.getScore();
		int num2=num==0?(stu1.getAge()-stu2.getAge()):num;
		int num3=num2==0?(stu1.getNameString().compareTo(stu2.getNameString())):num2;
		return num3;
	}

}
