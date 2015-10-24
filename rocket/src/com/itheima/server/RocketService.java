package com.itheima.server;


import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

import com.itheima.rocket.BackgroundActivity;
import com.itheima.rocket.R;


public class RocketService extends Service {

	private WindowManager windowManager;
	private int width;
	private int height;
	private WindowManager.LayoutParams params;
	private View view;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		windowManager=(WindowManager) getSystemService(WINDOW_SERVICE);
		Display defaultDisplay = windowManager.getDefaultDisplay();
		DisplayMetrics outMetrics=new DisplayMetrics();
		defaultDisplay.getMetrics(outMetrics);
		//获取主控件的宽高
		width = outMetrics.widthPixels;
		height = outMetrics.heightPixels;
		showRocket();
		bgShow();
		
	}
	
	/**
	 * 添加显示是否发射的图片
	 */
	public void bgShow(){
		params2 = new WindowManager.LayoutParams();
		view1 = View.inflate(getApplicationContext(), R.layout.activity_bg, null);
		iv_rocket_bg=(ImageView) view1.findViewById(R.id.iv_rockt_bg);
		params2.height = WindowManager.LayoutParams.WRAP_CONTENT;
		params2.width = WindowManager.LayoutParams.WRAP_CONTENT;
		params2.gravity=Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
		params2.flags =
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | 
				//WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE| 
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
		params2.format = PixelFormat.TRANSLUCENT;
		params2.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
		
		windowManager.addView(view1, params2);
	}
	
	/**
	 * 设置显示小火箭的操作
	 */
	public  void showRocket(){
		params = new WindowManager.LayoutParams();
		view = View.inflate(getApplicationContext(),R.layout.activity_rockt , null);		
		ImageView iv_rocket=(ImageView) view.findViewById(R.id.iv_rocket);
		//iv_rocket_bg = (ImageView) view.findViewById(R.id.iv_rockt_bg);
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
		//开启动画
		AnimationDrawable background = (AnimationDrawable) iv_rocket.getBackground();		
		background.start();
		touchRocket();
		
		windowManager.addView(view, params);
		
	}
	/**
	 * 火箭的触摸事件
	 */
	private void touchRocket() {
		// TODO Auto-generated method stub
		view.setOnTouchListener(new OnTouchListener() {
			
			private int startX;
			private int startY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
					startX = (int) event.getRawX();
					startY = (int) event.getRawY();
					iv_rocket_bg.setBackgroundResource(R.drawable.desktop_bg_tips_3);
						break;
					case MotionEvent.ACTION_MOVE:
						//当事件进行移动的时候
						int  newX=(int) event.getRawX();
						int newY=(int) event.getRawY();
						//iv_rocket_bg.setImageResource(R.drawable.desktop_bg_tips_2);
						//获取两者之间的距离
						int dr=newX-startX;
						int dt=newY-startY;
						//获取现在的坐标,也就是距离父控件的左边和上边的距离
						params.x+=dr;
						params.y+=dt;
						//判断不符合条件的状态
						if(params.x<0){
							params.x=0;
						}
						if(params.x>width-view.getWidth()){
							params.x=width-view.getWidth();
						}
						if(params.y<0){
							params.y=0;
						}
						if(params.y>height-view.getHeight()-25){
							params.y=height-view.getHeight()-25;
						}
						if(params.y>(height-view.getHeight()-25)-20){
							iv_rocket_bg.setVisibility(View.INVISIBLE);
						}
						windowManager.updateViewLayout(view, params);
						startX=newX;
						startY=newY;

						break;
					case MotionEvent.ACTION_UP:
						//抬起的时候冒烟
						if(params.y>290 && params.x>100 && params.x<200){
							send();
							Intent intent=new Intent(RocketService.this,BackgroundActivity.class);
							//创建一个新的Activity栈
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(intent);
							
						}
						break;
				
				}
				
				
				return true; 
						
			}
		});
		
		
		
	}
	
	/**
	 * 发送小火箭就是一直在更改着y的值
	 * 
	 */
	 Handler handler=new Handler(){
		 public void handleMessage(android.os.Message msg) {
			 params.y-=10;
			 windowManager.updateViewLayout(view, params);
		 };
//		public void handleMessage(android.os.Message msg) {			
//			//重新绘制画面
//			windowManager.updateViewLayout(view, params);
//			
//		};
	};
	private ImageView iv_rocket_bg;
	private View view1;
	private WindowManager.LayoutParams params2;
	public void  send(){
		new Thread(){
		public void run() {
			for(int i=0 ;i<45;i++){
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				handler.sendEmptyMessage(0);
			}
			
			
		};
		}.start();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(windowManager!=null && view!=null && view1!=null){
			windowManager.removeView(view);
			windowManager.removeView(view1);
			windowManager=null;
			view=null;
			view1=null;
		}
	}
}
