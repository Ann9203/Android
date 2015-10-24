package lianxi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharForDemo {
	/*
	 * �ַ����ı����ļ��з�ʽ
	 * 
	 * 		��Ч�ʣ�
	 * 			һ���ַ�һ���ַ��ı���
	 * 			һ���ַ�����Ľ��б���
	 *		��Ч��
	 *			һ��һ���ַ�
	 *			һ��һ���ַ�����
	 *			һ��һ�е�ȥ����
	 *
	 * */
		public static void main(String[] args) throws IOException {
			
			//LowBianLi();
			//HightBianLi();
			//BufferBianLi();
			//BufferBianLiShuZu();
			LineRead();
		}
		//��Ч�ʵĽ��б���
		public static void LowBianLi() throws IOException
		{
			//����������
			FileReader fd=new FileReader("Z:\\ccc.txt");
			FileWriter fWriter=new FileWriter("X:\\ww.txt");
			int ch=0;
			while((ch=fd.read())!=-1)
			{
				fWriter.write(ch);
				fWriter.flush();
			}
			fWriter.close();
			fd.close();
			
			
		}
		//��Ч�ʵ�������б���
		public static void HightBianLi()throws IOException
		{
			//�������������
			FileWriter fWriter=new FileWriter("X:\\ee.txt");
			//��������������
			FileReader fd=new FileReader("Z:\\ccc.txt");
			//����һ������
			char[] chs=new char[1024];
			int leng=0;
			//
			while((leng=fd.read(chs))!=-1)
			{
				fWriter.write(chs,0,leng);
				fWriter.flush();
			}
			fWriter.close();
			fd.close();
		}
		//��Ч�ı�����ʽ
		//һ���ַ�һ���ַ��Ľ��б���
		public static void BufferBianLi()throws IOException
		{
			BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\pp.txt"));
			BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
			int ch=0;
			while((ch=bReader.read())!=-1)
			{
				bWriter.write(ch);
				bWriter.flush();
			}
			bWriter.close();
			bReader.close();
			
		}
		//һ������һ������Ľ��б���
		public static  void BufferBianLiShuZu()throws IOException
		{
			BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\uu.txt"));
			BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
			char[] chs=new char[1024];
			int leng=0;
			while((leng=bReader.read(chs))!=-1)
			{
				bWriter.write(chs,0,leng);
				bWriter.flush();
			}
			bWriter.close();
			bReader.close();
		}
		//һ��һ�е�����ȡ�ļ�
		public static void LineRead()throws IOException
		{
			//����������
			BufferedReader bReader=new BufferedReader(new FileReader("Z:\\ccc.txt"));
			BufferedWriter bWriter=new BufferedWriter(new FileWriter("X:\\yy.txt"));
			//һ��һ�еĶ�ȡ��String��ʼΪNull
			String string=null;
			//һ��һ�еĶ�ȡ
			while((string=bReader.readLine())!=null)
			{
				bWriter.write(string);
				//��Ϊ��ȡ�ǲ����ȡ�ָ����ģ�����Ҫ�Զ����Ϸָ���
				bWriter.newLine();
			}
			bWriter.close();
			bReader.close();                                                                      
			
			
		}
}
