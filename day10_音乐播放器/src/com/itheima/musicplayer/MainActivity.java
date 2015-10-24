package com.itheima.musicplayer;

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
	static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 设置静态的handle，handle可以传递数据
			// 设置一个bundle获取传递过来的数据
			Bundle bundle = new Bundle();
			bundle = msg.getData();
			// 提取数据
			int max = (Integer) bundle.get("duration");
			int current = (Integer) bundle.get("current");
			// 将当前时间，和最大的时间给seekBar中
			sb.setMax(max);
			sb.setProgress(current);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取seekBar的进度条
		sb = (SeekBar) findViewById(R.id.sb);
		// 设置SeekBar的拖动监听事件
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			// 当拖动的时候的时间要获取
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// 设置拖放的进度
				int progress = sb.getProgress();
				im.setBar(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});
		// 绑定意图
		Intent intent = new Intent(this, MusicServers.class);
		// 开始服务，让进城变成服务进程
		startService(intent);
		// 绑定服务 ，拿到中间的对象
		bindService(intent, new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				im = (IMusic) service;
			}
		}, BIND_AUTO_CREATE);

	}

	// 开始
	public void start(View v) {
		im.start();
	}

	// 结束
	public void stop(View v) {
		im.pause();
	}

	// 继续
	public void continues(View v) {
		im.continues();
	}

}
