package lianxi;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameSelect {
	//�ҳ��ļ����µ�.mp3�ļ�
	public static void main(String[] args) {
		
		File file=new File("Z:");
		//�鿴Դ��
		
		
		String[]files =file.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				//return false;
				//�����ļ�
				File file=new File(dir,name);
				boolean flag=file.isFile();
				boolean flag1=name.endsWith(".mp3");
				return flag && flag1;
				
			
			}
		});
		for (String string : files) {
			System.out.println(string);
		}
	}

}
