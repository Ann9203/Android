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
		
		//��ȡͼƬ,idд����ͼƬ��id
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.cat);
		
		//����һ���µ�
		Bitmap blank_img = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		//��������
		Paint paint =new Paint();
		//��������
		Canvas canva=new Canvas(blank_img);
		//����
		canva.drawBitmap(bitmap, new Matrix(), paint);
		//�ҵ�image��id
		ImageView iv_img=(ImageView) findViewById(R.id.iv_img);
		ImageView iv_copyimg=(ImageView) findViewById(R.id.iv_copyimg);
		iv_img.setImageBitmap(bitmap);
		iv_copyimg.setImageBitmap(blank_img);
		
		
		
	}
	public void click(View v)
	{
		//��ȡ��Դ
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
		//������ֽ
		Bitmap copy_bitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		//��������
		Paint paint=new Paint();
		//��������
		Canvas canva=new Canvas(copy_bitmap);
		canva.drawBitmap(bitmap, new Matrix(), paint);
		 
		ImageView iv_img=(ImageView)findViewById(R.id.iv_img);
		ImageView iv_copyimg=(ImageView)findViewById(R.id.iv_copyimg);
		iv_img.setImageBitmap(bitmap);
		iv_copyimg.setImageBitmap(copy_bitmap);
	
		
	}


}
