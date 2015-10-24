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
		//��ȡ��Դ
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
		//������ֽ
		Bitmap copy_bitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		//��������
		Paint paint=new Paint();
		//��������
		Canvas canva=new Canvas(copy_bitmap);
		Matrix matrix = new Matrix();
		//Ψһ
		//matrix.setTranslate(20, 10);
		///����
		//matrix.setScale(0.5f, 5,copy_bitmap.getWidth()/2,copy_bitmap.getHeight()/2);
		//��ת
		//matrix.setRotate(45, copy_bitmap.getWidth()/2, copy_bitmap.getHeight()/2);
		//����Ч��
//		matrix.setScale(-1, 1);
//		matrix.postTranslate(copy_bitmap.getWidth(), 0);
		//��ӰЧ��
//		matrix.setScale(1, -1);
//		matrix.postTranslate(0, copy_bitmap.getHeight����);
		
		 //λ��
//      matrix.setTranslate(20, 10);
      //����
//      matrix.setScale(0.5f, 2, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
      //��ת
//      matrix.setRotate(-45, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
      //����Ч��
//      matrix.setScale(-1, 1);
//      matrix.postTranslate(bmCopy.getWidth(), 0);
      //��ӰЧ��
//      matrix.setScale(1, -1);
//      matrix.postTranslate(0, bmCopy.getHeight());
      
		canva.drawBitmap(bitmap, matrix, paint);
		 
		ImageView iv_img=(ImageView)findViewById(R.id.iv_img);
		ImageView iv_copyimg=(ImageView)findViewById(R.id.iv_copyimg);
		iv_img.setImageBitmap(bitmap);
		iv_copyimg.setImageBitmap(copy_bitmap);
	}

	

}
