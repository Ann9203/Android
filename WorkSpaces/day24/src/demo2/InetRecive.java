package demo2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class InetRecive {
	public static void main(String[] args) throws IOException {
		//����һ���˿�
			DatagramSocket dSocket=new DatagramSocket(12306);
			
			while(true)
			{
				byte[]by=new byte[1024];
				DatagramPacket dPacket=new DatagramPacket(by, by.length);
				dSocket.receive(dPacket);
				
				//��ȡ�������ݰ�֮��Ҫ���н�������
				//��ȡIP 
				String ip=dPacket.getAddress().getHostName();
				//��ȡ����
				String dateString=new String(dPacket.getData(),0,dPacket.getLength());
				System.out.println("it's coming from"+ip+",it's content"+dateString);
				//dSocket.close();
			}
	
			
		
	}

}
