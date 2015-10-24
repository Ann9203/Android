package com.jczb.car.ui;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;

/**
 * 注册功能页面 我们实现了取消线程的机制，从而保证它不会泄露 onDestroy()常常被用来在Activity推出前取消线程
 * 
 * @author 吴利昌
 * @date 2015-9-3上午9:19:15
 */
public class RegisterActivity extends Activity implements OnClickListener {

	// 声明用到的页面控件
	private EditText etRegisterName;
	private EditText etCode;
	private EditText etPassword;
	private Button btCode;
	private Button btRegister;

	// 定义变量
	private String userName;
	private String passWord;
	
	/**客户端输入的验证码*/
	private String valicationCode;
	
	/**服务器端获取的验证码*/
	private static String serverValicationCode;

	/** 注册时所带的参数 */
	private Map<String, Object> registerParams = new HashMap<String, Object>();

	/** 获取验证码时所带的参数 */
	private Map<String, Object> codeParams = new HashMap<String, Object>();

	/** 注册是否成功 */
	private boolean regisgerStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.register);

		initView();
	}

	/**
	 * 初始化页面控件和事件
	 * 
	 * @user 吴利昌
	 * @date 2015-9-3 上午9:23:42
	 */
	public void initView() {
		// 初始化控件
		etRegisterName = (EditText) findViewById(R.id.et_register_username_id);
		etCode = (EditText) findViewById(R.id.et_register_code_id);
		etPassword = (EditText) findViewById(R.id.et_register_password_id);
		btCode = (Button) findViewById(R.id.bt_getcode_id);
		btRegister = (Button) findViewById(R.id.bt_register_id);
		// 初始化监听事件
		btCode.setOnClickListener(this);
		btRegister.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_getcode_id:
			getValidateCode();
			break;
		case R.id.bt_register_id:
			register();
			break;
		default:
			break;
		}

	}

	/**
	 * 获取验证码
	 * 
	 * @user 吴利昌
	 * @date 2015-9-3 下午3:26:55
	 */
	public boolean getValidateCode() {
		
		String name = etRegisterName.getText().toString().trim();
		String code = etCode.getText().toString().trim();
		if (name.equals("")) {
			Toast.makeText(this, "请输入用户名或手机号!", Toast.LENGTH_SHORT).show();
			return false;
		}else {
			userName = name;
			valicationCode = code;
			Thread codeThread = new Thread(codeRunnable);
			codeThread.start();
		}
		return true;
	}

	/**
	 * 注册
	 * 
	 * @user 吴利昌
	 * @date 2015-9-3 下午3:27:23
	 */
	public void register() {
		// 1.首先判断输入的值是否有效
		// 2.然后判断输入的验证码是否有效（防止没有点击获取验证码自己填的错误验证码)
		// 3.最后注册
		if (isValid()) {
			
			if (valicationCode.equals(serverValicationCode)) {
				Thread thread = new Thread(sRunnable);
				thread.start();
			}else {
				Toast.makeText(this, "输入的验证码不正确!", Toast.LENGTH_SHORT).show();
			}
			
		}
	}
	
	//--------------------------------获取验证码线程处理过程---开始-----------------------------
	/**
	 * 自定义一个静态的具有弱引用的Handler，解决内存泄漏的问题,本handler用来获取验证码
	 */
	private static class CodeHandler extends Handler {
		// 持有对本外部类的弱引用
		private final WeakReference<RegisterActivity> mActivity;

		public CodeHandler(RegisterActivity activity) {
			mActivity = new WeakReference<RegisterActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			
			// 获取上下文对象
			RegisterActivity activity = mActivity.get();
			if (activity != null) {
				switch (msg.what) {
				case 1:
					serverValicationCode = (String)msg.obj;
					activity.etCode.setText(serverValicationCode);
					break;
				case -1:
					Toast.makeText(activity, "获取验证码失败!", Toast.LENGTH_SHORT).show();
					break;
				case 0:
					Toast.makeText(activity, "哎呀,出错啦..", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		}
	}
	
	/**实例化自定义的handler*/
	private final CodeHandler codeHandler = new CodeHandler(this);
	
	/**定义获取验证码的子线程*/
	private Runnable codeRunnable = new Runnable() {
		@Override
		public void run() {
			Message msg = new Message();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", userName);
			// 获取全局对象Application
			AppContext appContext = (AppContext) getApplication();

			try {
				// 获取服务器数据
				String serverCode = appContext.getCode(map);

				// 返回true则将消息的what值为1，为false则what为-1，异常为0
				if (serverCode.equals("")) {
					msg.what = -1;
				} else {
					msg.what = 1;
					msg.obj = serverCode;
				}

			} catch (AppException e) {
				msg.what = 0;
				e.printStackTrace();
			}
			codeHandler.sendMessage(msg);
		}
	};
	
	//--------------------------------获取验证码线程处理过程----完成------------------------------

	//--------------------------------注册线程处理过程--开始----------------------------------
	/**
	 * 自定义一个静态的具有弱引用的Handler，解决内存泄漏的问题，注册使用
	 */
	private static class MyHandler extends Handler {
		// 持有对本外部类的弱引用
		private final WeakReference<RegisterActivity> mActivity;

		public MyHandler(RegisterActivity activity) {
			mActivity = new WeakReference<RegisterActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			
			// 获取上下文对象
			RegisterActivity activity = mActivity.get();
			if (activity != null) {
				switch (msg.what) {
				case 1:
					Toast.makeText(activity, "注册成功!", Toast.LENGTH_SHORT).show();
					activity.finish();
					break;
				case -1:
					Toast.makeText(activity, "注册失败!", Toast.LENGTH_SHORT).show();
					break;
				case 0:
					Toast.makeText(activity, "哎呀,出错啦..", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		}
	}
	
	/**实例化自定义的handler*/
	private final MyHandler mHandler = new MyHandler(this);
	
	/**自定义子线程*/
	private Runnable sRunnable = new Runnable() {
		@Override
		public void run() {
			Message msg = new Message();

			// 获取全局对象Application
			AppContext appContext = (AppContext) getApplication();

			try {
				// 获取服务器数据
				regisgerStatus = appContext.register(registerParams);

				// 返回true则将消息的what值为1，为false则what为-1，异常为0
				if (regisgerStatus) {
					msg.what = 1;
					msg.obj = regisgerStatus;
				} else {
					msg.what = -1;
				}

			} catch (AppException e) {
				msg.what = 0;
				e.printStackTrace();
			}
			mHandler.sendMessage(msg);
		}
	};

	//--------------------------------注册线程处理过程---完成-----------------------------------

	/**
	 * 注册之前判断数据是否为空
	 * 
	 * @return
	 * @user 吴利昌
	 * @date 2015-9-3 下午3:29:04
	 */
	public boolean isValid() {

		userName = etRegisterName.getText().toString().trim();
		valicationCode = etCode.getText().toString().trim();
		passWord = etPassword.getText().toString().trim();

		if (userName.equals("")) {
			Toast.makeText(this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
			return false;
		}

		if (valicationCode.equals("")) {
			Toast.makeText(this, "验证码不能为空!", Toast.LENGTH_SHORT).show();
			return false;
		}

		if (passWord.equals("")) {
			Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
			return false;
		} else if (passWord.length() < 7) {
			Toast.makeText(this, "密码至少6位!", Toast.LENGTH_SHORT).show();
			return false;
		}

		registerParams.put("username", userName);
		registerParams.put("password", passWord);

		return true;
	}
}
