package com.itheima.telrecord;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class RecordServer extends Service {

	//创建录音对象
	MediaRecorder record;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate()
	{
		super.onCreate();
		//获取电话管理器
		TelephonyManager manager=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		//监听电话状态
		manager.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}
	//继承电话状态监听类
	class MyListener extends PhoneStateListener
	{
		//重写电话改变的方法
		@Override
		public void onCallStateChanged(int state,String incomingNumber)
		{
			super.onCallStateChanged(state, incomingNumber);
			switch(state)
			{
			//如果电话使空闲的状体啊
			   case TelephonyManager.CALL_STATE_IDLE:
				   //闲事状态就将录音关掉
				   	if(record!=null)
				   	{
				   		record.stop();
				   		//释放资源
				   		record.release();
				   		record=null;
				   	}
				   	break;
				   	//当电话响起的时候，就开始创建录音 对象
			   case TelephonyManager.CALL_STATE_RINGING:
				   System.out.println("响铃");
				   //如果录音为空，就开始创建录音
				   if(record==null)
				   {
					   record =new MediaRecorder();
					   //设置音频的来源
					   record.setAudioSource(MediaRecorder.AudioSource.MIC);
					   //设置录音的保存格式
					   record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					   //设置保存的路径和文件名
					   record.setOutputFile("sdcard/heihei.3gp");
					   //设置编码格式
					   record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					   //等待
					   try {
						record.prepare();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }
				   break;
			   case TelephonyManager.CALL_STATE_OFFHOOK:
				   //当电话拿起的时候
				   System.out.println("正在接通电话");
				   if(record!=null)
				   {
					   //如果录音不为空的话，就开始录音
					   record.start();
				   }
				   break;
				   
			}
		}
	}

}
