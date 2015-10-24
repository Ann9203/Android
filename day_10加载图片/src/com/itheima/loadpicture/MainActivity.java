package com.itheima.loadpicture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void click1(View v)
	{
		
		//创建一个对象，封装的时候加载图片时候使用的参数对象
		Options pos=new Options();
		//只解析图片的宽高，而不去解析图片的像素信息，那么就不需要胃图片申请内存
		pos.inJustDecodeBounds=true;
		//加载图片，顺便把ops作为参数传入
		//Bitmap imageBitmap = BitmapFactory.decodeFile("data/cat.jpg", pos);
		//获取宽和高
		int imageWidth = pos.outWidth;   
		int imageHeight = pos.outHeight;
		//后去屏幕的宽高
		Display dp = getWindowManager().getDefaultDisplay();
		@SuppressWarnings("deprecation")
		int screenWidth = dp.getWidth();
		@SuppressWarnings("deprecation")
		int screenHeight = dp.getHeight();
		//获取比例
		int scale =1;
		int scaleWidth=imageWidth/screenWidth;
		int scaleHeight=imageHeight/screenHeight;
		//查看比例谁大就用谁
		if(scaleWidth>=scaleHeight && scaleWidth>0)
		{
			scale=scaleWidth;
		}else if(scaleWidth<scaleHeight && scaleHeight>0)
		{
			scale=scaleHeight;
			
		}
		//谁知宽高都要设置缩小的比例
		pos.inSampleSize=scale;
		pos.inJustDecodeBounds=false;
		System.out.println("hahahh");
		Bitmap imageBitmap1 = BitmapFactory.decodeFile("data/cat.jpg", pos);
		ImageView msg=(ImageView)findViewById(R.id.iv_msg);
		msg.setImageBitmap(imageBitmap1);
		
		
		
	}
}
