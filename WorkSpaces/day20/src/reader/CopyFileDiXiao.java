package reader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFileDiXiao {

		/*
		 * 复制文件有两个方法
		 * 	一个就是高效率
		 * 	通过 public int read(char[] ch)方法
		 * 另一个就是通过低效率的方式
		 * */
		public static void main(String[] args) throws IOException {
			
			FileReader fd=new FileReader("Z:\\aa.txt");
			FileWriter fw=new FileWriter("X:\\c.txt");
			int length=0;
			//进行循环，这是一个一个的读取，然后一个一个的写入文件中
			while((length=fd.read())!=-1)
			{
				fw.write(length);
				
			}
			//流没有关闭就没有刷新
			//没有刷新就不会写入到文件中去
			fd.close();
			fw.close();
			
		}
}
