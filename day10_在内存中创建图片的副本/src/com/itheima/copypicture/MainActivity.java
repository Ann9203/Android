package com.itheima.copypicture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//获取图片,id写的是图片的id
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.cat);
		
		//创建一个新的
		Bitmap blank_img = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		//创建画笔
		Paint paint =new Paint();
		//创建画板
		Canvas canva=new Canvas(blank_img);
		//画画
		canva.drawBitmap(bitmap, new Matrix(), paint);
		//找到image的id
		ImageView iv_img=(ImageView) findViewById(R.id.iv_img);
		ImageView iv_copyimg=(ImageView) findViewById(R.id.iv_copyimg);
		iv_img.setImageBitmap(bitmap);
		iv_copyimg.setImageBitmap(blank_img);
		
		
		
	}
	public void click(View v)
	{
		//获取资源
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
		//创建白纸
		Bitmap copy_bitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		//创建画笔
		Paint paint=new Paint();
		//创建画板
		Canvas canva=new Canvas(copy_bitmap);
		canva.drawBitmap(bitmap, new Matrix(), paint);
		 
		ImageView iv_img=(ImageView)findViewById(R.id.iv_img);
		ImageView iv_copyimg=(ImageView)findViewById(R.id.iv_copyimg);
		iv_img.setImageBitmap(bitmap);
		iv_copyimg.setImageBitmap(copy_bitmap);
	
		
	}


}
