package com.example.day04_code;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private EditText et_url;
	private TextView tv_code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ȡÿ���ռ��idֵ
		et_url=(EditText)findViewById(R.id.et_urll);
		Button bt_download=(Button)findViewById(R.id.bt_download);
		 tv_code=(TextView)findViewById(R.id.tv_code);
		
		//�����ʱ��
		bt_download.setOnClickListener(this);
		
	}
	//ע�����̲߳����Դ����ʱ�ĳ���
	//���̲߳����Զ�UI������и���
	Handler handler=new Handler(){
		//��д����
		public void handleMessage(android.os.Message msg)
		{
			//Ҫ��ȡmessage
			String result=(String) msg.obj;
			//չʾ��ǰ̨����
			tv_code.setText(result);
			
		}
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//���õ���¼�
		//��ȡurl
		final String path=et_url.getText().toString().trim();
		//����URL���� 
		//���߳��в������к�ʱ�Ķ�����ҪҪ������߳�
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					URL url = new URL(path);
					//����HttpURLConnection 
					HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
					//����������
					openConnection.setRequestMethod("GET");
					//���ó�ʱʱ��
					openConnection.setConnectTimeout(10*1000);
					//����״̬��
					if(openConnection.getResponseCode()==200)
					{
						InputStream in = openConnection.getInputStream();
						//����Ҫ����ת�����ַ���
						String result=StreamToString.changToString(in);
						//����һ��Message����
						System.out.println();
						Message msg = new Message();
						//��ȡ����
						msg.obj=result;
						//���Ͷ���
						handler.sendMessage(msg);
						in.close();
					}
					
					//��ȡ������
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      			}
			
			
			
		}).start();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
