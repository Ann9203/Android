package demo5;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
public class ProperLoad {
	/*
	 * 把文本中的数据加载到集合中去
	 * 		load
	 * 前提：文本中的数据必须是以键值存在的关系
	 * */
	public static void main(String[] args) throws IOException {
//		Properties properties=new Properties();
//		//创建目的地流的额对象
//		FileReader fileReader=new FileReader("Z:\\ccc.txt");
//		properties.load(fileReader);
//		fileReader.close();
//		Set<String>key=properties.stringPropertyNames();
//		for (String string : key) {
//			String vlue=properties.getProperty(string);
//			System.out.println(string+"***********"+vlue);
//			
//		}
		Properties properties=new Properties();
		FileReader fd=new FileReader("Z:\\ccc.txt");
		properties.load(fd);
		fd.close();
		Set<String> key=properties.stringPropertyNames();
		for (String string : key) {
			String value=properties.getProperty(string);
			System.out.println(string+"*********"+value);
		}
		
	}

}
