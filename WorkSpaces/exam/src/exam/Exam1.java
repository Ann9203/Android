package exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Exam1 {
	/*
	 * ���ı��ж�ȡ�ڿ���̨��ʾ
	 *    ˼·��
	 *    		Ҫ��һ����׼�Ŀ���̨�����
	 *    		B:һ��һ�еĶ�ȡ�Ļ���Ҫת��
	 *    		Ȼ��д�뵽�ı���ȥ
	 * */
	public static void main(String[] args) throws IOException {
		/*//����һ����׼�Ŀ���̨������
		System.out.println("���������֣�");
		InputStream isr=System.in;
		//����̨���������ֽ��͵ģ���ȡ�ļ���Ҫ�ֽ�ת�����ַ���׼����
		InputStreamReader iStreamReader=new InputStreamReader(isr);
		//��������Դ����
		BufferedReader bReader=new BufferedReader(iStreamReader);
		//����Ŀ�ĵص�������
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("nn.txt"));
		//����һ���ַ���
		String string =null;
		while((string=bReader.readLine())!=null)
		{
			if("over".equals(string))
			{
				break;
			}
			bWriter.write(string);
			bWriter.flush();
		}
		bWriter.close();
		bReader.close();*/
		
		//������Ǵ��ı��ж�ȡ���ݣ�Ȼ����ʾ�ڿ���̨��
		BufferedReader bufferedReader=null;
		try {
			bufferedReader=new BufferedReader(new FileReader("t.txt"));
			String string=null;
			while((string=bufferedReader.readLine())!=null)
			{
				System.out.println(string);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();}
		finally
		
		{
			{if(bufferedReader!=null)
				try {
					bufferedReader.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			
			
		}
		
		
		
	}

}
