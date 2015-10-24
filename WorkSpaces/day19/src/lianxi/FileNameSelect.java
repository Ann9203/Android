package lianxi;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameSelect {
	//找出文件夹下的.mp3文件
	public static void main(String[] args) {
		
		File file=new File("Z:");
		//查看源码
		
		
		String[]files =file.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				//return false;
				//创建文件
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
