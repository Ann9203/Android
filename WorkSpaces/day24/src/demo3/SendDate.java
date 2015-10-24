package demo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class SendDate extends Thread {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		//创建一个端口
		try {
			DatagramSocket dSocket=new DatagramSocket();
			//创建写入数据
			BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
			String  string=null;
			while((string=bReader.readLine())!=null)
			{
				if("over".equals(string))
				{
					break;
				}
				byte[] by=string.getBytes();
				//创建数据包
				DatagramPacket dPacket=new DatagramPacket(by, by.length, InetAddress.getByName("192.168.22.129"),10086);
				dSocket.send(dPacket);
			}
			dSocket.close();
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
