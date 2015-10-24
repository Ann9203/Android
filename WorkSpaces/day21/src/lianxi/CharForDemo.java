package lianxi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharForDemo {
	/*
	 * 字符流的遍历的集中方式
	 * 
	 * 		低效率：
	 * 			一个字符一个字符的遍历
	 * 			一个字符数组的进行遍历
	 *		高效：
	 *			一次一个字符
	 *			一次一个字符数组
	 *			一次一行的去遍历
	 *
	 * */
		public static void main(String[] args) throws IOException {
			
			//LowBianLi();
			//HightBianLi();
			//BufferBianLi();
			//BufferBianLiShuZu();
			LineRead();
		}
		//低效率的进行遍历
		public static void LowBianLi() throws IOException
		{
			//创建流对象
			FileReader fd=new FileReader("Z:\\ccc.txt");
			FileWriter fWriter=new FileWriter("X:\\ww.txt");
			int ch=0;
			while((ch=fd.read())!=-1)
			{
				fWriter.write(ch);
				fWriter.flush();
			}
			fWriter.close();
			fd.close();
			
			
		}
		//低效率的数组进行遍历
		public static void HightBianLi()throws IOException
		{
			//创建输出流对象
			FileWriter fWriter=new FileWriter("X:\\ee.txt");
			//创建输入流对象
			FileReader fd=new FileReader("Z:\\ccc.txt");
			//创建一个数组
			char[] chs=new char[1024];
			int leng=0;
			//
			while((leng=fd.read(chs))!=-1)
			{
				fWriter.write(chs,0,leng);
				fWriter.flush();
			}
			fWriter.close();
			fd.close();
		}
		//高效的遍历方式
		//一个字符一个字符的进行遍历
		public static void BufferBianLi()throws IOException
		{
			BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\pp.txt"));
			BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
			int ch=0;
			while((ch=bReader.read())!=-1)
			{
				bWriter.write(ch);
				bWriter.flush();
			}
			bWriter.close();
			bReader.close();
			
		}
		//一个数组一个数组的进行遍历
		public static  void BufferBianLiShuZu()throws IOException
		{
			BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\uu.txt"));
			BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
			char[] chs=new char[1024];
			int leng=0;
			while((leng=bReader.read(chs))!=-1)
			{
				bWriter.write(chs,0,leng);
				bWriter.flush();
			}
			bWriter.close();
			bReader.close();
		}
		//一行一行的来读取文件
		public static void LineRead()throws IOException
		{
			//创建流对象
			BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
			BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\yy.txt"));
			//一行一行的读取，String初始为Null
			String string=null;
			//一行一行的读取
			while((string=bReader.readLine())!=null)
			{
				bWriter.write(string);
				//因为读取是不会读取分隔符的，所以要自动加上分隔符
				bWriter.newLine();
			}
			bWriter.close();
			bReader.close();                                                                      
			
			
		}
}
