package day20lianxi;
import java.io.FileReader;
import java.io.IOException;
public class FileReaderHight {
	/*
	 * 高效的读取方式
	 * read(char[]),就是以数组为单位进行数据的读取，读取的数据放在数组中，然后在输出
	 * 这个方法返回的int型是这个读取的数组的长度
	 * */
	public static void main(String[] args) throws IOException {
		FileReader rd=new FileReader("Z:\\aa.txt");
		//定义一个数组,长度为1024的倍数,
//		char[] ch=new char[1024];
//		int leng=0;
//		while((leng=rd.read(ch))!=-1)
//		{
//			//输出数组
//			//System.out.print(rd.read(ch, 0, leng));
//			//把字节装入到字符数组中，打印字符数组通过String的构造方法来打印
//			System.out.println(new String(ch, 0, leng));
//		}
//		rd.close();
		char[] ch=new char[1024];
		int length=0;
		while((length=rd.read(ch))!=-1)
		{
			System.out.println(new String(ch, 0, length));
		}
		rd.close();
	}

}
