package filewriter;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterEx {
	/*
	 * 如何抛出异常
	 * 正常的写法
	 * */
	public static void main(String[] args) {
		
		FileWriter fileWriter=null;
		try {
			fileWriter=new FileWriter("d.txt");
			fileWriter.write("Hello,EveryBody");
			fileWriter.flush();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally
		{
			if(fileWriter!=null)
			{
				try {
					fileWriter.close();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

}
