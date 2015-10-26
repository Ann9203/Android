package com.itheima.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Toggle extends View {

	private Paint paint;
	//设置背景图
	private Bitmap backagroundImageBitmap;
	//设置滑动的图
	private Boolean isTouchMode=false;
	private Bitmap slideImageBitmap;
	public Toggle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	//布局使用
	public Toggle(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
		String namespace="http://schemas.android.com/apk/res/com.itheima.switchs";
		int switchbackagroundImage = attrs.getAttributeResourceValue(namespace, "switch_background", -1);
		int switchslideImage=attrs.getAttributeResourceValue(namespace, "slide_button", -1);
		
		mswitchState=attrs.getAttributeBooleanValue(namespace, "switch_state", false);
		setBackgroundImage(switchbackagroundImage);
		setSlideImage(switchslideImage);
	
	}

	public Toggle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public void init(){
		paint=new Paint();
	}
	/**
	 * 设置布局宽高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(backagroundImageBitmap.getWidth(), backagroundImageBitmap.getHeight());
	}
	
	/**
	 * 绘制布局绘制具体的内容
	 */
	//设置监听对象
	private OnSwitchStateUpdateListener onStateUpdateListener;
	//设置开关的初始状态为false
	private boolean mswitchState=false;
	private float currentX;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				isTouchMode=true;
			currentX = event.getX();
			
				break;
			case MotionEvent.ACTION_MOVE:
				
				currentX = event.getX();
				break;
			case MotionEvent.ACTION_UP:
				isTouchMode=false;
				//活动停止了，看看点停留在了那个位置
				currentX = event.getX();
				//活动停止了判断x的位置，以及监听是否为空，这样会传递数据
				float stateX=backagroundImageBitmap.getWidth()/2.0f;
				//设置一个状态
				boolean state=currentX>stateX;
				if(state!=mswitchState &onStateUpdateListener!=null){
					  //如果当前的状态不等于图片的状态就提示用户，如果等于就不提示
					onStateUpdateListener.setState(state);
				}
				//改变图片的状态
				mswitchState=state;
				//事件监听
				break;
				default:
					break;
		}
		//重新绘制画面
		invalidate();
		return true;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		//绘制背景
		canvas.drawBitmap(backagroundImageBitmap, 0, 0, paint);
		
		//绘制滑块
		//如果触摸了滑块
		//滑动模块需要考虑左右的状况
		if(isTouchMode){
			//设置新的x
			float newleft=currentX-slideImageBitmap.getWidth()/2.0f;
			//设置向左滑动的最大值
			float maxleft=backagroundImageBitmap.getWidth()-slideImageBitmap.getWidth();
			//判断这个滑动的幅度是否超出了变价
			if(newleft<0){
				newleft=0;
			}else if(newleft>maxleft){
				newleft=maxleft;
			}
			
			//当滑动的时候绘制图片
			canvas.drawBitmap(slideImageBitmap, newleft, 0, paint);
			
		}else{
			//如果状态是false的话，状态就是false的状态
			//如果当前的状态是开着的话
			if(mswitchState){
				int newleft12=backagroundImageBitmap.getWidth()-slideImageBitmap.getWidth();
				canvas.drawBitmap(slideImageBitmap, newleft12, 0, paint);
				
			}else{
				//如果是关的话
				canvas.drawBitmap(slideImageBitmap, 0, 0, paint);
				//根据当前的currentX判断是开还是关
			}
		}
		
		
		
	}
	/**
	 * 设置图片的背景
	 * @param switchBackground
	 */

	public void setBackgroundImage(int switchBackground) {
		backagroundImageBitmap=BitmapFactory.decodeResource(getResources(), switchBackground);
		
	}

	/**
	 * 设置滑动的图片
	 * @param slideButton
	 */
	public void setSlideImage(int slideImage) { 	
		slideImageBitmap=BitmapFactory.decodeResource(getResources(), slideImage);
	}

	/**
	 * 设置状态
	 * @param b
	 */
	public void setState(boolean b) {
		// TODO Auto-generated method stub
		this.mswitchState=b;
	}

	/**
	 * 设置事件的监听，监听事件回调状态
	 *
	 */
	public interface OnSwitchStateUpdateListener{
		void setState(boolean state);
	}
	//设置事件的监听
	public  void setOnSwitchStateUpdateListener(OnSwitchStateUpdateListener onSwitchStateUpdateListener){
		this.onStateUpdateListener=onSwitchStateUpdateListener;
	}
	

}
