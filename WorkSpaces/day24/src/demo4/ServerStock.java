package demo4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStock {
	/*
	 * ��������������
	 * */
	public static void main(String[] args) throws IOException {
		
		//����˿�
		ServerSocket ss=new ServerSocket(12306);
		Socket socke=ss.accept();
		//�������ݽ��ж�ȡ
		BufferedWriter bWriter=new BufferedWriter(new OutputStreamWriter(socke.getOutputStream()));
		BufferedReader bReader=new BufferedReader(new InputStreamReader(socke.getInputStream()));
		String line=null;
		while((line=bReader.readLine())!=null)
		{
			bWriter.write(line.toUpperCase());
			bWriter.newLine();
			bWriter.flush();
		}
		//��������
		System.out.println("���ݳɹ�����");
		
	
	}

}
