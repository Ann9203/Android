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
	 * 复制z盘uu目录下的所有的.java的文件
	 * 但是复制到X盘xx目录下之后，要把.java文件进行更改名称.txt;
	 * 
	 * */
	/*
	 * 思路：
	 * 	先筛选出符合条件的文件
	 * 然后根据文件定义具体的文件的路径
	 * 然后更改名称
	 * 然后把改好名称的文件进行复制
	 * 
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		
		//创建流对象
		File file= new File("Z:\\java");
		//循环遍历file中的所有的文件，筛选出符合条件的文件
		File[] files=file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return new File(dir,name).isFile() && name.endsWith(".java");
			}
		});
		//创建目的地的文件夹对象
		File desk=new File("X:\\jdk");
		//判断此文件夹是否存在，不存在就创建
		if(!desk.exists())
		{
			desk.mkdir();
		}
		//循环得到的筛选出来的File数组
		for (File file2 : files) {
			//得到没一个文件夹的名称
			String name=file2.getName();
			//更改后缀名彻骨
			String newName=name.substring(0,name.lastIndexOf(".java"))+".txt";
			//有了名称了，然后就创建文件对象
			File file3=new File(file, name);
			File file4=new File(desk,newName);
			//创建流对象
			BufferedWriter bWriter=new BufferedWriter(new FileWriter(file4));
			BufferedReader bReader=new BufferedReader(new FileReader(file3));
			//创建数组
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
