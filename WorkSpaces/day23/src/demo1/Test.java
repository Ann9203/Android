package demo1;

public class Test {
	public static void main(String[] args) {
		MyTread myTread=new MyTread();
		MyTread myTread2=new MyTread();
		MyTread myTread3=new MyTread();
		myTread.setName("�ܲ�");
		myTread2.setName("����");
		myTread3.setName("�����");
		myTread.start();
		myTread2.start();
		myTread3.start();
		
	}

}
