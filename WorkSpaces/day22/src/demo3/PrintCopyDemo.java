package demo3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintCopyDemo {
	/*
	 * 
	 * ʹ�ô�ӡ�������ı��ļ��ĸ���
	 * */
	public static void main(String[] args) throws IOException {
//		//��������Դ
//		BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
//		//����Ŀ�ĵ�����Դ
//		PrintWriter pWriter=new PrintWriter("hh.txt");
//		String string=null;
//		while((string=bReader.readLine())!=null)
//		{
//			//�����Զ�ˢ�µĹ���
//			pWriter.println(string);
//			
//		}
//		pWriter.close();
//		bReader.close();
		// ��������Դ,ֻ����д�����ݣ�û�ж�ȡ���ݵĹ���
		PrintWriter pWriter=new PrintWriter("cc.txt");
		//��������Դ
		BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
		String string=null;
		while((string=bReader.readLine())!=null)
		{
			pWriter.println(string);
		}
		pWriter.close();
		bReader.close();
		
	}

}
