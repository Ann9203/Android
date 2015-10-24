package demo5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;

public class Properties6 {
	/*
	 * 需求：请查找文件user.txt中是否有lisi这个键，如果有，则修改其值为50
	 * 思路：
	 * 		A:显示把文本中的数据读出来，然后存入到集合中
	 * 		B:遍历集合如果找到的话就进行修改
	 * 		C:修改完了之后就再返回文本中重新保存
	 * */
	public static void main(String[] args) throws IOException {
		//method1();
		method2();
		
	}
	public static void method1() throws IOException
	{
		//创建数据源对象
		FileReader fReader=new FileReader("p.txt");
		//创建集合
//		Properties properties=new Properties();
//		properties.load(fReader);
//		Set<String> keySet=properties.stringPropertyNames();
//		for (String string : keySet) {
//			//如果存在的话，就修改元素
//			if("8".equals(properties.getProperty(string)))
//			{
//				//修改元素
//				properties.setProperty(string, "45");
//			}
//		}
//		//修改完元素之后，还要把元素存入到流文件中
//		//FileWriter fWrit=new FileWriter("p.txt");
//		//使用list方法存入奥文本中去
//		PrintWriter pWriter=new PrintWriter("p.txt");
//		properties.list(pWriter);
//		pWriter.close();
		
		//创建源数据流对象
		FileReader frFileReader=new FileReader("p.txt");
		//创建几何对象
		Properties properties=new Properties();
		//把文本数据存入到集合中load就是下载意思，也就是把集合中的内容下载下来
		properties.load(frFileReader);
		if(properties.containsKey("云云"))
		{
			properties.setProperty("云云", "50");
		}
//		Set<String>keySet=properties.stringPropertyNames();
//		for (String string : keySet) {
//			if("8".equals(properties.getProperty(string)))
//			{
//				//修改元素
//				properties.setProperty(string, "45");
//			}
//		}
		//修改完数据之后，存入文本中
		PrintWriter pWriter=new PrintWriter("p.txt");
		//list就是把集合的内容返回给文本
		properties.list(pWriter);
		pWriter.close();
		fReader.close();
		
	}
	//方法二，存入文本的另一个方法store
	public static void method2() throws IOException
	{
		//创建数据源流对象
		FileReader fReader=new FileReader("p.txt");
		//创建集合对象
		Properties properties=new Properties();
		//吧文本中的内容读入到集合中
		properties.load(fReader);
		//遍历集合
		Set<String>key=properties.stringPropertyNames();
		for (String string : key) {
			if("4".equals(properties.getProperty(string)))
			{
				properties.setProperty(string, "23");
			}
		}
		//修改后的文本写入到集合中
		FileWriter fwFileWriter=new FileWriter("p.txt");
		properties.store(fwFileWriter, "小孩子");
		fwFileWriter.close();
	}
	

}
