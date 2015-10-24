
package demo2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamDemo {
	/*
	 * ���ʱ������ӵ�е��Ǳ�׼����¼�롣
	 * ���ֽڵ������������ֶ�ȡ��ʽ��
	 * ��ʽ1��һ�ζ�ȡһ���ֽڣ����ǣ������Ļ�������ÿ�ζ���Ҫ�Զ�ȡ�������ݽ��б��棬�����ж��Ƿ�һ�н������鷳��
	 * ��ʽ2��һ�ζ�ȡһ���ֽ����飬���ǣ����ʱ�����������ǣ�����ĳ��ȶ���Ϊ��󲻺�ȷ����
	 * ���ԣ����Ǿ��������ַ�ʽ�������á�
	 * ���Ǿ�����û��������һ�ַ�����ֱ��һ�ζ�ȡһ�����ݡ�
	 * ���Ǿ��뵽���ַ�������������:readLine()������
	 * ���ǾͰ���Ĭ�ϵ�д��ȥ����������飬���ǣ����ֱ����ˡ�
	 * ��ΪBufferedReader��Ҫ����һ���ַ��������������е�ȷʵһ���ֽ�����
	 * ��������һ���������ܹ�ʵ���ֽ���ת���ַ��������ǾͿ������ˡ�
	 * ת������InputStreamReader
	 */

		public static void main(String[] args) throws IOException {
			/*//������׼�ļ���¼��
			InputStream iStream=System.in;//��ӡ�ĵײ����ֽ������� BufferInputStream �ֽ���
			//ת����
			//����ת����
			InputStreamReader isr=new InputStreamReader(iStream);//ת���ֽ���
			//�������������󣬾���Ҫ��ȡ���Ǽ���¼���������
			BufferedReader bReader=new BufferedReader(isr);
			//������������󣬾���д���ĵ�
			BufferedWriter bWriter=new BufferedWriter(new FileWriter("aa.txt"));
			//��Ϊһ��һ�еĶ�ȡ���ݵĻ�����ӵĸ�Ч
			String string=null;
			while((string=bReader.readLine())!=null)
			{
				//�����������ʱ��Ӧ���Լ�����һ����β��
				if("over".equals(string))
				{
					break;
				}
				bWriter.write(string);
				bWriter.newLine();
				bWriter.flush();
			}
			bReader.close();
			bWriter.close();*/
			
//			InputStream iStream=System.in;
//			//ת����
//			//���ֽ���ת��Ϊ�ַ���
//			InputStreamReader isr=new InputStreamReader(iStream);
//			//��������Դ����
//			BufferedReader bReader=new BufferedReader(isr);
//			//����Ŀ�ĵ�����Դ����
//			BufferedWriter bWriter=new BufferedWriter(new FileWriter("txt.txt"));
//			String string=null;
//			while((string=bReader.readLine())!=null)
//			{
//				bWriter.write(string);
//				bWriter.newLine();
//				bWriter.flush();
//			}
//			bWriter.close();
//			bReader.close();
			InputStream isInputStream=System.in;
			//ת�������ֽ���ת��Ϊ�ֽ���
			InputStreamReader isr=new InputStreamReader(isInputStream);
			//��������Դ����
			BufferedReader bReader=new BufferedReader(isr);
			//����Ŀ��di������
			BufferedWriter bwBufferedWriter=new BufferedWriter(new FileWriter("ee.txt"));
			String string=null;
			while((string=bReader.readLine())!=null)
			{
				if("over".equals(string))
				{
					break;
					
				}
				bwBufferedWriter.write(string);
				bwBufferedWriter.newLine();
				bwBufferedWriter.flush();
			}
			bwBufferedWriter.close();
			bReader.close();
		}
}
