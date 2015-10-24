package com.jczb.car.ui;




import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jczb.car.AppContext;
import com.jczb.car.R;

public class Mine extends Activity implements OnClickListener {
	
	RelativeLayout editNickname,modifyPassword,myClan,versionInfo,aboutUs;
	ImageView userPhoto;
	Button btMineQuit;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine);
		findViewByID();
		
		 /*拿到ID*/
//        Intent intent = getIntent(); 
//        Bundle bundle = intent.getExtras(); 
		
		/*跳转到修改昵称、修改密码、我的部落、版本信息、关于我们*/
	    editNickname.setOnClickListener(this);
	    modifyPassword.setOnClickListener(this);
	    myClan.setOnClickListener(this);
        versionInfo.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        userPhoto.setOnClickListener(this);
        btMineQuit.setOnClickListener(this);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.my);
        userPhoto.setImageBitmap(toRoundCorner(b,200));
	}
	
	
	
	
	/*根据ID找到控件*/
	  public void findViewByID(){
		  
		  
		  /*跳转到修改昵称、修改密码、我的部落、版本信息、关于我们*/
		  modifyPassword=(RelativeLayout) findViewById(R.id.modify_password);
		  editNickname=(RelativeLayout) findViewById(R.id.edit_nickname);
		  myClan=(RelativeLayout) findViewById(R.id.my_clan);
	      versionInfo=(RelativeLayout) findViewById(R.id.version_info);
		  aboutUs=(RelativeLayout) findViewById(R.id.about_us);
		  userPhoto=(ImageView) findViewById(R.id.user_picture);
		  btMineQuit=(Button) findViewById(R.id.mine_quit);
		  
		 
	}
	  
	  /**
	   * 功能说明：设置圆角头像
	   * 作者：丁国华
	   * 时间：2015年9月6日 14:20:33
	   */
	  public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);

			Canvas canvas = new Canvas(output);

			final int color = 0xff424242;

			final Paint paint = new Paint();

			final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

			final RectF rectF = new RectF(rect);

			final float roundPx = pixels;

			paint.setAntiAlias(true);

			canvas.drawARGB(0, 0, 0, 0);

			paint.setColor(color);

			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

			canvas.drawBitmap(bitmap, rect, rect, paint);
			return output;

		}
	  
	  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mine, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final AppContext ac = (AppContext)getApplication();
		
		switch(v.getId()){
		case R.id.modify_password:
			if(ac.isLogin())
			{
				Intent intentModifyPassword = new Intent(this,ModifyPasswordActivity.class);
			    startActivity(intentModifyPassword);
			}
			else
			{
				Intent intentModifyPassword = new Intent(this,LoginActivity.class);
			    startActivity(intentModifyPassword);
			}
			break;
			
		case R.id.my_clan:
			if(ac.isLogin())
			{
			Intent intentMyClan = new Intent(this,MyClanActivity.class);
		    startActivity(intentMyClan);
			}
			else
			{
				Intent intentModifyPassword = new Intent(this,LoginActivity.class);
			    startActivity(intentModifyPassword);
			}
			break;
		case R.id.version_info:
			Intent intentVersionInfo = new Intent(this,VersionInfoActivity.class);
		    startActivity(intentVersionInfo);
			break;
		case R.id.about_us:
			Intent intentAboutUs = new Intent(this,AboutUsActivity.class);
		    startActivity(intentAboutUs);
			break;
		case R.id.edit_nickname:
			if(ac.isLogin())
			{
			Intent intentEditNickname = new Intent(this,EditNicknameActivity.class);
		    startActivity(intentEditNickname);
			}
			else
			{
				Intent intentModifyPassword = new Intent(this,LoginActivity.class);
			    startActivity(intentModifyPassword);
			}
			break;
		case R.id.user_picture:
			Intent intentUserPicture = new Intent(this,SelectPicPopupWindow.class);
		    startActivity(intentUserPicture);
			break;
		case R.id.mine_quit:
			Intent intentMineQuit = new Intent(this,LoginActivity.class);
		    startActivity(intentMineQuit);
			break;
		default:
			break;
		
		
	}

}
	
	
	/*//测试，记得删除
	 public void test() throws AppException{
		//
		 开始线程
	     new Thread(new myThread()).start();
//		 AppContext appContext = (AppContext)getApplication();
//		 appContext.getChannelCategoryList(1, false);
	 }
	 public class myThread implements Runnable {
			public void run() {			
				Message msg = new Message();						
				
				
				try {
					AppContext appContext = (AppContext)getApplication();
					 appContext.getChannelCategoryList(1, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mHandler.sendMessage(msg);	
				
			}
		}
	 private  Handler mHandler = new Handler(){
	    	public void handleMessage(Message msg) {
	    		String temp=(String) msg.obj;
//	    		ChannelCategoryList=JSONObject(temp,ChannelCategoryList.class);
	    		List<ChannelCategoryList> a=JSON.parseArray(temp, ChannelCategoryList.class);
	    		 
	    	
	    		
	    	}
	 };
	    	*/
	
}
