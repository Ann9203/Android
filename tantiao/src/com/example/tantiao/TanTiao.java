package com.example.tantiao;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;


public class TanTiao extends View {

	public TanTiao(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    private final String TAG = "QQBrowserLoading";
    //����
    private Paint mPaint;
    //��ɫ
    private int color = Color.parseColor("#0000FF");
    //�뾶
    private int radius = 10;
    private float density;
    private RectF rectF;
    //��㡢�յ㡢��ǰ��
    private int startY,startX,endY,currentY;
    public TanTiao(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public TanTiao(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		 density = getResources().getDisplayMetrics().density;
	        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	        mPaint.setStyle(Paint.Style.FILL);
	}

	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //��ȡ��ͼ�����ĵ�
        startX = getWidth()/2;
        endY =  getHeight() / 2;
        startY= endY * 5 / 6;
        mPaint.setColor(color);
        if(currentY == 0){
            playAnimator();
        }else{
            drawCircle(canvas);
            drawShader(canvas);
        }
    }

    //����ִ��
    private void playAnimator(){
    	//ֻҪ��ȡY�᷽���ϵı仯�Ϳ�����
    	ValueAnimator valueAnimator=ValueAnimator.ofInt(startY,endY);
    	valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
		
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				currentY=(Integer) animation.getAnimatedValue();
				
			}
		});
    	//���Ӧ���ǲ�����ǵ����Ķ���
    	valueAnimator.setInterpolator(new AccelerateInterpolator(1.2f));
    	//-1��������һֱ����
    	valueAnimator.setRepeatCount(-1);
    	//�ظ���ģʽ��ʲô���ӵ�
    	valueAnimator.setRepeatMode(2);
    	valueAnimator.setDuration(500);
    	
        //����ֻ��ҪȡY�᷽���ϵı仯����
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(startY,endY);
////        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                currentY = (Integer)animation.getAnimatedValue();
//                invalidate();
//            }
//        });
//        valueAnimator.setInterpolator(new AccelerateInterpolator(1.2f));
//        valueAnimator.setRepeatCount(-1);
//        valueAnimator.setRepeatMode(2);
//        valueAnimator.setDuration(500);
//        valueAnimator.start();
    }

    /**
     * ����Բ��
     * @param canvas
     */
    private void drawCircle(Canvas canvas){
        //���Ӵ����ײ�ʱ������Ϊ��Ҫ���һ��ѹ���Ч��
        if(endY - currentY >10){
            canvas.drawCircle(startX,currentY,radius * density,mPaint);
        }else{
            rectF = new RectF(startX - radius * density - 2,currentY - radius * density+5,
                    startX + radius * density+2,currentY + radius * density);
            canvas.drawOval(rectF,mPaint);
            Log.d(TAG,"Oval");
        }
    }

    /**
     * ������Ӱ���֣�����Բ��֧�֣����ݸ߶ȱ����ײ���Ӱ�Ĵ�С
     */
    private void drawShader(Canvas canvas){
        //�����ֵ�߶�
        int dx = endY - startY;
        //���㵱ǰ��ĸ߶Ȳ�ֵ
        int dx1 = currentY - startY;
        float ratio = (float) (dx1 *1.0 / dx);
        if(ratio <= 0.3){//���߶ȱ���С��0.3�����ڱȽϸߵ�ʱ��Ͳ����л���Ӱ��
            return;
        }
        int ovalRadius = (int) (radius * ratio * density);
        //���õ�Ӱ����ɫ
        mPaint.setColor(Color.parseColor("#3F3B2D"));
        //������Բ
        rectF = new RectF(startX-ovalRadius,endY+10,startX+ovalRadius,endY+15);
        canvas.drawOval(rectF,mPaint);
    }

    /**
     * ������ɫ
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
    }
    

}
