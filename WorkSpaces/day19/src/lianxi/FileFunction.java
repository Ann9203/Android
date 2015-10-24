package lianxi;

import java.io.File;
import java.io.IOException;

public class FileFunction {
/*
 * 1�����췽������һ�飩
 * File(String pathname) ����ָ����·������file����
 * File(String parent,String child)������ָ���ĸ��ļ��к����ļ������ļ��д���File����
 * File(File parent,String child):����ָ���ĸ��ļ��ж�������ļ������ļ��д���File����
 * 2����Ա�����������ܣ������飩ɾ�����ܣ������飩�жϹ��ܣ���һ�飩��ȡ���ܣ���һ�飩
 * 
 * */
	public static void main(String[] args) throws IOException {
		//File���췽������ϵ
		//������췽������û��ָ����·������ֱ����ӵ���·����
		File file=new File("a.txt");
		//����ļ�ʹ�õķ���
		//���ص���boolean����
		file.createNewFile();
		//��ָ���ĸ�Ŀ¼�´���һ���ļ���
		File file1=new File("Z:", "a.txt");
		file1.createNewFile();
		//�����ļ��еķ���
		// mkdir  mkdirs
		File file2=new File("Z:","aaa\\bbb");
		//mkdirs�������Ƕ༶�ļ���
		file2.mkdirs();
		//mkdirֻ�ܴ���һ���ļ��У������Դ����༶�ļ���
		File file3=new File(file2, "b.txt");
		file3.createNewFile();
		/*
		 * ɾ�����ܣ�
		 * 		A:ɾ���ļ���
		 * 		B��Ҳ����ɾ���ļ�
		 * 		C:ɾ�����ļ��ǲ��������������еġ�ֱ�Ӿ�ɾ����
		 * 
		 * */
//		System.out.println(file.delete());//ɾ����Ŀ¼�µ�a.txt�ļ�
//		System.out.println(file1.delete());//ɾ����Ŀ¼�µ�a.txt�ļ�
//		System.out.println(file3.delete());//ɾ��Z���µ�b.txt�ļ�
//		System.out.println(file2.delete());////ɾ��Z���µ��ļ���  //��Ϊbb�ļ����л�����������Ӧ����Ҫɾ��������ɾ���������ļ���		
//		System.out.println(file2.delete());
		//���ɾ��ֻ����һ��һ����ɾ��
//		file1.delete();//ɾ��Z���µ�a.txt�ļ�
//		file2.delete();//ɾ��Z���µ��ļ���
//		file3.delete();//ɾ��Z���µ�b.txt�ļ�
		
		/*
		 * �жϹ���
		 * 			Boolean exists():�ж�file�����Ƿ����
					Boolean isFile();�ж�file�����Ƿ����ļ�
					Boolean isDirectory()���ж�file�����Ƿ����ļ���
					Boolean isAbsolute();�ж�file�����Ƿ��Ǿ���·��
					Boolean canRead();�ж�file�����Ƿ�ɶ�
					Boolean canWrite();�ж�file�����Ƿ��д
					Boolean isHidden()���ж�file�����Ƿ�����
		 * 
		 * */
		
/*		System.out.println(file.exists());
		System.out.println(file2.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.isAbsolute());
		System.out.println(file.canRead());
		System.out.println(file.canWrite());
		System.out.println(file.isHidden());*/
		/*
		 * 
		 * 
		 * 
		 * ��ȡ����
		 * 			String getAbsolutepath():����·��
					String getPath();���·��
					String getName();�ļ�����
					Long length();�ļ���С����λ���ֽ�
					Long lastModified()���ϴ���ʱ�����ֵ
				��Ҫ�Ļ�ȡgongn
					Public static File[] listRoots();�г�����ϵͳ�ļ��ĸ�Ŀ¼
					Public string[] list();���ص���ָ��Ŀ¼�������ļ��л��ļ�������
					Public File[] listFiles() ���ص���ָ��Ŀ¼�������ļ������ļ��еĶ�������

		 * 
		 * */
		
		System.out.println(file.getParentFile());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getPath());
		System.out.println(file.getParent());
		System.out.println(file.getName());
		System.out.println(file.length());
		System.out.println(file.lastModified());
		//��ȡ�����̷�
		File[] files=File.listRoots();
		for (File file4 : files) {
			System.out.println(file4);
		}
		System.out.println("**************");
		//��ӡ�����ļ��еĸ�Ŀ¼�µ�����
		String[] strings=file2.list();
		for (String string : strings) {
			System.out.println(string);
		}
		File[] files2=file2.listFiles();
		for (File file4 : files2) 
			System.out.println(file4);
			
		}
		
		
	}

