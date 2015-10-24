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
	 * 编写一个程序，将d:\java目录（单级目录）
	 * 下的所有.java文件复制到d:\jad目录下，
	 * 并将原来文件的扩展名从.java改为.jad
	 * 
	 * */
	public static void main(String[] args) throws IOException{
		/*
		 * 思路：
		 * 		A封装一个数据源
		 * 		B:循环这个目录下的所有的文件
		 * 		C:循环到所有文件之后，然后取得问一个文件的名称
		 * 		D:对文件的名称进行改名，先改名
		 * 			1.就是截取原来的名字，截取到最后一次出现的.java之前，然后加上要更改的".jad"后缀名
		 * 		C:获取到新的名称之后，然后创建要复制的文件的对象，以及要被复制到一个位置的目的地的对象文件
		 * 		D:用Buffered缓冲流进行复制文件
		 * */
		//创建一个file，给出路径
		File file=new File("Z:\\java");
		//遍历file，得到file单级目录下的所有的文件包括文件夹
		//获取改单级目录下所有的以。java为结尾的文件
		File[] files =file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				//return false;
				return new File(dir,name).isFile()&&name.endsWith(".java");
			}
		});
		/*
		 * 循环所有的Files数组，然后先改名后复制到目录下
		 * 
		 * */
		//先判断要复制到的目的地的文件夹是否存在
		//如果不存在的话就要进行创建h
		File  deskFile=new File("Z:\\jad");
		if(!deskFile.exists())
		{
			deskFile.mkdir();
		}
		for (File file2 : files) {
			//获取到文件夹的名称
			String name=file2.getName();
			//先改名字然后再复制
			//name.lasIndexOf:这个是最后出现的，java的索引，进行截取
			//避免发生a.java.ssd.java这时候就会出现问题
			String Newname=name.substring(0,name.lastIndexOf(".java"))+".jad";
			//有名字了，就要创建一个新的文件
			//这个就是将具体的文件复制到具体的文件之下
//			BufferedReader bReader=new BufferedReader(new File(file, nam));
//			BufferedWriter bWriter=new BufferedWriter(new File(deskFile,Newname));
			//BufferedReader bReader=new BufferedReader(new File(file,name));
			//BufferedReader bReader=new BufferedReader(new File);
			//根据目录和文件，要组装成一个完整的路径
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
