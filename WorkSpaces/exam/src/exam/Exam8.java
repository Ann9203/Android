
package exam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Exam8 {
/*
 * ʹ�ô����幦�ܵ��ֽ��������ļ���
 * */
	public static void main(String[] args) throws IOException {
		//��������Դ
		BufferedReader br=new BufferedReader(new FileReader("Z:\\ccc.txt"));
		//��ʼ��Ŀ�ĵ�Դ����
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("stu.txt"));
		//�����ַ���
		String leng=null;
		//���������ַ�д�뵽�ַ����У�ֱ������Ϊֵ
		while((leng=br.readLine())!=null)
		{
			bWriter.write(leng);
			//ˢ����
			bWriter.flush();
		}
		bWriter.close();
		
	}
}
