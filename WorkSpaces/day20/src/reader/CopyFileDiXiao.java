package reader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFileDiXiao {

		/*
		 * �����ļ�����������
		 * 	һ�����Ǹ�Ч��
		 * 	ͨ�� public int read(char[] ch)����
		 * ��һ������ͨ����Ч�ʵķ�ʽ
		 * */
		public static void main(String[] args) throws IOException {
			
			FileReader fd=new FileReader("Z:\\aa.txt");
			FileWriter fw=new FileWriter("X:\\c.txt");
			int length=0;
			//����ѭ��������һ��һ���Ķ�ȡ��Ȼ��һ��һ����д���ļ���
			while((length=fd.read())!=-1)
			{
				fw.write(length);
				
			}
			//��û�йرվ�û��ˢ��
			//û��ˢ�¾Ͳ���д�뵽�ļ���ȥ
			fd.close();
			fw.close();
			
		}
}
