package com.itheima.simpleduepicture;



import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取资源
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
		//创建白纸
		Bitmap copy_bitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		//创建画笔
		Paint paint=new Paint();
		//创建画板
		Canvas canva=new Canvas(copy_bitmap);
		Matrix matrix = new Matrix();
		//唯一
		//matrix.setTranslate(20, 10);
		///缩放
		//matrix.setScale(0.5f, 5,copy_bitmap.getWidth()/2,copy_bitmap.getHeight()/2);
		//旋转
		//matrix.setRotate(45, copy_bitmap.getWidth()/2, copy_bitmap.getHeight()/2);
		//镜面效果
//		matrix.setScale(-1, 1);
//		matrix.postTranslate(copy_bitmap.getWidth(), 0);
		//倒影效果
//		matrix.setScale(1, -1);
//		matrix.postTranslate(0, copy_bitmap.getHeight（）);
		
		 //位移
//      matrix.setTranslate(20, 10);
      //缩放
//      matrix.setScale(0.5f, 2, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
      //旋转
//      matrix.setRotate(-45, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
      //镜面效果
//      matrix.setScale(-1, 1);
//      matrix.postTranslate(bmCopy.getWidth(), 0);
      //倒影效果
//      matrix.setScale(1, -1);
//      matrix.postTranslate(0, bmCopy.getHeight());
      
		canva.drawBitmap(bitmap, matrix, paint);
		 
		ImageView iv_img=(ImageView)findViewById(R.id.iv_img);
		ImageView iv_copyimg=(ImageView)findViewById(R.id.iv_copyimg);
		iv_img.setImageBitmap(bitmap);
		iv_copyimg.setImageBitmap(copy_bitmap);
	}

	

}
