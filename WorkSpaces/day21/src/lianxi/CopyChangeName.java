package lianxi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

public class CopyChangeName {
	/*
	 * ����z��uuĿ¼�µ����е�.java���ļ�
	 * ���Ǹ��Ƶ�X��xxĿ¼��֮��Ҫ��.java�ļ����и�������.txt;
	 * 
	 * */
	/*
	 * ˼·��
	 * 	��ɸѡ�������������ļ�
	 * Ȼ������ļ����������ļ���·��
	 * Ȼ���������
	 * Ȼ��Ѹĺ����Ƶ��ļ����и���
	 * 
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		
		//����������
		File file= new File("Z:\\java");
		//ѭ������file�е����е��ļ���ɸѡ�������������ļ�
		File[] files=file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return new File(dir,name).isFile() && name.endsWith(".java");
			}
		});
		//����Ŀ�ĵص��ļ��ж���
		File desk=new File("X:\\jdk");
		//�жϴ��ļ����Ƿ���ڣ������ھʹ���
		if(!desk.exists())
		{
			desk.mkdir();
		}
		//ѭ���õ���ɸѡ������File����
		for (File file2 : files) {
			//�õ�ûһ���ļ��е�����
			String name=file2.getName();
			//���ĺ�׺������
			String newName=name.substring(0,name.lastIndexOf(".java"))+".txt";
			//���������ˣ�Ȼ��ʹ����ļ�����
			File file3=new File(file, name);
			File file4=new File(desk,newName);
			//����������
			BufferedWriter bWriter=new BufferedWriter(new FileWriter(file4));
			BufferedReader bReader=new BufferedReader(new FileReader(file3));
			//��������
			char[] chs=new char[1024];
			int leng=0;
			while((leng=bReader.read(chs))!=-1)
			{
				bWriter.write(chs);
			}
			bWriter.close();
			bReader.close();
			
		}
		
	}
}
