package day20lianxi;

import java.io.File;

public class DiGuiShow {
	
public static void main(String[] args) {
	File file = new File("Z:\\");

	showFile(file);
}
			
		

		public static void showFile(File file) {

			File[] fileArr = file.listFiles();
			//��Ҫ��һ����ʦ��Ϊʲô����if����ֿ�ָ���쳣����if�Ż�����������
		
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
