package com.itheima.musicplayer;



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

	MediaPlayer player;
	Timer timer;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new Music();
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		player=new MediaPlayer();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		player.release();
		if(timer!=null)
		{
			timer.cancel();
			timer=null;
		}
	}
	
	class Music extends Binder implements IMusic
	{
		@Override
		public void start() {
			// TODO Auto-generated method stub
			MusicServers.this.start();
		}

		@Override
		public void pause() {
			// TODO Auto-generated method stub
			MusicServers.this.pause();
		}

		@Override
		public void continues() {
			// TODO Auto-generated method stub
			MusicServers.this.continues();
		}

		@Override
		public void setBar(int progress) {
			// TODO Auto-generated method stub
			MusicServers.this.setBar(progress);
		}
		
	}
	public void start(){
		//开始播放
		player.reset();
		try {
			player.setDataSource("sdcard/crazy.mp3");
			//异步准备
			player.prepareAsync();
			//监听player的准备状态
			player.setOnPreparedListener(new OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO Auto-generated method stub
					player.start();
					//每次播放进度条跟着前进
					addTime();
				}
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
	public void pause(){
		player.pause();
		
	}
	public void continues(){
		player.start();
	}
	public void setBar(int progress){
		player.seekTo(progress);
	}
	public void  addTime()
	{
		if(timer==null)
		{
			timer=new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//获取音乐的总是长
					int duration = player.getDuration();
					//获取当前音乐的长度
					int current = player.getCurrentPosition();
					
					//吧这这两条数据封装到了Bundlezhong 
					Bundle bundle=new Bundle();
					bundle.putInt("duration", duration);
					bundle.putInt("current", current);
					Message msg = MainActivity.handler.obtainMessage();
					//message封装数据
					msg.setData(bundle);
					MainActivity.handler.sendMessage(msg);
					
					
				}
			}, 5, 500);
		}
	}

}
