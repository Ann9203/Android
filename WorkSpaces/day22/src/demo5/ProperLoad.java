package demo5;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
public class ProperLoad {
	/*
	 * ���ı��е����ݼ��ص�������ȥ
	 * 		load
	 * ǰ�᣺�ı��е����ݱ������Լ�ֵ���ڵĹ�ϵ
	 * */
	public static void main(String[] args) throws IOException {
//		Properties properties=new Properties();
//		//����Ŀ�ĵ����Ķ����
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
