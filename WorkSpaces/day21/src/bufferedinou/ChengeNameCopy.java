package bufferedinou;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

public class ChengeNameCopy {
	/*
	 * 
	 * ��дһ�����򣬽�d:\javaĿ¼������Ŀ¼��
	 * �µ�����.java�ļ����Ƶ�d:\jadĿ¼�£�
	 * ����ԭ���ļ�����չ����.java��Ϊ.jad
	 * 
	 * */
	public static void main(String[] args) throws IOException{
		/*
		 * ˼·��
		 * 		A��װһ������Դ
		 * 		B:ѭ�����Ŀ¼�µ����е��ļ�
		 * 		C:ѭ���������ļ�֮��Ȼ��ȡ����һ���ļ�������
		 * 		D:���ļ������ƽ��и������ȸ���
		 * 			1.���ǽ�ȡԭ�������֣���ȡ�����һ�γ��ֵ�.java֮ǰ��Ȼ�����Ҫ���ĵ�".jad"��׺��
		 * 		C:��ȡ���µ�����֮��Ȼ�󴴽�Ҫ���Ƶ��ļ��Ķ����Լ�Ҫ�����Ƶ�һ��λ�õ�Ŀ�ĵصĶ����ļ�
		 * 		D:��Buffered���������и����ļ�
		 * */
		//����һ��file������·��
		File file=new File("Z:\\java");
		//����file���õ�file����Ŀ¼�µ����е��ļ������ļ���
		//��ȡ�ĵ���Ŀ¼�����е��ԡ�javaΪ��β���ļ�
		File[] files =file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				//return false;
				return new File(dir,name).isFile()&&name.endsWith(".java");
			}
		});
		/*
		 * ѭ�����е�Files���飬Ȼ���ȸ������Ƶ�Ŀ¼��
		 * 
		 * */
		//���ж�Ҫ���Ƶ���Ŀ�ĵص��ļ����Ƿ����
		//��������ڵĻ���Ҫ���д���h
		File  deskFile=new File("Z:\\jad");
		if(!deskFile.exists())
		{
			deskFile.mkdir();
		}
		for (File file2 : files) {
			//��ȡ���ļ��е�����
			String name=file2.getName();
			//�ȸ�����Ȼ���ٸ���
			//name.lasIndexOf:����������ֵģ�java�����������н�ȡ
			//���ⷢ��a.java.ssd.java��ʱ��ͻ��������
			String Newname=name.substring(0,name.lastIndexOf(".java"))+".jad";
			//�������ˣ���Ҫ����һ���µ��ļ�
			//������ǽ�������ļ����Ƶ�������ļ�֮��
//			BufferedReader bReader=new BufferedReader(new File(file, nam));
//			BufferedWriter bWriter=new BufferedWriter(new File(deskFile,Newname));
			//BufferedReader bReader=new BufferedReader(new File(file,name));
			//BufferedReader bReader=new BufferedReader(new File);
			//����Ŀ¼���ļ���Ҫ��װ��һ��������·��
			File file3=new File(deskFile, Newname);
			File file4=new File(file,name);
			BufferedReader bReader=new BufferedReader(new FileReader(file4));
			BufferedWriter bWriter=new BufferedWriter(new FileWriter(file3));
			
			char[] ch=new char[1024];
			int leng=0;
			while((leng=bReader.read(ch))!=-1)
			{
				bWriter.write(ch,0,leng);
				bWriter.flush();
			}
			bWriter.close();
			bReader.close();
			
		}
		
	}

}
