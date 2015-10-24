
package demo2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamDemo {
	/*
	 * 这个时候，我们拥有的是标准键盘录入。
	 * 而字节的输入流有两种读取方式：
	 * 方式1：一次读取一个字节，但是，这样的话，我们每次都需要对读取到的数据进行保存，将来判断是否一行结束，麻烦。
	 * 方式2：一次读取一个字节数组，但是，这个时候，最大的问题是，数组的长度定义为多大不好确定。
	 * 所以，我们觉得这两种方式都不够好。
	 * 我们就想有没有这样的一种方案，直接一次读取一行数据。
	 * 我们就想到了字符缓冲输入流的:readLine()方法。
	 * 我们就按照默认的写法去做了这件事情，但是，发现报错了。
	 * 因为BufferedReader需要的是一个字符流，而你现在有的确实一个字节流。
	 * 假如能有一个流对象能够实现字节流转成字符流，我们就可以用了。
	 * 转换流：InputStreamReader
	 */

		public static void main(String[] args) throws IOException {
			/*//创建标准的键盘录入
			InputStream iStream=System.in;//打印的底层是字节输入流 BufferInputStream 字节流
			//转换流
			//创建转换流
			InputStreamReader isr=new InputStreamReader(iStream);//转换字节流
			//创建输入流对象，就是要读取的是键盘录入的流数据
			BufferedReader bReader=new BufferedReader(isr);
			//创建输出流对象，就是写入文档
			BufferedWriter bWriter=new BufferedWriter(new FileWriter("aa.txt"));
			//因为一行一行的读取数据的话会更加的高效
			String string=null;
			while((string=bReader.readLine())!=null)
			{
				//键盘在输入的时候应该自己制作一个结尾的
				if("over".equals(string))
				{
					break;
				}
				bWriter.write(string);
				bWriter.newLine();
				bWriter.flush();
			}
			bReader.close();
			bWriter.close();*/
			
//			InputStream iStream=System.in;
//			//转换流
//			//有字节流转换为字符流
//			InputStreamReader isr=new InputStreamReader(iStream);
//			//创建数据源对象
//			BufferedReader bReader=new BufferedReader(isr);
//			//创建目的地数据源对象
//			BufferedWriter bWriter=new BufferedWriter(new FileWriter("txt.txt"));
//			String string=null;
//			while((string=bReader.readLine())!=null)
//			{
//				bWriter.write(string);
//				bWriter.newLine();
//				bWriter.flush();
//			}
//			bWriter.close();
//			bReader.close();
			InputStream isInputStream=System.in;
			//转换流将字节流转换为字节流
			InputStreamReader isr=new InputStreamReader(isInputStream);
			//创建数据源对象
			BufferedReader bReader=new BufferedReader(isr);
			//创建目的di流对象
			BufferedWriter bwBufferedWriter=new BufferedWriter(new FileWriter("ee.txt"));
			String string=null;
			while((string=bReader.readLine())!=null)
			{
				if("over".equals(string))
				{
					break;
					
				}
				bwBufferedWriter.write(string);
				bwBufferedWriter.newLine();
				bwBufferedWriter.flush();
			}
			bwBufferedWriter.close();
			bReader.close();
		}
}
