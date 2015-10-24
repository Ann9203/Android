package com.example.day04_picturelook;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private EditText et_url;
	private ImageView im_picture;
	private Button bt_download;
	private Context mecontext=this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ȡ�ؼ���id
		et_url = (EditText) findViewById(R.id.et_url);
		im_picture = (ImageView) findViewById(R.id.iv_picture);
		bt_download = (Button) findViewById(R.id.bt_download);

		// ��������¼�
		bt_download.setOnClickListener(this);

	}

	// Handler handler=new Handler(){
	//
	// public void handleMessage(android.os.Message msg)
	// {
	// msg.obj
	// }
	//
	//
	// };

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub

			Bitmap img = (Bitmap) msg.obj;
			// ��ӵ�ͼƬ��ȥ
			im_picture.setImageBitmap(img);

		};
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final String path = et_url.getText().toString().trim();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// ����Url
				try {
					URL url = new URL(path);
					HttpURLConnection open = (HttpURLConnection) url
							.openConnection();
					// ���û�ȡ�ķ���
					open.setRequestMethod("GET");
					// �����ӳٵ�ʱ��
					open.setReadTimeout(10 * 1000);
					// ����״̬��
					int code = open.getResponseCode();
					if (code == 200) {
						InputStream in = open.getInputStream();
						// ת����bitmp��ʽ��
						Bitmap img = BitmapFactory.decodeStream(in);
						// ������Ϣ����
						Message msg = new Message();
						msg.obj = img;
						handler.sendMessage(msg);
						in.close();
						open.disconnect();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}).start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
