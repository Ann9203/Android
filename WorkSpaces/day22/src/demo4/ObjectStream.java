package demo4;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ObjectStream {
	/*
	 * 序列化：把对象按照流一样的方式传输或者存储
	 * 反序列化：吧网络中的流数据或者文件中的流数据还原成对象读取数据
	 * 吧对象存储到文本文件中：
	 * 		ObjectInputStream：  反序列化
	 * 			Object readObject();读取数据
	 * 		ObjectOutputStream： 序列化
	 * 			void WriteObject（object obj）
	 * */
	public static void main(String[] args) throws IOException, IOException, ClassNotFoundException {
		//创建序列化也就是写入Object对象
			//ObjectOutputStream（Object obj）
		//乱码
//		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("tt.txt"));
//		oos.writeObject(new Person("周杰伦",34));
//		oos.flush();
//		oos.close();
		//反序列话就是读取数据
//		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("t.txt"));
//		Object object=ois.readObject();
//		Person person=(Person)object;
//		System.out.println(person.getName()+"********"+person.getAge());
//		ois.close();
		//序列化
		ObjectOutputStream sos=new ObjectOutputStream(new FileOutputStream("p.txt"));
		sos.writeObject(new Person("snow",24));
		sos.close();
		
		//反序列话就是通过流来读取对象
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("p.txt"));
		Person person=(Person)ois.readObject();
		System.out.println(person.getName()+"********"+person.getAge());
		ois.close();
		
	}

}
