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
	 * ���л����Ѷ�������һ���ķ�ʽ������ߴ洢
	 * �����л����������е������ݻ����ļ��е������ݻ�ԭ�ɶ����ȡ����
	 * �ɶ���洢���ı��ļ��У�
	 * 		ObjectInputStream��  �����л�
	 * 			Object readObject();��ȡ����
	 * 		ObjectOutputStream�� ���л�
	 * 			void WriteObject��object obj��
	 * */
	public static void main(String[] args) throws IOException, IOException, ClassNotFoundException {
		//�������л�Ҳ����д��Object����
			//ObjectOutputStream��Object obj��
		//����
//		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("tt.txt"));
//		oos.writeObject(new Person("�ܽ���",34));
//		oos.flush();
//		oos.close();
		//�����л����Ƕ�ȡ����
//		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("t.txt"));
//		Object object=ois.readObject();
//		Person person=(Person)object;
//		System.out.println(person.getName()+"********"+person.getAge());
//		ois.close();
		//���л�
		ObjectOutputStream sos=new ObjectOutputStream(new FileOutputStream("p.txt"));
		sos.writeObject(new Person("snow",24));
		sos.close();
		
		//�����л�����ͨ��������ȡ����
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("p.txt"));
		Person person=(Person)ois.readObject();
		System.out.println(person.getName()+"********"+person.getAge());
		ois.close();
		
	}

}
