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
	//���ش��ͼƬ
	public void click1(View v)
	{
		//����option�Ķ���
		Options opt=new Options();
		//���ü��ص�ʱ���Ȳ�Ҫ�����ڴ�
		opt.inJustDecodeBounds=true;
		 BitmapFactory.decodeFile("data/li.jpg", opt);
		//��ȡ���
		int imageWidth=opt.outWidth;
		int imageHeight=opt.outHeight;
		//��ȡ��Ļ�ĸ߿�
		Display display = getWindowManager().getDefaultDisplay();
		@SuppressWarnings("deprecation")
		int screenWidth=display.getWidth();
		@SuppressWarnings("deprecation")
		int screenHeight=display.getHeight();
		
		//����һ������
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
		//˭֪��С�Ŀ��
		opt.inSampleSize=scale;
		opt.inJustDecodeBounds=false;
		Bitmap bitmap = BitmapFactory.decodeFile("data/li.jpg", opt);
		ImageView iv=(ImageView) findViewById(R.id.iv_img);
		iv.setImageBitmap(bitmap);
		
		
	}

}
