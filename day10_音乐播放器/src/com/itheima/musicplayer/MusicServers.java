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
		//��ʼ����
		player.reset();
		try {
			player.setDataSource("sdcard/crazy.mp3");
			//�첽׼��
			player.prepareAsync();
			//����player��׼��״̬
			player.setOnPreparedListener(new OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO Auto-generated method stub
					player.start();
					//ÿ�β��Ž���������ǰ��
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
					//��ȡ���ֵ����ǳ�
					int duration = player.getDuration();
					//��ȡ��ǰ���ֵĳ���
					int current = player.getCurrentPosition();
					
					//�������������ݷ�װ����Bundlezhong 
					Bundle bundle=new Bundle();
					bundle.putInt("duration", duration);
					bundle.putInt("current", current);
					Message msg = MainActivity.handler.obtainMessage();
					//message��װ����
					msg.setData(bundle);
					MainActivity.handler.sendMessage(msg);
					
					
				}
			}, 5, 500);
		}
	}

}
