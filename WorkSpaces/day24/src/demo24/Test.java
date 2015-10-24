package demo24;

public class Test {
	public static void main(String[] args) {
		Student student=new Student();
		SetStudent se=new SetStudent(student);
		GetStudent ge=new GetStudent(student);
		se.start();
		ge.start();
		
	}

}
