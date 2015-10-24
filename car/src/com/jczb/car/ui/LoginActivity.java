package com.jczb.car.ui;

import java.util.HashMap;
import java.util.Map;





import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;
import com.jczb.car.api.ApiClient;
import com.jczb.car.bean.SiteUser;
import com.jczb.car.bean.User;
import com.jczb.car.common.StringUtils;
import com.jczb.car.common.UIHelper;

/**
 * 登陆页面处理
 * 
 * @author 吴利昌
 * @date 2015-9-1上午10:20:08
 */
public class LoginActivity extends Activity implements OnClickListener {

	TextView tvRegister;
	
	
	/** 用户名 */
	private EditText etUserName;
	/** 密码 */
	private EditText etPassWord;
	/** 登陆 */
	private Button btLogin;
	/** 登陆Intent */
	private Intent loginIntent;
	/** 用户实体 */
	private SiteUser user;
	/** 登录时访问服务器端时传参用的集合 */
	private Map<String, Object> params = new HashMap<String, Object>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置登录页面
		setContentView(R.layout.login);
		
		findViewById();

		// 初始化页面控件
		initView();
		
		/*跳转到注册页面*/
		tvRegister.setOnClickListener(this);

		// 给控件添加数据，不用每次都输入了，方便测试----记得删除
		etUserName.setText("lc");
		etPassWord.setText("lc");

	}

	private void findViewById() {
		// TODO Auto-generated method stub
		tvRegister=(TextView) findViewById(R.id.register_id);
		
	}

	/**
	 * 初始化页面中的控件和监听事件
	 * 
	 * @user 吴利昌
	 * @date 2015-9-1 上午10:30:41
	 */
	private void initView() {

		// 初始化控件
		etUserName = (EditText) findViewById(R.id.et_username_id);
		etPassWord = (EditText) findViewById(R.id.et_password_id);
		btLogin = (Button) findViewById(R.id.bt_login_id);

		// 对具有点击事件的控件设置监听
		btLogin.setOnClickListener(this);

	}

	/**
	 * 实现登陆跳转等其他点击事件
	 */
	@Override
	public void onClick(View v) {

		// 根据id判断单击的是哪个控件，固定写法
		switch (v.getId()) {
		case R.id.bt_login_id:
			String userName = etUserName.getText().toString().trim();
			String pwd = etPassWord.getText().toString().trim();
			//判断输入
			if(StringUtils.isEmpty(userName)){
				UIHelper.ToastMessage(v.getContext(), getString(R.string.msg_login_username_null));
				return;
			}
			if(StringUtils.isEmpty(pwd)){
				UIHelper.ToastMessage(v.getContext(), getString(R.string.msg_login_pwd_null));
				return;
			}
			loginNew(userName,pwd);
			break;
		case R.id.register_id:
			Intent intentRegister = new Intent(this,RegisterActivity.class);
		    startActivity(intentRegister);
			break;

		default:
			break;
		}

	}
	 
    //登录验证
    private void loginNew(final String account, final String pwd) {
		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if(msg.what == 1){
					User user = (User)msg.obj;
					if(user != null){
						//清空原先cookie
						ApiClient.cleanCookie();
						//发送通知广播
						//UIHelper.sendBroadCast(LoginDialog.this, user.getNotice());
						//提示登陆成功
						UIHelper.ToastMessage(LoginActivity.this, R.string.msg_login_success);

						finish();
					}
				}else if(msg.what == 0){
					
					UIHelper.ToastMessage(LoginActivity.this, getString(R.string.msg_login_fail)+msg.obj);
				}else if(msg.what == -1){
				
					((AppException)msg.obj).makeToast(LoginActivity.this);
				}
			}
		};
		new Thread(){
			public void run() {
				Message msg =new Message();
				try {
					AppContext ac = (AppContext)getApplication(); 
	                User user = ac.loginVerify(account, pwd);
	              
	                if(user != null)
	                {
	                	ac.saveLoginInfo(user);//保存登录信息
	                	msg.what = 1;//成功
	                	msg.obj = user;
	                }
	                else
	                {
	                	ac.cleanLoginInfo();//清除登录信息
	                	msg.what = 0;//失败
	                	msg.obj = "失败";
	                }
 
	            } catch (AppException e) {
	            	e.printStackTrace();
			    	msg.what = -1;
			    	msg.obj = e;
	            }
				handler.sendMessage(msg);
			}
		}.start();
    }
	/**
	 * 登陆方法
	 * 
	 * @return
	 * @user 吴利昌
	 * @date 2015-9-1 上午11:08:15
	 */
	public void login() {

		if (isUserNameAndPwdValid()) {

			// 获取控件的值
			String userName = etUserName.getText().toString().trim();
			String pwd = etPassWord.getText().toString().trim();

			// 登录传递参数
			params.put("username", userName);
			params.put("password", pwd);

			// 声明
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);

					// 需要根据线程中传递的msg中what的返回值进行判断
					switch (msg.what) {
					case 1:
						Toast.makeText(LoginActivity.this, "登录成功!",
								Toast.LENGTH_SHORT).show();

						// 将是否首次登录的值修改为false
						SharedPreferences loginXml = getSharedPreferences(
								"loginXml", 0);
						Editor editor = loginXml.edit();
						boolean isFirst = false;
						editor.putBoolean("login", isFirst);
						editor.commit();

						// 获取用户Id
						SiteUser siteUser = (SiteUser) msg.obj;
						int userId = siteUser.getId();

//						// 带着用户id进行跳转
//						loginIntent = new Intent();
//						loginIntent.putExtra("userId", userId);
//						loginIntent.setClass(LoginActivity.this, Mine.class);
//						startActivity(loginIntent);
						finish();

						break;
					case -1:
						Toast.makeText(LoginActivity.this, "登录失败!",
								Toast.LENGTH_SHORT).show();
						break;
					case 0:
						Toast.makeText(LoginActivity.this, "哎呀,出错了..", Toast.LENGTH_SHORT).show();
						break;
					default:
						break;
					}
				}
			};

			// 开启一个登录子线程
			Thread loginThread = new Thread(new Runnable() {

				@Override
				public void run() {
					Message msg = new Message();

					// 获取全局对象Application
					AppContext appContext = (AppContext) getApplication();

					try {
						// 获取服务器数据
						user = appContext.getUser(params, true, 1);

						// 返回SiteUser则将消息的what值为1，否则为-1
						if (user != null) {
							msg.what = 1;
							msg.obj = user;

							// //生成userInfo.xml文件并将用户的信息以键值对的形式保存
							// //TODO:注意：目前使用的明文！！！没有加密。
							// SharedPreferences userInfo =
							// getSharedPreferences("userInfo", 0);
							// Editor editor = userInfo.edit();
							// String username = user.getUserName();
							// String nickname = user.getNickname();

						} else {
							msg.what = -1;
						}

					} catch (AppException e) {
						msg.what = 0;
						e.printStackTrace();
					}
					handler.sendMessage(msg);

				}
			});
			// 启动子线程
			loginThread.start();

		}
	}

	/**
	 * 判断用户名和密码是否为空
	 * 
	 * @return
	 * @user 吴利昌
	 * @date 2015-9-1 上午11:10:41
	 */
	public boolean isUserNameAndPwdValid() {

		if (etUserName.getText().toString().trim().equals("")) {
			Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
			return false;
		} else if (etPassWord.getText().toString().trim().equals("")) {
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
}
