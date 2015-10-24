package com.itheima.drawblanks;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv_draw;
	private Paint paint;
	private Canvas canvas;
	private int setX;
	private int setY;
	private Bitmap blank;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		//创建一个画板，进行画画
		 blank = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		 paint=new Paint();
		 canvas=new Canvas(blank);
		canvas.drawBitmap(blank, new Matrix(), paint);
		iv_draw=(ImageView)findViewById(R.id.iv);
	
		iv_draw.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				
				    case MotionEvent.ACTION_DOWN:
				    	
				    //获取触摸的新的焦点
				    	setX=(int) event.getRawX();
				    	setY=(int) event.getRawY() ;
					break;
					case MotionEvent.ACTION_MOVE:
						//滑动的时候会有新的轨迹新的焦点
						int newX=(int)event.getRawX();
						int newY=(int)event.getRawY();
						//画线条
						canvas.drawLine(setX, setY, newX, newY, paint);
						iv_draw.setImageBitmap(blank);
						setX=newX;
						setY=newY;
						
						break;
					case MotionEvent.ACTION_UP:
						break;
				
				}
				
				return true;
			}
		});
		
	}

	public void green(View v)
	{
		paint.setColor(Color.GREEN);
	}
	public void red(View v)
	{
		paint.setColor(Color.RED);
	}
	public void brush(View v)
	{
		paint.setStrokeWidth(5);
	}
	//保存
	public void save(View v)
	{
		File file=new File("sdcard/be.jpg");
		FileOutputStream os;
		try{
			os=new FileOutputStream(file);
			blank.compress(CompressFormat.PNG, 100, os);
			os.close();
					
		}catch(Exception e){e.printStackTrace();}
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
		intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
		sendBroadcast(intent);
	}

}
