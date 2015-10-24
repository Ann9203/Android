package day13;

import java.util.Scanner;

public class Day13_StringBufferDemo {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
		StringBuffer sb=new StringBuffer(s);
		sb.reverse();
		System.out.println(sb);
		sc.close();
//		Scanner sc=new Scanner(System.in);
//		String s=sc.nextLine();
//		char[] c=s.toCharArray();
//		StringBuffer sb1=new StringBuffer();
//		for (int i = c.length-1; i >=0; i--) {
//			sb1.append(c[i]);
//			
//		}
//		System.out.println(sb1);
//		sc.close();
//		String s = "abc";
//		change(s);
//		System.out.println(s); 
//		String s1 = "a";
//		String s2 = "b";
//		String s3 = "ab";
//		System.out.println(s3 == s1 + s2);   
//		System.out.println(s3 == "a" + "b"); 
	}
	public static void change(String s)
	{
		s += "hello";
	}

	
}
