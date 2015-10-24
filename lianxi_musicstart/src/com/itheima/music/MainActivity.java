package com.itheima.music;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	private static SeekBar sb;
	private IMusic im;
	private static int i=4;
	static Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			//读取传过来的数据
			Bundle bundle = msg.getData();
			//读取中的长度
			int sumTime=(Integer) bundle.get("sumTime");
			//读取当前时间
			int currentTime=(Integer) bundle.get("currentTime");
			//吧当前时间和中的长度赋值给seekBar
			sb.setProgress(currentTime);
			sb.setMax(sumTime);
		};
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取seekBar的id
		sb=(SeekBar)findViewById(R.id.sb_music);
		//sb的拖动事件
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				im.seekProgress(progress);
			}
		});
		//获取服务
		Intent intent=new Intent(this,MusicServers.class);
		startService(intent);
		bindService(intent, new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				im=(IMusic) service;
			}
		}, BIND_AUTO_CREATE);
	}
	public void play(View v)
	{
		if(i % 3==1)
		{
			im.start();
			i++;
		}else if(i % 3==2)
		{
			im.stop();
			i++;
		}else if(i % 3==0)
		{
			im.continues();
			i++;
		}
		
	}
	public void start(View v)
	{
		im.start();
	}
	public void stop(View v)
	{
		im.stop();
	}
	public void continues(View v)
	{
		im.continues();
	}

}
