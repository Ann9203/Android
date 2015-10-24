package exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exam12 {
/*
 * 11、编写程序，
 * 循环接收用户从键盘输入多个字符串，
 * 直到输入“end”时循环结束，
 * 并将所有已输入的字符串按字典顺序倒序打印。
 * 
 * 思路：
 * 		键盘录入：Scanner对象，然后接受
 * 		按照字典顺序，就是要进行比较排序
 * 		使用BufferBuilder有按照可以倒序打印
 * */
	public static void main(String[] args) {
		//创建stringBuilder对象
		//StringBuilder sBuilder=new StringBuilder();
		ArrayList<String> aList=new ArrayList<String>();
		Scanner scanner=new Scanner(System.in);
		//String string=scanner.nextLine();
		//String string=scanner.next();
		//因为是一直在打印
		while(true)
		{
			String string=scanner.nextLine();
			if("end".equals(string))
			{
				break;
			}
			
			
			//string.compareTo(string);
//			sBuilder.append(string);	
			aList.add(string);
		}
		
		Collections.sort(aList, Collections.reverseOrder());
		for (String string : aList) {
			System.out.print(string);
		}
		
		
	}
}
