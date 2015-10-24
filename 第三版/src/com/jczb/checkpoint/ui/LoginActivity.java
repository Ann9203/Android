package com.jczb.checkpoint.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.jczb.checkpoint.R;
import com.jczb.checkpoint.common.AgentApi;
import com.jczb.checkpoint.common.Constants;
import com.jczb.checkpoint.common.NetWorkUtil;
import com.jczb.checkpoint.manager.InitDbManager;
import com.jczb.checkpoint.manager.UserManager;
import com.jczb.checkpoint.model.User;
import com.jczb.checkpoint.model.UserVo;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 登录页面
 * 
 * @author wlc
 * @date 2015-3-13
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

	// 变量声明
	private EditText etUserName;
	private EditText etPassWord;
	private Button btLogin;
	private Button btInitDb;
	private Intent loginIntent;
	private ImageView loginView; // 登陆背景图

	private InitDbManager initDbManager;
	private UserManager userManager;
	private User user;
	private NetWorkUtil networkUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		findViewById();
		initView();

		/*********************** 判断是否首次使用软件-开始 ****************************/
		// 写入简键值对
		SharedPreferences settings = getSharedPreferences("appFirst", 0);
		// 设置是否首次使用标识-true
		Boolean loginFirst = settings.getBoolean("APP_FIRST", true);
		if (loginFirst) {
			// 改变标识-false
			settings.edit().putBoolean("APP_FIRST", false).commit();
			Toast.makeText(LoginActivity.this, "首次使用需要初始化数据，请等待...",
					Toast.LENGTH_LONG).show();
			// 调用相关方法初始化数据库
			initDbManager = new InitDbManager();
			initDbManager.Init(this);
		} else {
			// 非初始化代码
			// Toast.makeText(LoginActivity.this, "不是第一次",
			// Toast.LENGTH_SHORT).show();
		}
		/*********************** 判断是否首次使用软件-结束 ****************************/

	}

	@Override
	protected void findViewById() {
		etUserName = (EditText) findViewById(R.id.loginaccount_id);
		etPassWord = (EditText) findViewById(R.id.loginpassword_id);
		btLogin = (Button) this.findViewById(R.id.login);
		loginView = (ImageView) this.findViewById(R.id.login_top_logo);
	}

	@Override
	protected void initView() {
		// 对登录按钮设置监听，方法由下面的Onclick实现
		btLogin.setOnClickListener(this);
		// btInitDb.setOnClickListener(this);

	}

	@Override
	/**
	 * 实现登录按钮的跳转
	 */
	public void onClick(View v) {
		// 根据id判断单击的是哪个控件，固定写法
		switch (v.getId()) {
		case R.id.login:
			login();
			break;
		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		}
		return false;
	}

	/**
	 * 登录方法
	 */
	public boolean login() {

		if (isUserNameAndPwdValid()) {

			String userName = etUserName.getText().toString().trim();
			String pwd = etPassWord.getText().toString().trim();
			userManager = new UserManager(this);
			networkUtil = new NetWorkUtil();

			// 有网的情况下优先进行联网登录
			// 1.离线登录，离线不存在则进行联网登录
			if (!networkUtil.isNetworkAvailable(this)) {
				// 1.1 提示联网在不联网的情况下，查询本地数据用户是否存在
				List<User> users = userManager.getUserByNameAndPwd(userName,
						pwd);
				// 1.1.1如果存在则登录系统，
				if (users != null && users.size() > 0) {

					loginIntent = new Intent();
					loginIntent
							.setClass(LoginActivity.this, MainActivity.class);
					loginIntent.putExtra("username", userName);
					startActivity(loginIntent);
					finish();
				}
				// 1.1.2 如果不存在则提示用户联网查询
				else {
					Toast.makeText(this,
							getString(R.string.connect_internet_tip),
							Toast.LENGTH_SHORT).show();
					return false;
				}

			}
			// 2 联网登录，http请求并返回数据
			else {
				mHandler = new Handler() {
					public void handleMessage(Message msg) {
						if (msg.what == 1) {
							if (msg.obj != null) {
								// 将解析的对象强制转换为UserVo
								UserVo userVo = (UserVo) msg.obj;
								// 如果服务器端返回的数据为true，说明存在该用户名和密码
								if (userVo.getResult().equals("true")) {
									// 2.1删除本地用户
									List<User> users = userManager
											.getUserByNameAndPwd(etUserName
													.getText().toString()
													.trim(), etPassWord
													.getText().toString()
													.trim());
									userManager.Delete(userVo.getUser().get(0)
											.getUserName());

									user = new User();
									// 2.2添加服务器端用户到本地用户表中
									user.setUserName(userVo.getUser().get(0)
											.getUserName());
									user.setPassWord(userVo.getUser().get(0)
											.getPassWord());
									user.setRealName(userVo.getUser().get(0)
											.getRealName());
									user.setServerid(userVo.getUser().get(0)
											.getServerid());
									// 删除本地已经存在的用户名
									userManager.Insert(user);

									// 2.3跳转
									String userName = etUserName.getText()
											.toString().trim();
									// 将username保存起来，便于其他多个Activity接收
									GlobalVariable userApplication = (GlobalVariable) getApplication();
									userApplication.setUserName(userName);
									loginIntent = new Intent();
									loginIntent.setClass(LoginActivity.this,
											MainActivity.class);
									startActivity(loginIntent);
									finish();
								} else {
									// 提示不能登录
									// makeText方法的第一个参数不能只写this了
									Toast.makeText(LoginActivity.this,
											getString(R.string.login_failed),
											Toast.LENGTH_SHORT).show();
								}
							}

						}
					}
				};

				/**
				 * 开辟一个新线程，用于异步发送和接收数据请求 并对返回的Json数据进行解析
				 */
				new Thread() {
					public void run() {
						Message msg = new Message();
						try {
							String userName = etUserName.getText().toString()
									.trim();
							String pwd = etPassWord.getText().toString().trim();
							Map<String, String> parmas = new HashMap<String, String>();
							parmas.put("username", userName);
							parmas.put("password", pwd);
							// 要发送的数据和访问的地址
							String result = AgentApi.dopost(parmas,
									Constants.LOGIN_URL);
							// 解析返回的Json数据为对应的对象
							UserVo userVo = JSON.parseObject(result,
									UserVo.class);
							msg.what = 1;
							// 将被解析成的对象赋值
							msg.obj = userVo;
						} catch (Exception e) {
							e.printStackTrace();
							msg.what = -1;
							msg.obj = e;
						}
						mHandler.sendMessage(msg);
					}
				}.start();

			}
		}

		return false;
	}

	/**
	 * 判断用户名和密码是否有效
	 * 
	 * @return
	 */
	public boolean isUserNameAndPwdValid() {
		// 用户名和密码不得为空
		if (etUserName.getText().toString().trim().equals("")) {
			Toast.makeText(this, getString(R.string.name_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (etPassWord.getText().toString().trim().equals("")) {
			Toast.makeText(this, getString(R.string.pwd_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	/**
	 * 给下个Activity传参，用户名
	 */
	/*
	 * public void conveyUserName(){ Intent intent = new Intent();
	 * 
	 * intent.putExtra("username",etUserName.getText().toString().trim()); }
	 */
}