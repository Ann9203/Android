package reader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class CopyFile {
	//���D������һ���ļ�a.txt, ������������Ƶ�E����,���Ҹ���copy.txtΪ��������?
	public static void main(String[] args) throws IOException {
		FileReader fd=new FileReader("Z:\\aa.txt");
		FileWriter fw=new FileWriter("X:\\cop.txt");
		int ch=fd.read();
		StringBuilder sBuilder=new StringBuilder();
		while (ch!=-1) {
		sBuilder.append((char)ch);
			ch=fd.read();
//			fw.write(ch);
//			ch=fd.read(); 
		}
		
		
		String string=sBuilder.toString();
		
		fw.write(string);
	
		fd.close();
		fw.close();

	}

}
