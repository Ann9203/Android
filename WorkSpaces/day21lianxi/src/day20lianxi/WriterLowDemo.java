package day20lianxi;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class WriterLowDemo {
	/*
	 * �����ļ��ĵ�Ч�ʵķ�ʽ
	 * */
	public static void main(String[] args) throws IOException {
//		FileReader rd=new FileReader("Z:\\aa.txt");
//		FileWriter fw=new FileWriter("X:\\x.txt");
//		int length=0;
//		while((length=rd.read())!=-1)
//		{
//			fw.write(length);
//		}
//		fw.close();
//		rd.close();
		FileReader fd=new FileReader("Z:\\aa.txt");
		FileWriter fw=new FileWriter("X:\\x.txt");
		int length=0;
		while((length=fd.read())!=-1)
		{
			fw.write(length);
		}
		fd.close();
		fw.close();
	}

}
