package filewriter;

import java.io.FileWriter;
import java.io.IOException;

public class WriterStringDemo {
	/*
	 * ���д������
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		
		FileWriter fwFileWriter=new FileWriter("a.txt",true);
		fwFileWriter.write("Hello\t\n");
		fwFileWriter.write("Java\t\n");
		fwFileWriter.write("java\t\n");
		fwFileWriter.close();
	}

}
