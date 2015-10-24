package com.itheima.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.TextView;

import com.itheima.dao.QueryLocationDAO;
import com.itheima.mobileSafe.R;

/**
 * 监听电话到来的服务，
 * 
 * @author 雪宝宝
 * 
 */

public class CallLocationServer extends Service {
	WindowManager windowManager = null;
	// 创建一个TextView对象
	private View view = null;
	private SharedPreferences sp;
	private TelephonyManager telephonyManager;
	private MyCallBroadCaster mycallBroadCaster;
	private MyListen myListen;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		myListen = new MyListen();
		sp = getSharedPreferences("config", MODE_PRIVATE);
		// 注册电话外拨的监听zzzzza
		mycallBroadCaster = new MyCallBroadCaster();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
		// IntentFilter intentFilter=new
		// IntentFilter("android.intent.action.NEW_OUTGOING_CALL");
		System.out.println("Hello");
		registerReceiver(mycallBroadCaster, intentFilter);
		// 创建电话管理者
		telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		// 电话的回调监听
		telephonyManager.listen(myListen, PhoneStateListener.LISTEN_CALL_STATE);

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		telephonyManager.listen(myListen, PhoneStateListener.LISTEN_NONE);

		unregisterReceiver(mycallBroadCaster);
	}

	class MyListen extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			// 三种状态在电话铃响，挂掉电话，接听电话
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				// 电话处于空闲的状态
				hidToast();
				System.out.println("nihao");
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				// 处于接听电话的状态

				break;
			case TelephonyManager.CALL_STATE_RINGING:
				// 处于电话正在响的状态
				String queryDao = QueryLocationDAO.queryDao(incomingNumber,
						getApplicationContext());
				if (!TextUtils.isEmpty(queryDao)) {
					show(queryDao);
					// 电话正在响是想要实现一个自己定义的Toast
					
				}

				break;

			}
		}
	}

	/**
	 * 添加土司 添加自定义的土司
	 * 
	 * @param num
	 */
	public void show(String num) {

		int[] bgcolor = new int[] { R.drawable.call_locate_white,
				R.drawable.call_locate_orange, R.drawable.call_locate_blue,
				R.drawable.call_locate_gray, R.drawable.call_locate_green };
		// 获取一个windowsManager
		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		// 创建一个TextView对象
		// tv_toast=new TextView(getApplicationContext());
		view = View.inflate(getApplicationContext(),
				R.layout.activity_toastshow, null);
		TextView tv_local = (TextView) view
				.findViewById(R.id.tv_toastshow_location);
		tv_local.setText(num);
		// 根据sp中的which数据给view的背景设置图片
		view.setBackgroundResource(bgcolor[sp.getInt("whitch", 0)]);
		params = new WindowManager.LayoutParams();
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;
		params.flags =
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | 
				//WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE| 
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
		params.format = PixelFormat.TRANSLUCENT;
		params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
		// 设置位置
		params.gravity = Gravity.LEFT | Gravity.TOP;
		//得到父控件的规则属性
		DisplayMetrics outMetrics=new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		width = outMetrics.widthPixels;
		height = outMetrics.heightPixels;
		// 设置距离上下多少
		params.x = sp.getInt("X", 10);
		params.y = sp.getInt("Y", 10);
		//根据在SharedPreference中存储的XY的坐标，来设置响铃归属地的位置
		viewTouch();
		windowManager.addView(view, params);
		

	}
	
	/**
	 * 设置土司view的点击触摸事件
	 * 
	 */
	private  int startX;
	private int startY;
	private int width;
	private int height;
	private WindowManager.LayoutParams params;
	public void viewTouch(){
		System.out.println("00");
		view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						//按下
						startX=(int) event.getRawX();
						startY=(int) event.getRawY();
						
						break;
					case MotionEvent.ACTION_MOVE:
						
						//移动，获取新的原点
						int newX=(int)event.getRawX();
						int newY=(int)event.getRawY();
						//获取两点之间的距离
						int dr=newX-startX;
						int dy=newY-startY;
						//获取新的原点,距离父控件的左边的距离和右边的距离
						params.x+=dr;
						params.y+=dy;

//						if(l<0 || t<15 || l>width-view.getWidth() || t>height-view.getHeight()){
//							//如果遇到以上问题就不绘制控件了，直接跳出控件；
//							break;
//						}
						if(params.x<0){
							params.x=0;
						}
						if(params.y<0){
							params.y=0;
						}
						if(params.x>(width-view.getWidth())){
							params.x=width-view.getWidth();
						}
						if(params.y>(height-view.getHeight()-15)){
							params.y=height-view.getHeight()-15;
						}
						//获取距离父控件的位置
//						int r=view.getWidth()+l;
//						int b=view.getHeight()+t;
						//绘制控件的位置
						//view.layout(l, t, r, b);
						//更新父控件中的属性
						windowManager.updateViewLayout(view, params);
						//得到新的焦点
						startX=newX;
						startY=newY;
						
						
						break;
					case MotionEvent.ACTION_UP:
						//弹起
						//获取jiaodian
						int X=params.x;
						int Y=params.y;
						//保存焦点
						Editor edit=sp.edit();
						edit.putInt("X", X);
						edit.putInt("Y", Y);
						edit.commit();
						break;
				}
				
				
				return true;
			}
		});
	}
	

	/**
	 * 隐藏土司
	 */
	public void hidToast() {
		// 先判断windows和TextView是否为空
		if (windowManager != null && view != null) {
			// 如果不为空的话
			windowManager.removeView(view);
			windowManager = null;
			view = null;

		}
	}

	/**
	 * 监听外接电话，使用brodcaseter,在代码中注册
	 * 
	 */
	class MyCallBroadCaster extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			// 监听电话的时间
			String num = getResultData();
			System.out.println("lalalala" + num);

			String phone = QueryLocationDAO.queryDao(num,
					getApplicationContext());
			if (!TextUtils.isEmpty(phone)) {
				show(phone);

			}

		}
 
	}

}
