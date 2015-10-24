package demo1;

import java.util.Scanner;

public class ZhengZe {

	public static void main(String[] args) {
//		String regex="\\w+@\\w{2,8}(\\.\\w{2,3})+";
//		while(true)
//			
//		{
//			Scanner scanner=new Scanner(System.in);
//			String string=scanner.nextLine();
//			if("over".equals(string))
//			{
//				break;
//			}
//			boolean flag=string.matches(regex);
//			System.out.println(flag);
//		}
		String string="sdddkgggabbbckkk";
		String regex="(.)\\1+";
		//boolean falge=string.matches(regex);
		String[] string2=string.split(regex);
		//System.out.println(falge);
		for (String string3: string2) {
			System.out.println(string3);
		}
//		String ooo = "eheeheeehe";
//		String[] strings =ooo.split("e");
//		for (String string : strings) {
//			System.out.println(string);
//		}

		
	}
}
