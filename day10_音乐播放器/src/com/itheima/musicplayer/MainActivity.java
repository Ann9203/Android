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
			// ���þ�̬��handle��handle���Դ�������
			// ����һ��bundle��ȡ���ݹ���������
			Bundle bundle = new Bundle();
			bundle = msg.getData();
			// ��ȡ����
			int max = (Integer) bundle.get("duration");
			int current = (Integer) bundle.get("current");
			// ����ǰʱ�䣬������ʱ���seekBar��
			sb.setMax(max);
			sb.setProgress(current);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ȡseekBar�Ľ�����
		sb = (SeekBar) findViewById(R.id.sb);
		// ����SeekBar���϶������¼�
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			// ���϶���ʱ���ʱ��Ҫ��ȡ
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// �����ϷŵĽ���
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
		// ����ͼ
		Intent intent = new Intent(this, MusicServers.class);
		// ��ʼ�����ý��Ǳ�ɷ������
		startService(intent);
		// �󶨷��� ���õ��м�Ķ���
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

	// ��ʼ
	public void start(View v) {
		im.start();
	}

	// ����
	public void stop(View v) {
		im.pause();
	}

	// ����
	public void continues(View v) {
		im.continues();
	}

}
