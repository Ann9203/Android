package FileInPutStreamDemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class printDemo1 {
	public static void main(String[] args) throws IOException {
		PrintWriter printWriter=new PrintWriter(new FileWriter("X:\\ll.txt"), true);
		BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
		String string=null;
		while((string=bReader.readLine())!=null)
		{
			printWriter.write(string);
			printWriter.println();
		}
		printWriter.close();
		bReader.close();
		
	}
}