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
	 * 思路：
	 * 		A封装一个数据源
	 * 		B:循环这个目录下的所有的文件
	 * 		D;循环玩文件之后进行复制
	 * 		C:修改名称
	 * */
	public static void main(String[] args) throws IOException {
		
		File file=new File("Z:\\java");
		//循环目录，找出符合条件的文件
		File[] files=file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				//return false;
				return new File(dir,name).isFile()&&name.endsWith(".java"); 
					
			}
		});
		//创建目的地对象
		File file2 =new File("Z:\\uu");
		if(!file2.exists())
		{
			file2.mkdir();
		}
		//循环Files 然后把Files中循环出来的元素添加到了
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
		//更改名称
		//根据for循环出每一个文件，然后进行
		
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
