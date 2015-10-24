package exam;

import java.util.TreeSet;

public class Exam6 {
	public static void main(String[] args) {
		TreeSet<Integer>tSet=new TreeSet<Integer>();
		//Object object=new Object();
		while(tSet.size()<10)
		{
			//Set.add(((int)Math.random()*10+1));
			tSet.add((int) (Math.random() * 10 + 1));
		}
		System.out.println(tSet);
//		TreeSet<Integer> ts = new TreeSet<Integer>();
//		// Random rd = new Random();
//		//¿ØÖÆ³¤¶È
//		while (ts.size() < 10) {
//			ts.add((int) (Math.random() * 10 + 1));
//		}
//		System.out.println(ts);
	}

}
