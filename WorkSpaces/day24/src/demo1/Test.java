package demo1;

public class Test {
	public static void main(String[] args) {
		Student student=new Student();
		SetStudent setStudent=new SetStudent(student);
		getStudent getS=new getStudent(student);
		setStudent.start();
		getS.start();
	}

}
