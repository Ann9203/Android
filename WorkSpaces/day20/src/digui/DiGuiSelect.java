package digui;

import java.io.File;

public class DiGuiSelect {
	/*
	 * ʹ�õݹ���÷������в�ѯ
	 * */
	public static void main(String[] args) {
		File file=new File("Z:\\aaa");
		select(file);
		
	}
	//дһ��ie���ڲ�ѯ�ĺ���
	/*
	 * ������Ҫ���ǲ�ѯa���´���".map3"���ļ�
	 * 
	 * */
	public static void select(File file)
	{
		//���Ȱ������ļ�ȡ��
	
		File[] files=file.listFiles();
		for (File file2 : files) {
			if(file2!=null)
			{
				if (file2.isDirectory()) {
					select(file2);
				}else if(file2 .getName().endsWith(".mp3")) {
					System.out.println(file2.getName());
					//System.out.println(file2 .getName().endsWith(".mp3"));
					//String string=file2 .getName().endsWith(".mp3");
					
				}
			}
		}
	}

}
