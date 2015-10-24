package exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Exam1 {
	/*
	 * 从文本中读取在控制台显示
	 *    思路：
	 *    		要有一个标准的控制台输出流
	 *    		B:一行一行的读取的话就要转型
	 *    		然后写入到文本中去
	 * */
	public static void main(String[] args) throws IOException {
		/*//创建一个标准的控制台输入流
		System.out.println("请输入数字：");
		InputStream isr=System.in;
		//控制台的输入流字节型的，读取文件就要字节转换成字符流准换流
		InputStreamReader iStreamReader=new InputStreamReader(isr);
		//创建数据源对象
		BufferedReader bReader=new BufferedReader(iStreamReader);
		//创建目的地的流对象
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("nn.txt"));
		//定义一个字符串
		String string =null;
		while((string=bReader.readLine())!=null)
		{
			if("over".equals(string))
			{
				break;
			}
			bWriter.write(string);
			bWriter.flush();
		}
		bWriter.close();
		bReader.close();*/
		
		//需求就是从文本中读取数据，然后显示在控制台上
		BufferedReader bufferedReader=null;
		try {
			bufferedReader=new BufferedReader(new FileReader("t.txt"));
			String string=null;
			while((string=bufferedReader.readLine())!=null)
			{
				System.out.println(string);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();}
		finally
		
		{
			{if(bufferedReader!=null)
				try {
					bufferedReader.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			
			
		}
		
		
		
	}

}
