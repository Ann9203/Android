package com.itheima.music;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

public class MusicServers extends Service {

	private Timer timer;
	private MediaPlayer mp;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new MusicPlayer();
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mp=new MediaPlayer();
		
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(timer!=null)
		{
			timer.cancel();
			timer=null;
		}
	}
	
	class MusicPlayer extends Binder implements IMusic
	{

		@Override
		public void start() {
			// TODO Auto-generated method stub
			MusicServers.this.start();
		}

		@Override
		public void stop() {
			// TODO Auto-generated method stub
			MusicServers.this.stop();
		}

		@Override
		public void continues() {
			// TODO Auto-generated method stub
			MusicServers.this.continues();
		}

		@Override
		public void seekProgress(int progress) {
			// TODO Auto-generated method stub
			MusicServers.this.seekProgress(progress);
		}
		
	}
	public void start()
	{
		mp.reset();
		//加载资源
		try {
			mp.setDataSource("sdcard/crazy.mp3");
			//异步准备
			mp.prepareAsync();
			//设置音乐的监听事件
			mp.setOnPreparedListener(new OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO Auto-generated method stub
					//开始音乐
					mp.start();
					//加载事件
					addTime();
				}
			});
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void continues(){
		mp.start();
	}
	public void stop(){
		mp.pause();
	}
	public void seekProgress(int progress){
		mp.seekTo(progress);
	}
	//时间跟踪
	public void addTime()
	{
		if(timer==null)
		{
			timer=new Timer();
			timer.schedule(new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					int sumTime=mp.getDuration();
					int currentTime=mp.getCurrentPosition();
					Bundle bundle=new Bundle();
					bundle.putInt("sumTime", sumTime);
					bundle.putInt("currentTime", currentTime);
					Message msg = MainActivity.handler.obtainMessage();
					msg.setData(bundle);
					MainActivity.handler.sendMessage(msg);				
					
				}
				
			}, 5, 500);
		}
	}

}
