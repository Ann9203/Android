package demo1;

public class Test {
	public static void main(String[] args) {
		MyTread myTread=new MyTread();
		MyTread myTread2=new MyTread();
		MyTread myTread3=new MyTread();
		myTread.setName("²Ü²Ù");
		myTread2.setName("Áõ±¸");
		myTread3.setName("Öî¸ğÁÁ");
		myTread.start();
		myTread2.start();
		myTread3.start();
		
	}

}
