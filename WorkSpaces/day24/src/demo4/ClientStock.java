package demo4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStock {
	/*
	 * �ͻ��˸��������ݣ�Ȼ���÷���
	 * ���裺
	 * 	A:�����������˿ڡ�
	 * 	B: ����¼��
	 * 	C:д������  ͬ���ܵ�д������
	 * 	D:���ܷ�����ͨ���ܵ����ܷ���
 	 * 	E:��ȡ����
	 * */
	public static void main(String[] args) throws UnknownHostException, IOException {
		//�����ͻ��˿�
		//ServerSocket ss=new ServerSocket("192.168.22.129",80086);
		Socket socket=new Socket("192.168.22.30", 12306);
		//����¼������
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
		//���̻�ȡ���ݣ�ͨ���ܵ���ȡ����
		BufferedWriter bWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		//д������
		String lineString=null;
		while((lineString=bReader.readLine())!=null)
		{
			if("over".equals(lineString))
			{
				break;
				
			}
			bWriter.write(lineString);
			bWriter.newLine();
			bWriter.flush();
		}
		bWriter.close();
		//д�������ݣ��õ�����
		BufferedReader bReader2=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String ling= bReader.readLine();
		System.out.println("server"+ling);
		bReader.close();
		bReader2.close();
		
	}

}
