package Demo6;

public class Test {
	//²âÊÔ£¬
	public static void main(String[] args) {
		Student student=new Student();
		SetStudent setStudent=new SetStudent(student);
		GetStudent getStudent=new GetStudent(student);
		setStudent.start();
		getStudent.start();
		
	}

}
