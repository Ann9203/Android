package bufferedinou;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class NewLineDemo {
	/*
	 * 
	 * ���ַ������������⹦�ܸ����ı��ļ�
 ����Դ��
 	FileOutputStreamDemo.java 
 Ŀ�ĵأ�
	Copy.java
	*/
	public static void main(String[] args) throws IOException {
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\pp.txt"));
		BufferedReader  bReader =new BufferedReader(new FileReader("Z:\\aa.txt"));
		String br=null;
		while(( br=bReader.readLine())!=null)
		{
			System.out.println(br);
			bWriter.write(br);
			bWriter.newLine();
		}
		bWriter.close();
		bReader.close();
		
	}

}
