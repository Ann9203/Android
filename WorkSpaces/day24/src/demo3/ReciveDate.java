package demo3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReciveDate extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		try {
			//�����ӿڶ���
			DatagramSocket dSocket=new DatagramSocket(10086);
			//��������
			byte[] by=new byte[1024];
			//��ȡ��Դ
			DatagramPacket dPacket=new DatagramPacket(by, by.length);
			//��������
			String dataString=new String(dPacket.getData(), 0, dPacket.getLength());
			//��ȡ������ip
			String addressString=dPacket.getAddress().getHostName();
			System.out.println("from"+addressString+"data"+dataString);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
