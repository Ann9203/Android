package reader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyDemo {
	public static void main(String[] args) throws IOException {
		/*
		 * 
		 * //���D������һ���ļ�a.txt, ������������Ƶ�E����,���Ҹ���copy.txtΪ��������?
		 * ���裺
		 * 		A:�Ȱ��ļ�������
		 * 			read(char ch)//�������˼���ǰѶ������Ķ������뵽chr��������
		 * 			char[] ch=new char[6]//�����˼����˵����6��6���ֽڵĶ���Ȼ����뵽chr������
		 * 		B���ļ���������Ȼ��ֱ����д�뵽ָ����Ŀ¼��txt�ı���
		 * */
		//����һ�������ļ�
		FileReader fd=new FileReader("Z:\\aa.txt");
		//����һ��Ҫд�ġ�txt�ļ�
		FileWriter fw=new FileWriter("X:\\c.txt");
		//����һ���ַ����飬������1024��������
		char[] ch=new char[1024];
		int length=0;
		//һ�¿��Լ�д
		//length=fd.read
		//while(length!=-1	)
		//�����ֶ��뵽������
		
		 // fd.read(ch);
		while((length=fd.read(ch))!=-1)
		{
			//д������			
			fw.write(ch,0,length);
			//fd.read(ch);
		}
		fd.close();
		fw.close();
		
		
		
	}

}
