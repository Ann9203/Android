package bufferedinou;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
public class AfterChangeName {
	/*
	 * ˼·��
	 * 		A��װһ������Դ
	 * 		B:ѭ�����Ŀ¼�µ����е��ļ�
	 * 		D;ѭ�����ļ�֮����и���
	 * 		C:�޸�����
	 * */
	public static void main(String[] args) throws IOException {
		
		File file=new File("Z:\\java");
		//ѭ��Ŀ¼���ҳ������������ļ�
		File[] files=file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				//return false;
				return new File(dir,name).isFile()&&name.endsWith(".java"); 
					
			}
		});
		//����Ŀ�ĵض���
		File file2 =new File("Z:\\uu");
		if(!file2.exists())
		{
			file2.mkdir();
		}
		//ѭ��Files Ȼ���Files��ѭ��������Ԫ����ӵ���
		for (File file4 : files) {
			String name=file4.getName();
			//File file3=new File(file4);
			File file5=new File(file2,name);
			BufferedWriter bWriter=new BufferedWriter(new FileWriter(file5));
			BufferedReader bReader=new BufferedReader(new FileReader(file4));
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
		//��������
		//����forѭ����ÿһ���ļ���Ȼ�����
		
		File[] fis=file2.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				//return false;
				return new File(dir,name).isFile() && name.endsWith(".java");
			}
		});
		for (File file3 : fis) {
			String name=file3.getName();
			String Newname=name.substring(0,name.lastIndexOf(".java"))+".jad";
			File newFile=new File(file2,Newname);
			file3.renameTo(newFile);
			System.out.println(file3.toString());
		}
		
	}

}
