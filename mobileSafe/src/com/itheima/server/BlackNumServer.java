package com.itheima.server;

import java.lang.reflect.Method;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;

import com.android.internal.telephony.ITelephony;
import com.itheima.db.dao.BlackNumSQL;

public class BlackNumServer extends Service {


	private BlackNumSQL sq;
	private BlackNumMsgReceiver msgRecevier;
	private TelephonyManager telephoneManager;
	private BlackNumCallListener blackNumCallListener;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		sq = new BlackNumSQL(getApplicationContext());
		msgRecevier = new BlackNumMsgReceiver();
		//代码注册广播就接受者
		IntentFilter intentFilter=new IntentFilter();
		intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
		intentFilter.setPriority(1001);
		registerReceiver(msgRecevier, intentFilter);
		
		//监听电话的状态
		telephoneManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		blackNumCallListener = new BlackNumCallListener();
		telephoneManager.listen(blackNumCallListener, PhoneStateListener.LISTEN_CALL_STATE);

	}
	/**
	 * 监听电话的状态
	 * @author 雪宝宝
	 *
	 */
	class BlackNumCallListener extends PhoneStateListener{
		@Override
		public void onCallStateChanged(int state, final String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			//检测如果是响铃的状态
			if(state==TelephonyManager.CALL_STATE_RINGING){
				String style=sq.queryBlackNumStyle(incomingNumber);
				if(style.equals(sq.CALL)||style.equals(sq.ALL)){
					endCall();
					//删除通话记录
					//定义内容观察者
					//定义url
					//删除的时候同时也要通知数据库进行删除，这时候用到了内容的观察者，进行实时观察
					final ContentResolver resolver=getContentResolver();
					final Uri uri=Uri.parse("content://call_log/calls");
					//使用观察者
					resolver.registerContentObserver(uri, true,new ContentObserver(new Handler()) {
						@Override
						public void onChange(boolean selfChange) {
							// TODO Auto-generated method stub
							super.onChange(selfChange);
							//发生变话就删除数据
							resolver.delete(uri, "number=?", new String[]{incomingNumber});
							//注销观察者
							resolver.unregisterContentObserver(this);
						}
					});
					
				}
			}
			//如果打来的电话是被拦截的就要挂断电话
			
			
		}

		
	}
	/**
	 * 挂断电话的方式
	 */
	private void endCall() {
		// TODO Auto-generated method stub
		
		try {
			//通过类加载器实现
			Class<?> loaderclass=BlackNumServer.class.getClassLoader().loadClass("android.os.ServiceManager");
			//获取方法
			Method method = loaderclass.getDeclaredMethod("getService", String.class);
			//通过方法给方法复制
			IBinder invode= (IBinder) method.invoke(null,Context.TELEPHONY_SERVICE );
			ITelephony itelephony = ITelephony.Stub.asInterface(invode);
			//挂断电话
			itelephony.endCall();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取类中的方法
		
	}
	/**
	 * 代码注册广播接受者
	 */
	class  BlackNumMsgReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//实现拦截
			Object[] objs = (Object[]) intent.getExtras().get("pdus");
			for(Object obj:objs ){
				SmsMessage smsMessage=SmsMessage.createFromPdu((byte[])obj);
				String body=smsMessage.getMessageBody();
				String sender=smsMessage.getDisplayOriginatingAddress();
				System.out.println("短信"+body+"；"+"发送人"+sender);
				//根据发件人的号码获取拦截的模式
				String style=sq.queryBlackNumStyle(sender);
				if(style.equals(sq.MSG)||style.equals(sq.ALL)){
					abortBroadcast();
				}
			}
		}
		
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(msgRecevier);
	}
}
