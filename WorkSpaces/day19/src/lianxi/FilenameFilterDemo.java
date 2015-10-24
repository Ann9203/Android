package lianxi;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterDemo {

		/*
		 * :�ļ�������
				��ȡָ��Ŀ¼��ָ����׺���ļ����ƣ�
					A:�Ȼ�ȡָ��Ŀ¼�������ļ������ļ��е�file���飬Ȼ���ٱ�����ʱ���жϣ����������ľ������File������
					B:ֱ�ӻ�ȡָ��Ŀ¼������������String(File)���飬Ȼ���������
				�ļ��ĳɹ�������FilenameFilter 
				Public String []list(FilenameFilter file)

		 * 
		 * */
	public static void main(String[] args) {
		File file=new File("Z:");
//		File file2=new File(file,"")
	
		String[] strings =file.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				//return false;
				//����Ҫ��ȡ�ľ��ǻ�ȡ�����ļ��������ļ���
				//File[] files=file.listFiles();
				//��dir  ��nameͨ�����캯���������µ�file�ļ�
				File file=new File(dir,name);
				boolean flag=file.isFile();
				//��ξ���Ҫ��ȡ����String�����Ʊ�������.mp3Ϊ��β��
				boolean flag2=name.endsWith(".mp3");
				return  flag && flag2;
				
			}
		});
		for (String string : strings) {
			System.out.println(string);
		}
	}
}
