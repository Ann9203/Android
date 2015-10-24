package com.example.day04_logingetorpost;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima.utils.Utils;
import com.itheima.utils.streamToString;

public class MainActivity extends Activity implements OnClickListener {

	private EditText et_username;
	private EditText et_pwd;
	private CheckBox ch_rempwd;
	private Button bt_login;
	private Context mContext;
	private String username;
	private String pwd;
	private boolean ischecked;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String result = (String) msg.obj;
			if (result.contains("success")) {
				if (ischecked) {
					boolean result1 = Utils.saveUserInfo(mContext, username,
							pwd);
					if (result1) {
						Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT)
								.show();

					} else {
						Toast.makeText(mContext, "保存失败", Toast.LENGTH_SHORT)
								.show();

					}
				} else {
					Toast.makeText(mContext, "登陆成功，无需记住密码", Toast.LENGTH_SHORT)
							.show();

				}

			} else {
				Toast.makeText(mContext, "登陆失败", 0).show();

			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取所有的控件
		et_username = (EditText) findViewById(R.id.et_username);
		et_pwd = (EditText) findViewById(R.id.et_pwd);
		ch_rempwd = (CheckBox) findViewById(R.id.cb_rempwd);
		bt_login = (Button) findViewById(R.id.bt_login);
		bt_login.setOnClickListener(this);
		
		//做密码和用户名回显
		Map<String,String>map=Utils.getUserInfo(mContext);
		if(map!=null)
		{
			String username=map.get("username");
			String pwd=map.get("pwd");
			et_username.setText(username);
			et_pwd.setText(pwd);
			ch_rempwd.setChecked(true);
		}

	}

	public void login() {
		username = et_username.getText().toString().trim();
		pwd = et_pwd.getText().toString().trim();
		ischecked = ch_rempwd.isChecked();
		if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
			Toast.makeText(mContext, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
		}
		// 检查数据的正确性
		loginForPost(username, pwd);
	}

	// 使用get的方式提交
	private void loginForGet(final String username, final String pwd) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String path = "http://192.168.56.1:8090/itheima74/servlet/LoginServlet?username="
						+ username + "&pwd=" + pwd;
				try {
					URL url = new URL(path);
					HttpURLConnection openConnection = (HttpURLConnection) url
							.openConnection();
					openConnection.setRequestMethod("GET");
					openConnection.setConnectTimeout(10 * 1000);
					if (openConnection.getResponseCode() == 200) {
						InputStream in = openConnection.getInputStream();
						String result = streamToString.streamAndString(in);
						Message msg = Message.obtain();
						msg.obj = result;
						handler.sendMessage(msg);
						in.close();
						openConnection.disconnect();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}).start();

	}

	// 使用post的方式提交
	private void loginForPost(final String username, final String pwd) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				String path = "http://192.168.56.1:8090/itheima74/servlet/LoginServlet";
				try {
					URL url = new URL(path);
					HttpURLConnection openConnection = (HttpURLConnection) url
							.openConnection();
					// 设置一些请求的消息头
					openConnection.setRequestMethod("POST");
					openConnection.setConnectTimeout(10 * 1000);
					String content = "userName" + username + "&pwd" + pwd;
					openConnection.setRequestProperty("Content-Length",
							String.valueOf(content.length()));
					openConnection.setRequestProperty("Cache-Control",
							"max-age=0");
					openConnection.setRequestProperty("Origin",
							"http://192.168.56.1:8090");
					openConnection.setRequestProperty("Content-type",
							"application/x-www-form-urlencoded");
					// 设置当前的URLConnection可以上传的主题内容
					openConnection.setDoOutput(true);
					// 获取URLConnection的一个写入流的将主体内容写入
					openConnection.getOutputStream().write(content.getBytes());
					if (openConnection.getResponseCode() == 200) {
						InputStream in = openConnection.getInputStream();
						String result = streamToString.streamAndString(in);
						Message message = Message.obtain();
						message.obj = result;
						handler.sendMessage(message);
						in.close();
						openConnection.disconnect();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}).start();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.bt_login:
				login();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
