package digui;

import java.io.File;

public class DeleteDemo {
/*
 * 
 * 删除Z自己创建目录下的文件以及文件夹
 * */
	public static void main(String[] args) {
		File file=new File("Z:\\aaa");
		deleteWenjian(file);
	}

private static void deleteWenjian(File file) {
	// TODO Auto-generated method stub
	File[] files=file.listFiles();
	if (file!=null) {
		//首先就是要循环file文件
		for (File file2 : files) {
			//判断是不是目录，如果是的话，就继续递归调用
			if(file2.isDirectory())
			{
				deleteWenjian(file2);
			}else {
				//如果是文件的话就要进行直接的删除
				System.out.println(file2.getName()+"***********"+file2.delete());
			}
		}
		//如果文件夹底层没有东西了，就会跳出循环，这样我们也可以将其删除
		System.out.println(file.getName()+"**************"+file.delete());
	}
	
}
}
