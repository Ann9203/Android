package digui;

import java.io.File;

public class DiGuiSelect {
	/*
	 * 使用递归调用方法进行查询
	 * */
	public static void main(String[] args) {
		File file=new File("Z:\\aaa");
		select(file);
		
	}
	//写一个ie关于查询的函数
	/*
	 * 方法主要就是查询a盘下带有".map3"的文件
	 * 
	 * */
	public static void select(File file)
	{
		//首先把所有文件取出
	
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
