package com.example.day05_upload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	final String path="http://192.168.56.1:8090/eula.1028.txt";
	int finishedThread=0;
	final int threadCount=3;
	private ProgressBar pb;
	private TextView tv_progress;
	//当前进度条
	int currentProgress=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button bt_download=(Button)findViewById(R.id.bt_download);
		bt_download.setOnClickListener(MainActivity.this);
		pb=(ProgressBar)findViewById(R.id.probar);
		tv_progress=(TextView)findViewById(R.id.tv_progress);
		
		
	}
	Handler handler= new Handler(){
		public void handleMessage(android.os.Message msg) {
			tv_progress.setText((long)pb.getProgress()*100 /pb.getMax() +"%");
			
		};
		
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	
		
		new Thread(new Runnable(){
			@Override
			public void run() {
					//请求网络
				try{
					URL url = new URL(path);
					HttpURLConnection openConnection = (HttpURLConnection)url.openConnection();
					openConnection.setReadTimeout(8*1000);
					openConnection.setRequestMethod("GET");
					if(openConnection.getResponseCode()==200)
					{
						int length = openConnection.getContentLength();
						//设置临时文件创建临时文件的长度
//						File file=new File("sdcard/"+getFileName(path));
						RandomAccessFile raf=new RandomAccessFile(new File("sdcard/"+getFileName(path)), "rwd");
						//设置临时文件创建时候的文件的长度
						raf.setLength(length);
						//关闭临时文件
						raf.close();
						//设置进度条的最大长度为源文件的长度
						pb.setMax(length);
						
						
						//设置每个线程应该要做的事情
						int len=length/threadCount;
						//创建完了临时文件这时候就要for循环三个线程
						for(int i=0;i<threadCount;i++)
						{
							//设置第一个位置
							int startIndex=i*len;
							//设置末尾的位置
							int endIndex=(i+1)*len-1;
							if(i==threadCount-1)
							{
								endIndex=length-1;
							}
							//这是后就要调用没一个线程了
							new DownLoad(startIndex,endIndex,i).start();
							
						}
					}
					
					
				}catch(Exception e){e.printStackTrace();}
				
				
			}
			
		}).start();
		
	}
	public final String getFileName(String path)
	{
		int len = path.lastIndexOf("/");
		String fileName = path.substring(len+1, path.length());
		return fileName;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	class DownLoad extends Thread
	{
		private int startIndex;
		private int endIndex;
		private int threadId;
		public DownLoad(int startIndex,int endIndex,int threadId)
		{
			this.startIndex=startIndex;
			this.endIndex=endIndex;
			this.threadId=threadId;
			
		}
		@Override
		public void run() {
			//设置一个最后下载的长度
			try{
				int lastLength=0;
				//首先创建一个线程下载的临时文件，专门存放线程下载了多少
				File file=new File("sdcard/"+threadId+".txt");
				//查看此文件是否存在
				
				if(file.exists())
				{
					//然后后去流中的数据
					FileInputStream fis=new FileInputStream(file);
			
					//获取流中数据的字节数量
					//使用Buffer额度进行读取
					BufferedReader br=new BufferedReader(new InputStreamReader(fis));
					//返回的长度将作为上次断点，这次开始的位置
					lastLength=Integer.parseInt(br.readLine());
					startIndex+=lastLength;
					fis.close();
					//把之前的下载也加到进度条中
					currentProgress+=lastLength;
					pb.setProgress(currentProgress);
					//可以发送一个空消息
					handler.sendEmptyMessage(0);
				}
				
				
				//请求网络
				URL url = new URL(path);
				HttpURLConnection openConnection = (HttpURLConnection)url.openConnection();
				openConnection.setReadTimeout(8*1000);
				openConnection.setRequestMethod("GET");
				//设置请求区域
				openConnection.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);
				//分段的线程是分段请求的
				if(openConnection.getResponseCode()==206)
				{
					//获取流
					InputStream inputStream = openConnection.getInputStream();
					byte[] by=new byte[1024];
					int len=-1;
					//记录当前线程下载中的字节
					int total=0;
					total=lastLength;
					//创建保存文件数据的临时文件
					RandomAccessFile raf=new RandomAccessFile(new File("sdcard/"+getFileName(path)), "rwd");
					//设置这个临时文件中的其实位置是第几个字符
					raf.seek(startIndex);
					while((len=inputStream.read(by))!=-1)
					{
						raf.write(by,0,len);
						total+=len;
						//这时候要生成分线程的临时文件 为什么要创建在里边而不是外边呢
						RandomAccessFile rafProgress=new RandomAccessFile(file, "rwd");
						//将总长度写入
						rafProgress.write(String.valueOf(total).getBytes());
						rafProgress.close();
						//设置一个进度条用于保存进度
						currentProgress+=len;
						pb.setProgress(currentProgress);
						//发送一个空消息，就是没有消息需要处理
						handler.sendEmptyMessage(0);
						
					}
					raf.close();
					finishedThread++;
					//当线程都要下载完毕的时候就要删除线程
					if(finishedThread==threadCount)
					{
						for(int i=0;i<threadCount;i++)
						{
							File filename=new File("sdcard/"+i+".txt");
							filename.delete();
						}
						finishedThread=0;
					}
				}
				
			}catch(Exception e){e.printStackTrace();}
			
		}
	}
}
