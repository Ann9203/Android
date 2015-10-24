package com.itheima.viewplayer;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity {

	private MediaPlayer player;
	private SurfaceView sv;
	private static int currentPosition=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sv=(SurfaceView)findViewById(R.id.sv);
		SurfaceHolder holder = sv.getHolder();
		holder.addCallback(new Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				if(player!=null)
				{
					//获取的当前播放的进度
					currentPosition=player.getCurrentPosition();
					player.stop();
					//释放资源
					player.release();
					player=null;
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				//创建的时候
				if(player==null)
				{
					player=new MediaPlayer();
					player.reset();
					try {
						player.setDataSource("sdcard/life.3gp");	
						System.out.println("hahhah");
						player.setDisplay(holder);
						player.prepare();
						player.seekTo(currentPosition);
						player.start();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
				
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
