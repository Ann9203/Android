package com.itheima.tearcloth;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv;
	private Bitmap copy;
	private Paint paint;
	private Canvas canvas;
	private int startX;
	private int startY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.awaiyi);
		 copy = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		paint=new Paint();
		canvas=new Canvas(copy);
		canvas.drawBitmap(bitmap, new Matrix(), paint);
		iv=(ImageView)findViewById(R.id.iv_yuan);
		iv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					break;
				case MotionEvent.ACTION_MOVE:
					//������ʱ�򣬾��÷�Բ5����λ�ڶ�Ҫ��Ϊ͸��ɫ
					int newX=(int) event.getX();
					int newY=(int) event.getY();
					for(int i=-5;i<5;i++)
					{
						for(int j=-5;j<5;j++)
						{
							//�ж��ǵ��Ƿ���Բ��
							if(i*i+j*j<=25)
							{
								//�ѽ����Ϊ͸��ɫ
								if((newX+i)<copy.getWidth()&&(newX+i)>0 && (newY+j)<copy.getHeight()&&(newY+j)>0)
								{
									copy.setPixel((newX+i),(newY+j), Color.TRANSPARENT);
								}
							}
						}
					}
					//�����µ�ͼƬ
					iv.setImageBitmap(copy);
					break;
				case MotionEvent.ACTION_UP:
					break;
				}
				return true;
			}
		});
		
	}

	
}
