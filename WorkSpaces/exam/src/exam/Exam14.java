package exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Exam14 {
	/*
	 * ����һ���ļ�������������read(byte[] b)������
	 * exercise.txt�ļ��е��������ݴ�ӡ����(byte����Ĵ�С����Ϊ5)��
	 * */
	public static void main(String[] args) throws FileNotFoundException {
		//�����ļ�������
		BufferedInputStream bInputStream=new BufferedInputStream(new FileInputStream("Z:\\ccc.txt"));
		//����һ��byte����
		byte[] by=new byte[5];
		int leng=0;
		try {
			while((leng=bInputStream.read(by))!=-1)
			{
				System.out.print(new String(by,0,leng));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
