package exam;

import java.util.ArrayList;

public class Exam15 {
	/*
	 * 向泛型为Integer的ArrayList中添加一个String类型的元素
	 * */
	public static void main(String[] args) {
		//new一个泛型集合
		ArrayList<Integer> aList=new ArrayList<Integer>();
		int x=Integer.parseInt("12345678");
		aList.add(x);
		System.out.println(aList);
		
		
	}

}
