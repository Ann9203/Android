package com.itheima.lianxi_loadpicture;

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
	//加载大的图片
	public void click1(View v)
	{
		//设置option的对象
		Options opt=new Options();
		//设置加载的时候先不要分配内存
		opt.inJustDecodeBounds=true;
		 BitmapFactory.decodeFile("data/li.jpg", opt);
		//获取宽高
		int imageWidth=opt.outWidth;
		int imageHeight=opt.outHeight;
		//获取屏幕的高宽
		Display display = getWindowManager().getDefaultDisplay();
		@SuppressWarnings("deprecation")
		int screenWidth=display.getWidth();
		@SuppressWarnings("deprecation")
		int screenHeight=display.getHeight();
		
		//设置一个变量
		int scale=1;
		int scaleWidth=imageWidth/screenWidth;
		int scaleHeight=imageHeight/screenHeight;
		if(scaleWidth>=scaleHeight && scaleWidth>0)
		{
			scale=scaleWidth;
		}if(scaleHeight>scaleWidth && scaleHeight>0)
		{
			scale=scaleHeight;
		}
		System.out.println(scale);
		//谁知缩小的宽高
		opt.inSampleSize=scale;
		opt.inJustDecodeBounds=false;
		Bitmap bitmap = BitmapFactory.decodeFile("data/li.jpg", opt);
		ImageView iv=(ImageView) findViewById(R.id.iv_img);
		iv.setImageBitmap(bitmap);
		
		
	}

}
