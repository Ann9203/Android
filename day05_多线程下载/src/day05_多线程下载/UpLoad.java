package day05_多线程下载;

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
				// 根据文件的长度创建临时文件
				RandomAccessFile raf = new RandomAccessFile(getFileName(path),
						"rwd");
				// 设置临时文件的大小
				raf.setLength(length);
				raf.close();

				// 计算每个线程的总长度
				int len = length / threadCount;
				// 计算开始的位置
				for (int d = 0; d < threadCount; d++) {
					// 这个是确定开始的索引位置

					int startIndex = d * len;
					int endIndex = (d + 1) * len - 1;
					if (d == threadCount - 1) {
						// 这个是确定最后的位置，当时最后一个线程的时候，
						// 那么最后的位置就是长度减去1
						endIndex = length - 1;

					}
					System.out.println("线程" + d + "的下载区间：" + startIndex + "---"
							+ endIndex);
					// 确定完开始位置和结束位置之后创建线程
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
					//首先要看看临时文件是否存在
					//设置一个最后下载的长度
				int lastLength=0;
				File fileProgress=new File(threadId+".txt");
				if(fileProgress.exists())
				{
					//如果文件存在的话就要获取上一次下载到的进度
					FileInputStream fis=new FileInputStream(fileProgress);
					//使用BufferReader进行读取
					//临时文件中存在的只是那个文件读取到那个字节的数值，所以使用buffered一行的读取，然后赋值给lastLength
					BufferedReader br=new BufferedReader(new InputStreamReader(fis));
					//获取直接
					lastLength=Integer.parseInt(br.readLine());
					//同时要设置一下开始位置，冲上一次下载完毕后开始
					startIndex+=lastLength;
					fis.close();
				}
	
				URL url = new URL(UpLoad.path);
				HttpURLConnection open = (HttpURLConnection) url.openConnection();
				open.setReadTimeout(10 * 1000);
				open.setRequestMethod("GET");
				//设置请求范围的数据
				open.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);
				//因为是多线程下载，所以就是每个线程部分请求长度，而且这时候的状态码返回的就是206
				if(open.getResponseCode()==206)
				{
					//请求成功，那么流中不是所有的数据而是部分的数据
					InputStream in = open.getInputStream();
					//获取中的长度
					byte[] by=new byte[1024];
					int len=-1;
					int  totle=0;
					//记录当前线程下载的中的字节
					totle=lastLength();
					//设置临时缓存文件
					RandomAccessFile raf=new RandomAccessFile(UpLoad.getFileName(UpLoad.path), "rwd");
					//改变写入数据的开始位置
					raf.seek(startIndex);
					while((len=in.read(by))!=-1)
					{
						raf.write(by,0,len);
						//此时的totle会有变化
						totle+=len;
						System.out.println("线程" + threadId + "下载了" + totle);
						//生成一个缓存文件保存进度
						RandomAccessFile rafProgress=new RandomAccessFile(fileProgress, "rwd");
						//写入进度中
						rafProgress.write(String.valueOf(totle).getBytes());
						rafProgress.close();
					}
					raf.close();
					System.out.println("线程" + threadId + "下载完毕---------------");
					//完成一个线程那么UpLoad中的就会有一个已经完成的线程
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



























