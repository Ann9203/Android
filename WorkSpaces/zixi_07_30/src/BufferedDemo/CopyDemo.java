package BufferedDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyDemo {
	/*
	 * ʹ��Buffered����copy��
	 * */
	public static void main(String[] args) {
		
		//��Ч�ʵĶ�ȡ��ʽ
		BufferedReader bReader=null;
		BufferedWriter bWriter=null;
//		try {
//			bReader=new BufferedReader(new FileReader("Z:\\aa.txt"));
//			 bWriter=new BufferedWriter(new FileWriter("X:\\c.txt"));
//			//char[] ch=new char[1024];
//			int b=0;
//			while((b=bReader.read())!=-1)
//			{
//				bWriter.write((char)b);
//			}
//			
//		} catch (IOException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		finally{
//				try {
//					if(bReader!=null)
//					{
//						bReader.close();
//					}
//				} catch (IOException e2) {
//					// TODO: handle exception
//					e2.printStackTrace();
//				}
//				try {
//					if(bWriter!=null)
//					{
//						bWriter.close();
//					}
//				} catch (IOException e2) {
//					// TODO: handle exception
//					e2.printStackTrace();
//				}
//			
//		}

		//��Ч�ķ�ʽ
		try {
			bReader=new BufferedReader(new FileReader("Z:\\aa.txt"));
			bWriter=new BufferedWriter(new FileWriter("X:\\t.txt"));
			int b=0;
			char[] ch=new char[1024];
			while((b=bReader.read(ch))!=-1)
			{
				bWriter.write(ch, 0, b);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if(bReader!=null)
				{
					bReader.close();
				}
			} catch (IOException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if(bWriter!=null)
				{
					bWriter.close();
				}
			} catch (IOException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		
	
	}

}
