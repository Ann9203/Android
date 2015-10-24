package com.itheima.mobileSafe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class AttributeLocalActivity extends Activity {

	private SharedPreferences sp;
	@ViewInject(R.id.ll_attributelocal_local)
	private LinearLayout ll_attribute;
	@ViewInject(R.id.tv_local_top)
	private TextView tv_local_top;
	@ViewInject(R.id.tv_local_bottom)
	private TextView tv_local_bottom;
	private WindowManager windowManager;
	private int width;
	private int height;
	private RelativeLayout.LayoutParams params;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attributelocal);
		sp=getSharedPreferences("config", MODE_PRIVATE);
		ViewUtils.inject(this);
		showAttribute();
		//控件的回显
		int X=sp.getInt("X", 10);
		int Y=sp.getInt("Y",  10);
		params = (LayoutParams) ll_attribute.getLayoutParams();
		//获取子控件距离父控件的左边以及上边的距离，来定点
		params.leftMargin=X;
		params.topMargin=Y;
		//子类设置相对于父类的属性完成回显
		ll_attribute.setLayoutParams(params);
		
		
		//获取屏幕高度和宽度的值
		windowManager=(WindowManager) getSystemService(WINDOW_SERVICE);
		//获取display，在屏幕上定制一个画画版
		Display display = windowManager.getDefaultDisplay();
		//创建一个画画版
		DisplayMetrics displayMetrics =new DisplayMetrics();
		//c初始化这个画画版
		display.getMetrics(displayMetrics);
		//获取画画版的宽高 也就相当于屏幕的宽高
		 width = displayMetrics.widthPixels;
		 height=displayMetrics.heightPixels;
		
		//设置两个描述框是谁在上谁在下
		 if(Y<(height/2)){
			 tv_local_bottom.setVisibility(View.VISIBLE);
			 tv_local_top.setVisibility(View.INVISIBLE);
		 }else{
			 
			 tv_local_bottom.setVisibility(View.INVISIBLE);
			 tv_local_top.setVisibility(View.VISIBLE);
		 }
		 doubleClick();
		 
	}
	

/**
 * 设置鼠标的双击事件
 * 
 */
	long[] mHits = new long[2];

	public  void doubleClick(){
		//控件的双击事件
				ll_attribute.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						System.arraycopy(mHits, 1, mHits, 0, mHits.length-1);
				        mHits[mHits.length-1] = SystemClock.uptimeMillis();  // 将离开机的时间设置给数组的第二个元素,离开机时间 :毫秒值,手机休眠不算
				        if (mHits[0] >= (SystemClock.uptimeMillis()-500)) {  
				        	//鼠标被双击之后就应该获取空间的中间的坐标
				        	 int l=(width-ll_attribute.getWidth())/2;
				        	 int t=(height-15-ll_attribute.getHeight())/2;
				        	//获取子控件距离父控件的左边以及上边的距离，来定点,获取新的原点
				        	 //重新绘制控件,前两个参数是：关于原点，后两个参数：右边的点距离其距离以下边的点距离其长度
				        	 ll_attribute.layout(l, t, l+ll_attribute.getWidth(), t+ll_attribute.getHeight());
				        	//重新将点保存至sp中
				        	 Editor edit=sp.edit();
				        	 edit.putInt("X", l);
				        	 edit.putInt("Y", t);
				        	 edit.commit();
				        	 
				        }
					}
				});
				
	}
	
	
	/**
	 *界面点击事件
	 */
	private void showAttribute(){
		
		
		
		//触摸事件
		ll_attribute.setOnTouchListener(new OnTouchListener() {
			int startX;
			int startY;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				
					case MotionEvent.ACTION_DOWN:
						//按钮按下去
						//获取位置
					
						 startX=(int) event.getRawX();
						 startY=(int)event.getRawY();
						break;
					case MotionEvent.ACTION_MOVE:
						//移动
						//获取移动时候的焦点
						int newX=(int) event.getRawX();
						int newY=(int)event.getRawY();
					
						//计算偏移量
						int dX=newX-startX;
						int dY=newY-startY;
						//计算原空间距离父控件的左边和顶部的距离
						int l=ll_attribute.getLeft();
						int t=ll_attribute.getTop();
						//得到现在空间的远点
						l+=dX;
						t+=dY;
						//问题：1.不能超过左右边界  
							//现在控件的定点X要大于0，同时距离左边的屏幕的距离不能大于屏幕宽度-控件宽度
						//问题2：不能超过上下边界   要大于0  同时距离底边的距离不能大于屏幕高度-控件高度
						if(l<0 || t<15 || l>width-ll_attribute.getWidth() || t>height-ll_attribute.getHeight()){
							//如果遇到以上问题就不绘制控件了，直接跳出控件；
							break;
						}
						//设置如果是超出边界如何
						
						
						
						//得到距离父控件的位置
						int r=l+ll_attribute.getWidth();
						int b=t+ll_attribute.getHeight();
						//重新绘制空间
						ll_attribute.layout(l, t, r, b);
						//重新获取点的位置
						if(t<(height/2)){
							 tv_local_bottom.setVisibility(View.VISIBLE);
							 tv_local_top.setVisibility(View.INVISIBLE);
						 }else{
							 
							 tv_local_bottom.setVisibility(View.INVISIBLE);
							 tv_local_top.setVisibility(View.VISIBLE);
						 }
						startX=newX;
						startY=newY;
						break;
					case MotionEvent.ACTION_UP:
						//抬起
						//获取空间距离左边的距离也就是左边的焦点
						int X = ll_attribute.getLeft();
						//获取控件上边的距离，也就是上边的焦点，两个组合式左上角的点
						int Y=ll_attribute.getTop();
						//再抬起的时候保存x和y的坐标
						Editor edit=sp.edit();
						edit.putInt("X", X);
						edit.putInt("Y", Y);
						edit.commit();
						
						break;
				}
				
				
				return false;
			}
		});
	}
}
