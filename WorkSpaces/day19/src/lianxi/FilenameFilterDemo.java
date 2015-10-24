package lianxi;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterDemo {

		/*
		 * :文件过滤器
				获取指定目录下指定后缀的文件名称：
					A:先获取指定目录下所有文件或者文件夹的file数组，然后再遍历的时候判断，满足条件的就输出改File的名称
					B:直接获取指定目录下满足条件的String(File)数组，然后遍历数组
				文件文成过滤器：FilenameFilter 
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
				//首先要获取的就是获取的是文件而不是文件夹
				//File[] files=file.listFiles();
				//用dir  和name通过构造函数，构造新的file文件
				File file=new File(dir,name);
				boolean flag=file.isFile();
				//其次就是要获取的是String的名称必须是以.mp3为结尾的
				boolean flag2=name.endsWith(".mp3");
				return  flag && flag2;
				
			}
		});
		for (String string : strings) {
			System.out.println(string);
		}
	}
}
