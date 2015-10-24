package day05_���߳�����;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpLoad {
	static String path = "http://192.168.56.1:8090/eula.1028.txt";
	static int threadCount = 3;
	static int finishedThread = 0;

	public static void main(String[] args) {

		try {
			URL url = new URL(path);
			HttpURLConnection open = (HttpURLConnection) url.openConnection();
			open.setReadTimeout(10 * 1000);
			open.setRequestMethod("GET");
			if (open.getResponseCode() == 200) {
				int length = open.getContentLength();
				// �����ļ��ĳ��ȴ�����ʱ�ļ�
				RandomAccessFile raf = new RandomAccessFile(getFileName(path),
						"rwd");
				// ������ʱ�ļ��Ĵ�С
				raf.setLength(length);
				raf.close();

				// ����ÿ���̵߳��ܳ���
				int len = length / threadCount;
				// ���㿪ʼ��λ��
				for (int d = 0; d < threadCount; d++) {
					// �����ȷ����ʼ������λ��

					int startIndex = d * len;
					int endIndex = (d + 1) * len - 1;
					if (d == threadCount - 1) {
						// �����ȷ������λ�ã���ʱ���һ���̵߳�ʱ��
						// ��ô����λ�þ��ǳ��ȼ�ȥ1
						endIndex = length - 1;

					}
					System.out.println("�߳�" + d + "���������䣺" + startIndex + "---"
							+ endIndex);
					// ȷ���꿪ʼλ�úͽ���λ��֮�󴴽��߳�
					new DownThread(startIndex, endIndex, d).start();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getFileName(String path) {
		// TODO Auto-generated method stub
		int index = path.lastIndexOf("/");
		String fileName = path.substring(index + 1, path.length());

		return fileName;
	}

}


class DownThread extends Thread
{
		private int startIndex;
		private int endIndex;
		private int threadId;

		public DownThread(int startIndex,int endIndex,int threadId)
		{
			this.startIndex=startIndex;
			this.endIndex=endIndex;
			this.threadId=threadId;
		}
		@Override
		public void run() {
			
			try{
					//����Ҫ������ʱ�ļ��Ƿ����
					//����һ��������صĳ���
				int lastLength=0;
				File fileProgress=new File(threadId+".txt");
				if(fileProgress.exists())
				{
					//����ļ����ڵĻ���Ҫ��ȡ��һ�����ص��Ľ���
					FileInputStream fis=new FileInputStream(fileProgress);
					//ʹ��BufferReader���ж�ȡ
					//��ʱ�ļ��д��ڵ�ֻ���Ǹ��ļ���ȡ���Ǹ��ֽڵ���ֵ������ʹ��bufferedһ�еĶ�ȡ��Ȼ��ֵ��lastLength
					BufferedReader br=new BufferedReader(new InputStreamReader(fis));
					//��ȡֱ��
					lastLength=Integer.parseInt(br.readLine());
					//ͬʱҪ����һ�¿�ʼλ�ã�����һ��������Ϻ�ʼ
					startIndex+=lastLength;
					fis.close();
				}
	
				URL url = new URL(UpLoad.path);
				HttpURLConnection open = (HttpURLConnection) url.openConnection();
				open.setReadTimeout(10 * 1000);
				open.setRequestMethod("GET");
				//��������Χ������
				open.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);
				//��Ϊ�Ƕ��߳����أ����Ծ���ÿ���̲߳������󳤶ȣ�������ʱ���״̬�뷵�صľ���206
				if(open.getResponseCode()==206)
				{
					//����ɹ�����ô���в������е����ݶ��ǲ��ֵ�����
					InputStream in = open.getInputStream();
					//��ȡ�еĳ���
					byte[] by=new byte[1024];
					int len=-1;
					int  totle=0;
					//��¼��ǰ�߳����ص��е��ֽ�
					totle=lastLength();
					//������ʱ�����ļ�
					RandomAccessFile raf=new RandomAccessFile(UpLoad.getFileName(UpLoad.path), "rwd");
					//�ı�д�����ݵĿ�ʼλ��
					raf.seek(startIndex);
					while((len=in.read(by))!=-1)
					{
						raf.write(by,0,len);
						//��ʱ��totle���б仯
						totle+=len;
						System.out.println("�߳�" + threadId + "������" + totle);
						//����һ�������ļ��������
						RandomAccessFile rafProgress=new RandomAccessFile(fileProgress, "rwd");
						//д�������
						rafProgress.write(String.valueOf(totle).getBytes());
						rafProgress.close();
					}
					raf.close();
					System.out.println("�߳�" + threadId + "�������---------------");
					//���һ���߳���ôUpLoad�еľͻ���һ���Ѿ���ɵ��߳�
					UpLoad.finishedThread++;
					synchronized (UpLoad.path) {
						if(UpLoad.finishedThread==3)
						{
							for(int i=0;i<UpLoad.finishedThread;i++)
							{
								File file=new File(i+".txt");
								file.delete();
							}
						}
					}
					
					
					
				}
				
				
			}catch(Exception e){e.printStackTrace();}

			
			
			
			

		}
		private int lastLength() {
			// TODO Auto-generated method stub
			return 0;
		}
	
}



























