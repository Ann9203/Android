package day20lianxi;
import java.io.File;
public class DiGuiSelect {

		/*
		 * 递归查找后缀是java的文件(理解的基础上敲一遍)
		 * */
	public static void main(String[] args) {
		File file=new File("Z:");
		diGui(file);
	/*
	 * 第二种获取的方法：
	 * 		通过List含参数的F/ilenamFilter一个容器，然后进行重写add方法
	 * 
	 * */
//	File[] files=file.listFiles();
//	for (File file2 : files) {
//		System.out.println(file2);
//	}
//		String[]strings=file.list(new FilenameFilter() {
//			
//			/*
//			 * 
//			 * 这个就是根据File name，构造一个文件，然后
//			 *通过文件查看是否是文件，查看文件的名字是否是一“.java”为结尾
//			 * */
//			@Override
//			public boolean accept(File dir, String name) {
//				// TODO Auto-generated method stub
//				//return false;
//				File file=new File(dir,name);
//				boolean flag1=file.isFile();
//				boolean flag2=name.endsWith(".mp3");
//				
//				return flag1 && flag2;
//			}
//		});
//		for (String string : strings) {
//			System.out.println(string);
//		}
		
	}
	/*
	 * 递归
	 * 		主要就是看是不是目录，是目录的话就要继续进行查找，不是的话就找到要找的文件
	 * 同事给的文件夹不能是空的
	 * */
	public static void diGui(File  file)
	{
		//显示便利出所有的文件夹下的目录
		File[] file2=file.listFiles();
//		if(file2!=null)
//		{
			for (File file3 : file2) {
				if(file3.isDirectory())
				{
					diGui(file3);
				}
				else if(file3.getName().endsWith(".mp3")) {
					System.out.println(file3.getName());
				}
			}
//		}
	
	}
}
