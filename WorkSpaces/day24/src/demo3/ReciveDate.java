package demo3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReciveDate extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		try {
			//创建接口对象
			DatagramSocket dSocket=new DatagramSocket(10086);
			//定义数组
			byte[] by=new byte[1024];
			//获取资源
			DatagramPacket dPacket=new DatagramPacket(by, by.length);
			//解析数据
			String dataString=new String(dPacket.getData(), 0, dPacket.getLength());
			//获取主机的ip
			String addressString=dPacket.getAddress().getHostName();
			System.out.println("from"+addressString+"data"+dataString);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
