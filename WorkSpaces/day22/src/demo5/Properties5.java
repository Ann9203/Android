package demo5;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Properties5 {
	/*
	 * 吧集合中的数据保存到文本中
	 * 用的方法是store
	 * */
public static void main(String[] args) throws IOException {
//	Properties properties=new Properties();
//	properties.setProperty("李雪", "22");
//	properties.setProperty("李许", "23");
//	//创建目的地
//	FileWriter fwFileWriter=new FileWriter("ZZ.txt");
//	//把集合中的数据保存到文本中，同时传入两个数据的共同性
//	//properties.save(fwFileWriter, "love me");
//	properties.store(fwFileWriter, "love");
//	fwFileWriter.close();
	
	Properties properties=new Properties();
	properties.setProperty("天天", "3");
	properties.setProperty("小红", "4");
	properties.setProperty("小明", "6");
	properties.setProperty("小云", "8");
	
	//创建数据源的目的地
	FileWriter fWriter=new FileWriter("p.txt");
	properties.store(fWriter, "");
	fWriter.close();
	
}
}
