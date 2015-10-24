package lianxi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class KeyBordCopy {
	/*
	 * 键盘录入数据 写入到文件（敲一遍）
	 * */
	public static void main(String[] args)throws IOException {
		//创建键盘录入的对象
		System.out.println("请输入数据：");
		Scanner scanner=new Scanner(System.in);
		//创建写出流对象
	
		//FileWriter fWriter=new FileWriter("a.txt");
		//还是创建Buffer的输出流，因为有自动的换行
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("a.txt"));
		//把键盘录入的数据存入在文件中，其实和把String类型的数据写入到文件中是一个道理的
		//初始化一个字符串
		String string=null;
		while((string=scanner.nextLine() )!= null)
		{
			//这样的while循环要给出一个结束语，
			if("over".equals(string))
			{
				break;
			}
			bWriter.write(string);
			bWriter.newLine();
			bWriter.flush();
		}
		bWriter.close();
		scanner.close();
		
	}

}
