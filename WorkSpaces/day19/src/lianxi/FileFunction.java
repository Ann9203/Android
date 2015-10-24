package lianxi;

import java.io.File;
import java.io.IOException;

public class FileFunction {
/*
 * 1：构造方法（敲一遍）
 * File(String pathname) 根据指定的路径创建file对象
 * File(String parent,String child)：根据指定的父文件夹和子文件或者文件夹创建File对象
 * File(File parent,String child):根据指定的父文件夹对象和子文件或者文件夹创建File对象
 * 2：成员方法创建功能（敲两遍）删除功能（敲两遍）判断功能（敲一遍）获取功能（敲一遍）
 * 
 * */
	public static void main(String[] args) throws IOException {
		//File构造方法的联系
		//这个构造方法就是没有指定的路径，就直接添加到根路径中
		File file=new File("a.txt");
		//添加文件使用的方法
		//返回的是boolean类型
		file.createNewFile();
		//在指定的父目录下创建一个文件夹
		File file1=new File("Z:", "a.txt");
		file1.createNewFile();
		//创建文件夹的方法
		// mkdir  mkdirs
		File file2=new File("Z:","aaa\\bbb");
		//mkdirs创建的是多级文件夹
		file2.mkdirs();
		//mkdir只能创建一个文件夹，不可以创建多级文件夹
		File file3=new File(file2, "b.txt");
		file3.createNewFile();
		/*
		 * 删除功能：
		 * 		A:删除文件夹
		 * 		B：也可以删除文件
		 * 		C:删除的文件是不放在垃圾回收中的。直接就删除的
		 * 
		 * */
//		System.out.println(file.delete());//删除根目录下的a.txt文件
//		System.out.println(file1.delete());//删除根目录下的a.txt文件
//		System.out.println(file3.delete());//删除Z盘下的b.txt文件
//		System.out.println(file2.delete());////删除Z盘下的文件夹  //因为bb文件夹中还有内容所以应该先要删掉内容再删掉其他的文件夹		
//		System.out.println(file2.delete());
		//这个删除只能是一级一级的删除
//		file1.delete();//删除Z盘下的a.txt文件
//		file2.delete();//删除Z盘下的文件夹
//		file3.delete();//删除Z盘下的b.txt文件
		
		/*
		 * 判断功能
		 * 			Boolean exists():判断file对象是否存在
					Boolean isFile();判断file对象是否是文件
					Boolean isDirectory()：判断file对象是否是文件夹
					Boolean isAbsolute();判断file对象是否是绝对路径
					Boolean canRead();判断file对象是否可读
					Boolean canWrite();判断file对象是否可写
					Boolean isHidden()：判断file对象是否隐藏
		 * 
		 * */
		
/*		System.out.println(file.exists());
		System.out.println(file2.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.isAbsolute());
		System.out.println(file.canRead());
		System.out.println(file.canWrite());
		System.out.println(file.isHidden());*/
		/*
		 * 
		 * 
		 * 
		 * 获取功能
		 * 			String getAbsolutepath():绝对路径
					String getPath();相对路径
					String getName();文件名称
					Long length();文件大小，单位是字节
					Long lastModified()：上次修时间毫秒值
				重要的获取gongn
					Public static File[] listRoots();列出可用系统文件的根目录
					Public string[] list();返回的是指定目录下所有文件夹或文件的名称
					Public File[] listFiles() 返回的是指定目录下所有文件或者文件夹的对象数组

		 * 
		 * */
		
		System.out.println(file.getParentFile());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getPath());
		System.out.println(file.getParent());
		System.out.println(file.getName());
		System.out.println(file.length());
		System.out.println(file.lastModified());
		//获取的是盘符
		File[] files=File.listRoots();
		for (File file4 : files) {
			System.out.println(file4);
		}
		System.out.println("**************");
		//打印的是文件夹的根目录下的内容
		String[] strings=file2.list();
		for (String string : strings) {
			System.out.println(string);
		}
		File[] files2=file2.listFiles();
		for (File file4 : files2) 
			System.out.println(file4);
			
		}
		
		
	}

