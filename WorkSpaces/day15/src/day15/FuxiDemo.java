package day15;

import java.util.Date;
public class FuxiDemo {
	public static void main(String[] args) {
		
		StringBuffer sBuffer=new StringBuffer();
		sBuffer.append("Hello");
		sBuffer .insert(2, "L");
		System.out.println(sBuffer);
		System.out.println(sBuffer .length());
	    System.out.println(Math .ceil(-14.3));	
		System.out.println(Math .ceil(13.2));
		System.out.println(Math .floor(-13.2));
		System.out.println(Math .floor(12.4));
		System.out.println(Math .round(12.4));
		System.out.println(Math .round(-13.2));
		String sr="Java";
		show(sr);
		System.out.println(sr);
		String s1 = "abc";
		   String s2 = new String("abc");
		   System.out.println(s1==s2); 
		   String s3 = "ÄãºÃÂð";
		   String s4 ="Äã";
		   String s5 ="ºÃÂð";
		   System.out.println(s3==(s4+s5));
		  Date date =new Date();
		  System.out.println(date);
		 System.out.println(date.getTime()); 
		 Date date2=new Date(1437565129812L);
		 System.out.println(date2);
		 date2.setTime(1437565129812L);
		 System.out.println(date2);
		

	
	}
	public static String show(String sr)
	{
		return sr+="Hello";
	}

}
