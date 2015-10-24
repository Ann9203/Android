package day20lianxi;

import java.io.File;

public class DiGuiShow {
	
public static void main(String[] args) {
	File file = new File("Z:\\");

	showFile(file);
}
			
		

		public static void showFile(File file) {

			File[] fileArr = file.listFiles();
			//需要问一下老师，为什么不加if会出现空指针异常，加if才会运行正常；
		
				for (File files : fileArr) {
					if (files.isDirectory()) {
						showFile(files);
					} else if (files.getName().endsWith(".java")) {
						System.out.println(files.getAbsolutePath() + "***"
								+ files.getName());
					}
				}
			
		}
}
