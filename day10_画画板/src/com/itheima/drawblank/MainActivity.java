package com.itheima.drawblank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv_print;
	private Paint paint;
	private Bitmap copy_bitmap;
	private Canvas canvas;
	int setX=0;
	int setY=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		 copy_bitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		 paint=new Paint();
		 canvas=new Canvas(copy_bitmap);
		canvas.drawBitmap(bitmap, new Matrix(), paint);
		 iv_print=(ImageView) findViewById(R.id.iv_img);
		 
		iv_print.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				//刚刚按下去
				case MotionEvent.ACTION_DOWN:
					setX=(int) event.getX();
					setY=(int) event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					//移动轨迹产生新的点
					int newX=(int) event.getX();
					int newY=(int) event.getY();
					//在背景图上画线条
					canvas.drawLine(setX, setY, newX, newY, paint);
					iv_print.setImageBitmap(copy_bitmap);
					//把现在的起点设置为新的起点
					
					setX=newX;
					setY=newY;
					break;
				case MotionEvent.ACTION_UP:
					
					break;

				default:
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
		paint.setStrokeWidth(6);
	}

	public void save(View v)
	{
		//画完后进行保存
		File file=new File("sdcard/nice.jpg");
		FileOutputStream os;
		try {
			os=new FileOutputStream(file);
			//把位图压缩成一个本地的文件
			copy_bitmap.compress(CompressFormat.PNG, 100, os);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//创建意图
		Intent intent =new Intent();
		//创建意图的动作
		intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
		//创建意图的时间，来源于外部存储卡
		intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
		//发送广播
		sendBroadcast(intent);
		
	}

}
