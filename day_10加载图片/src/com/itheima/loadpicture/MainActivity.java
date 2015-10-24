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
		
		//����һ�����󣬷�װ��ʱ�����ͼƬʱ��ʹ�õĲ�������
		Options pos=new Options();
		//ֻ����ͼƬ�Ŀ�ߣ�����ȥ����ͼƬ��������Ϣ����ô�Ͳ���ҪθͼƬ�����ڴ�
		pos.inJustDecodeBounds=true;
		//����ͼƬ��˳���ops��Ϊ��������
		//Bitmap imageBitmap = BitmapFactory.decodeFile("data/cat.jpg", pos);
		//��ȡ��͸�
		int imageWidth = pos.outWidth;   
		int imageHeight = pos.outHeight;
		//��ȥ��Ļ�Ŀ��
		Display dp = getWindowManager().getDefaultDisplay();
		@SuppressWarnings("deprecation")
		int screenWidth = dp.getWidth();
		@SuppressWarnings("deprecation")
		int screenHeight = dp.getHeight();
		//��ȡ����
		int scale =1;
		int scaleWidth=imageWidth/screenWidth;
		int scaleHeight=imageHeight/screenHeight;
		//�鿴����˭�����˭
		if(scaleWidth>=scaleHeight && scaleWidth>0)
		{
			scale=scaleWidth;
		}else if(scaleWidth<scaleHeight && scaleHeight>0)
		{
			scale=scaleHeight;
			
		}
		//˭֪��߶�Ҫ������С�ı���
		pos.inSampleSize=scale;
		pos.inJustDecodeBounds=false;
		System.out.println("hahahh");
		Bitmap imageBitmap1 = BitmapFactory.decodeFile("data/cat.jpg", pos);
		ImageView msg=(ImageView)findViewById(R.id.iv_msg);
		msg.setImageBitmap(imageBitmap1);
		
		
		
	}
}
