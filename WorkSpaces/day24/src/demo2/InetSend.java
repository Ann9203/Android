package demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class InetSend {
	/*
	 * ����ͨѶ������֮��ı��
	 *	˼·:
	 *		A: ����˿�
	 *		B: ��������涨���ݵ����ĳ���
	 *		C���������
	 *		D: ����
	 *		E: �ͷ���Դ
	 * */
	public static void main(String[] args) throws IOException {
		//�������Ͷ˵ķ�����������������з�������
		DatagramSocket ds=new DatagramSocket();
		//InetAddressû�й��췽��
		//Scanner scanner=new Scanner(System.in);
		//��ȡ��Դ
		BufferedReader bReader=new BufferedReader(new  InputStreamReader(System.in));
		String string=null;
		while((string=bReader.readLine())!=null)
		{
			if("886".equals(string))
			{
				break;
			}
			InetAddress inetAddress=InetAddress.getByName("192.168.22.129");
			byte[] by=string.getBytes();
			
			//�������ݰ�
			DatagramPacket daPacket=new DatagramPacket(by, by.length, inetAddress, 12345);
			ds.send(daPacket);
		}
		//�ͷ���Դ
		
		ds.close();
		
	}

}
