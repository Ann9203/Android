package com.jczb.car.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;
import com.jczb.car.bean.Content;
import com.jczb.car.bean.ContentVo;

public class PictureViewInfoActivity extends Activity implements OnClickListener {

	//声明需要用到的控件
	private TextView tvArticleTitle;
	private TextView tvArticleTime;
    private TextView tvArticleAuthor;
	private ImageView ivArticlePicture;
	private TextView tvArticleContent;
	
	/**解析发现接口用的实体类*/
	private ContentVo contentVo = null;
	/**传参用的集合 */
	private Map<String, Object> params = new HashMap<String, Object>();
	/**用来获得服务器接口的发现内容集合*/
	private List<Content> pictureViewInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_view_info);

		initView();

		// 启动子线程,获取服务器数据
		 new Thread(new pictureViewInfoThread()).start();
		 
		 
		 //ivArticlePicture.setImageURI(uri)(pictureViewInfo.get(0).getPath());
		 //ivArticlePicture.setImageBitmap(returnBitMap("http://www.sucaitianxia.com/d/file/20140115/small15f13cfbf1861bb77c5e7beb56f12a03.jpg"));
	}
	/**
	 * 处理图片的
	 */
	
	/*public Bitmap returnBitMap(String url) { 
		URL myFileUrl = null; 
		Bitmap bitmap = null; 
		try { 
		myFileUrl = new URL(url); 
		} catch (MalformedURLException e) { 
		e.printStackTrace(); 
		} 
		try { 
		HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection(); 
		conn.setDoInput(true); 
		conn.connect(); 
		InputStream is = conn.getInputStream(); 
		bitmap = BitmapFactory.decodeStream(is); 
		is.close(); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return bitmap; 
		} */
	
private void initView() {
		// TODO Auto-generated method stub
		//初始化控件
		tvArticleTitle=(TextView) findViewById(R.id.article_title_id);
 		tvArticleTime=(TextView) findViewById(R.id.article_time_id);
		tvArticleAuthor=(TextView) findViewById(R.id.article_author_id);
		ivArticlePicture=(ImageView) findViewById(R.id.article_picture_id);
		tvArticleContent=(TextView)findViewById(R.id.article_content_id);
}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	 /*子线程-解析数据*/
	private  Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case -1:
				Toast.makeText(PictureViewInfoActivity.this, "服务器连接失败!",
						Toast.LENGTH_SHORT).show();
				break;
			case -2:
				Toast.makeText(PictureViewInfoActivity.this, "哎呀，出错啦...",
						Toast.LENGTH_SHORT).show();
				break;				
			case 1:				
  				contentVo= (ContentVo)msg.obj;							
				pictureViewInfo = contentVo.getContent();
				tvArticleTitle.setText(pictureViewInfo.get(0).getTitle());
				tvArticleAuthor.setText(pictureViewInfo.get(0).getAuthor());
				tvArticleContent.setText(pictureViewInfo.get(0).getDetails());	
				tvArticleTime.setText(pictureViewInfo.get(0).getIssueTime().toString());
				
				

				//将拿到的json转换为数组
				//List<shoppingCart> ShoppingcartInfo = JSON.parseArray(temp,shoppingCart.class);
				
				
               break;
				
			default:
				break;
			}
		}
	};
	public class pictureViewInfoThread implements Runnable {
		public void run() {			
			Message msg = new Message();						
			
				Map<String, String> parmas = new HashMap<String, String>();
				parmas.put("Id", "1");
				parmas.put("type", "0");
					
				// 获取全局对象Application
				AppContext appContext = (AppContext) getApplication();	
				try {
					// 获取服务器数据
					contentVo = appContext.getImgWordContentDetail(params,true, 1);

					// 返回contentVo则将消息的what值为1，为空what为-1，异常为0
					if (contentVo != null) {
						msg.what = 1;
						msg.obj = contentVo;
				} else {
						msg.what = -1;
					}

				} catch (AppException e) {
					msg.what = 0;
					e.printStackTrace();
				}
				mHandler.sendMessage(msg);
			
		}
	}

}
