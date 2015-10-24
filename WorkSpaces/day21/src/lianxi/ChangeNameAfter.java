package lianxi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

public class ChangeNameAfter {
		/*
		 * 先进性改名字
		 * */
	public static void main(String[] args) throws IOException {
		File file=new File("Z:\\java");
		File[] files=file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return new File(dir,name).isFile()&& name.endsWith(".java");
				
			}
		});
		File deskFile=new File("X:\\oo");
		if(!deskFile.exists())
		{
			deskFile.mkdir();
		}
		for (File file2 : files) {
			
			String name=file2.getName();
			File file3=new File(file,name);
			File file4=new File(deskFile,name);
			BufferedWriter bWriter=new BufferedWriter(new FileWriter(file4));
			BufferedReader bReader=new BufferedReader(new FileReader(file3));
			char[] ch=new char[1024];
			int leng=0;
			while((leng=bReader.read(ch))!=-1)
			{
				bWriter.write(ch,0,leng);
			}
			bWriter.close();
			bReader.close();
		}
		File[] filsFile=deskFile.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return new File(dir,name).isFile()&&name.endsWith(".java");
				
			}
		});
		for (File file2 : filsFile) {
			String name=file2.getName();
			String newname=name.substring(0,name.lastIndexOf(".java"))+".txt";
			File file3 =new File(deskFile,newname);
			file2.renameTo(file3); 
			
		}
	}
}
