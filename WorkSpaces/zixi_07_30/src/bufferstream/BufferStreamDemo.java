package bufferstream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.shape.Line;

public class BufferStreamDemo {
	/*
	 * 
	 * BufferWriter��
	 * 		public void newLine():����ϵͳƽ̨д���зָ���
	 *BufferReader:
	 *		public String readLine();һ�ζ�ȡһ�е����ݣ����ǲ��������з�
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		//д����
//		BufferedWriter bWriter=new BufferedWriter(new FileWriter("u.txt"));
//		for (int i = 0; i < 5; i++) {
//			bWriter.write("hello"+i);
//			//����д���зָ���
//			bWriter.write("\r\n");
//			bWriter.newLine();
//			bWriter.flush();
//			
//		}
//		bWriter.close();
		//��ȡ�ļ�
		BufferedReader br=new BufferedReader(new FileReader("u.txt"));
		//�����Ļ�ֻ�ܶ�����������
//		String line = br.readLine();
//		System.out.println(line);
//		line = br.readLine();
//		System.out.println(line);
//		line = br.readLine();
//		System.out.println(line);
//		line = br.readLine();
//		System.out.println(line);
//		String line=null;
//		while ((line=br.readLine())!=null) {
//			System.out.println(line);
//			
//		}
		String line=null;
		while((line=br.readLine())!=null)
		{
			System.out.println(line);
		}
		
		br.close();
		
		
	}

}
