package filedemo;

import java.io.File;
import java.io.IOException;

public class FileDemo {
	/*
	 * ������������������Ŀ¼�д����ļ�a.txt,
	 *  ��ô�����ʵ��?F:\\hello\\world\\Java\\a.txt ����һ���ļ�
	 *  ˼·��
	 *  	�ȴ����ļ��У������ļ����д����ļ�
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		File file1=new File("Z:"
				+ "\\hello\\world\\java");
		System.out.println(file1.mkdirs());
		File file2=new File(file1, "a.txt");
		file2.createNewFile();
		
	}
	

}
