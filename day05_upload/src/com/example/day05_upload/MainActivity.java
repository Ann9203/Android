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
	//��ǰ������
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
					//��������
				try{
					URL url = new URL(path);
					HttpURLConnection openConnection = (HttpURLConnection)url.openConnection();
					openConnection.setReadTimeout(8*1000);
					openConnection.setRequestMethod("GET");
					if(openConnection.getResponseCode()==200)
					{
						int length = openConnection.getContentLength();
						//������ʱ�ļ�������ʱ�ļ��ĳ���
//						File file=new File("sdcard/"+getFileName(path));
						RandomAccessFile raf=new RandomAccessFile(new File("sdcard/"+getFileName(path)), "rwd");
						//������ʱ�ļ�����ʱ����ļ��ĳ���
						raf.setLength(length);
						//�ر���ʱ�ļ�
						raf.close();
						//���ý���������󳤶�ΪԴ�ļ��ĳ���
						pb.setMax(length);
						
						
						//����ÿ���߳�Ӧ��Ҫ��������
						int len=length/threadCount;
						//����������ʱ�ļ���ʱ���Ҫforѭ�������߳�
						for(int i=0;i<threadCount;i++)
						{
							//���õ�һ��λ��
							int startIndex=i*len;
							//����ĩβ��λ��
							int endIndex=(i+1)*len-1;
							if(i==threadCount-1)
							{
								endIndex=length-1;
							}
							//���Ǻ��Ҫ����ûһ���߳���
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
			//����һ��������صĳ���
			try{
				int lastLength=0;
				//���ȴ���һ���߳����ص���ʱ�ļ���ר�Ŵ���߳������˶���
				File file=new File("sdcard/"+threadId+".txt");
				//�鿴���ļ��Ƿ����
				
				if(file.exists())
				{
					//Ȼ���ȥ���е�����
					FileInputStream fis=new FileInputStream(file);
			
					//��ȡ�������ݵ��ֽ�����
					//ʹ��Buffer��Ƚ��ж�ȡ
					BufferedReader br=new BufferedReader(new InputStreamReader(fis));
					//���صĳ��Ƚ���Ϊ�ϴζϵ㣬��ο�ʼ��λ��
					lastLength=Integer.parseInt(br.readLine());
					startIndex+=lastLength;
					fis.close();
					//��֮ǰ������Ҳ�ӵ���������
					currentProgress+=lastLength;
					pb.setProgress(currentProgress);
					//���Է���һ������Ϣ
					handler.sendEmptyMessage(0);
				}
				
				
				//��������
				URL url = new URL(path);
				HttpURLConnection openConnection = (HttpURLConnection)url.openConnection();
				openConnection.setReadTimeout(8*1000);
				openConnection.setRequestMethod("GET");
				//������������
				openConnection.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);
				//�ֶε��߳��Ƿֶ������
				if(openConnection.getResponseCode()==206)
				{
					//��ȡ��
					InputStream inputStream = openConnection.getInputStream();
					byte[] by=new byte[1024];
					int len=-1;
					//��¼��ǰ�߳������е��ֽ�
					int total=0;
					total=lastLength;
					//���������ļ����ݵ���ʱ�ļ�
					RandomAccessFile raf=new RandomAccessFile(new File("sdcard/"+getFileName(path)), "rwd");
					//���������ʱ�ļ��е���ʵλ���ǵڼ����ַ�
					raf.seek(startIndex);
					while((len=inputStream.read(by))!=-1)
					{
						raf.write(by,0,len);
						total+=len;
						//��ʱ��Ҫ���ɷ��̵߳���ʱ�ļ� ΪʲôҪ��������߶����������
						RandomAccessFile rafProgress=new RandomAccessFile(file, "rwd");
						//���ܳ���д��
						rafProgress.write(String.valueOf(total).getBytes());
						rafProgress.close();
						//����һ�����������ڱ������
						currentProgress+=len;
						pb.setProgress(currentProgress);
						//����һ������Ϣ������û����Ϣ��Ҫ����
						handler.sendEmptyMessage(0);
						
					}
					raf.close();
					finishedThread++;
					//���̶߳�Ҫ������ϵ�ʱ���Ҫɾ���߳�
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
