package bufferedinou;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class BufferedInOut {
	/*
	 * 
	 * ����:�û����������ļ�
		����Դ: 
			FileOutputStreamDemo.java
		Ŀ�ĵ�:  
			D:\\ Copy.java
	 * */
	public static void main(String[] args) throws IOException {
//		BufferedReader bReader=new BufferedReader(new FileReader("Z:\\aa.txt")) ;
//		BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\u.txt"));
//		char[] chs=new char[1024];
//		int len=0;
//		while((len=bReader.read(chs))!=-1)
//		{
//			bWriter.write(chs,0,len);
//		}
//		bWriter.close();
//		bReader.close();
		BufferedReader bReader=new BufferedReader(new FileReader("Z:\\aa.txt"));
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\c.txt"));
		char[] chs=new char[1024];
		int leng=0;
		while((leng=bReader.read(chs))!=-1)
		{
			bWriter.write(chs,0,leng);
		}
		bReader.close();
		bWriter.close();
	}

}
