package lianxi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class KeyBordCopy {
	/*
	 * ����¼������ д�뵽�ļ�����һ�飩
	 * */
	public static void main(String[] args)throws IOException {
		//��������¼��Ķ���
		System.out.println("���������ݣ�");
		Scanner scanner=new Scanner(System.in);
		//����д��������
	
		//FileWriter fWriter=new FileWriter("a.txt");
		//���Ǵ���Buffer�����������Ϊ���Զ��Ļ���
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("a.txt"));
		//�Ѽ���¼������ݴ������ļ��У���ʵ�Ͱ�String���͵�����д�뵽�ļ�����һ�������
		//��ʼ��һ���ַ���
		String string=null;
		while((string=scanner.nextLine() )!= null)
		{
			//������whileѭ��Ҫ����һ�������
			if("over".equals(string))
			{
				break;
			}
			bWriter.write(string);
			bWriter.newLine();
			bWriter.flush();
		}
		bWriter.close();
		scanner.close();
		
	}

}
