package com.itheima.Reservice;

import com.itheima.mobileSafe.R;
import com.itheima.server.GPSService;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore.Audio.Media;
import android.sax.StartElementListener;
import android.telephony.SmsMessage;



public class CommandMSGReservice extends BroadcastReceiver {


	/**
	 * 发送指令到被盗的手机上
	 * 根据指令得到响应的操作
	 */
	
	
	private static MediaPlayer player;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		//获取管理员驱动
		DevicePolicyManager devicePolicyManager=(DevicePolicyManager) context.getSystemService(context.DEVICE_POLICY_SERVICE);
		//定义一个名称，是的获取管理员权限
		ComponentName componentName=new ComponentName(context,Admin.class);
		//解析发送过来的短信
		//首先显示获取Bundle，因为短信存放在Bundle的puds中
		Object[] objs = (Object[]) intent.getExtras().get("pdus");
		for(Object obj:objs ){
			SmsMessage smsMessage=SmsMessage.createFromPdu((byte[])obj);
			String body=smsMessage.getMessageBody();
			String sender=smsMessage.getDisplayOriginatingAddress();
			System.out.println("短信"+body+"；"+"发送人"+sender);
			if("#*location*#".equals(body)){
				//GPS跟踪跳转到开启到GPS服务中
			Intent intent_gps=new Intent(context,GPSService.class);
			
			context.startService(intent_gps);
			System.out.println("成功接收");
				
				abortBroadcast();
			}else if("#*alarm*#".equals(body)){
				//发送报警
				//声音管理者
				AudioManager audioManager=(AudioManager) context.getSystemService(context.AUDIO_SERVICE);
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 15, 0);
				//播放音乐
				 //如果不为空的话就要释放资源，因为广播接受者每次都回new一个新的广播接收者
				 if(player!=null){
					 player.release();
				 }
				 player=MediaPlayer.create(context, R.raw.alarm);
				 player.start();
				
				System.out.println("发送报警");
				abortBroadcast();
			}else if("#*wipedata*#".equals(body)){
				//远程删除数据
				System.out.println("远程销毁数据");
				//如果有超级管理员权限的话
				if(devicePolicyManager.isAdminActive(componentName)){
					devicePolicyManager.wipeData(0);
				}
				
				abortBroadcast();
			}else if("#*lockscreen*#".equals(body)){
				//远程锁屏
				//如果有管理员权限的话
				
				if(devicePolicyManager.isAdminActive(componentName)){
					//锁屏
					devicePolicyManager.lockNow();
				}
				
				System.out.println("远程锁屏");
				abortBroadcast();
			}
			
		}
	}
	



}
