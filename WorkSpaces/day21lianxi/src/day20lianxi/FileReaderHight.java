package day20lianxi;
import java.io.FileReader;
import java.io.IOException;
public class FileReaderHight {
	/*
	 * ��Ч�Ķ�ȡ��ʽ
	 * read(char[]),����������Ϊ��λ�������ݵĶ�ȡ����ȡ�����ݷ��������У�Ȼ�������
	 * ����������ص�int���������ȡ������ĳ���
	 * */
	public static void main(String[] args) throws IOException {
		FileReader rd=new FileReader("Z:\\aa.txt");
		//����һ������,����Ϊ1024�ı���,
//		char[] ch=new char[1024];
//		int leng=0;
//		while((leng=rd.read(ch))!=-1)
//		{
//			//�������
//			//System.out.print(rd.read(ch, 0, leng));
//			//���ֽ�װ�뵽�ַ������У���ӡ�ַ�����ͨ��String�Ĺ��췽������ӡ
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
