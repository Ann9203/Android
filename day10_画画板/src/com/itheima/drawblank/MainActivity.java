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
				//�ոհ���ȥ
				case MotionEvent.ACTION_DOWN:
					setX=(int) event.getX();
					setY=(int) event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					//�ƶ��켣�����µĵ�
					int newX=(int) event.getX();
					int newY=(int) event.getY();
					//�ڱ���ͼ�ϻ�����
					canvas.drawLine(setX, setY, newX, newY, paint);
					iv_print.setImageBitmap(copy_bitmap);
					//�����ڵ��������Ϊ�µ����
					
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
		//�������б���
		File file=new File("sdcard/nice.jpg");
		FileOutputStream os;
		try {
			os=new FileOutputStream(file);
			//��λͼѹ����һ�����ص��ļ�
			copy_bitmap.compress(CompressFormat.PNG, 100, os);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//������ͼ
		Intent intent =new Intent();
		//������ͼ�Ķ���
		intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
		//������ͼ��ʱ�䣬��Դ���ⲿ�洢��
		intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
		//���͹㲥
		sendBroadcast(intent);
		
	}

}
