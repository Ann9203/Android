package com.jczb.car.ui;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
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
import com.jczb.car.bean.SiteUser;

/**
 * 重置密码功能页面
 * 
 * @author 吴利昌
 * @date 2015-9-4上午11:33:55
 */
public class ResetPasswordActivity extends Activity implements OnClickListener {

	// 声明控件
	private Button btConfirmModifyPwd;
	private EditText etValicationCode;
	private EditText etNewPassword;
	private String userName;
	private String serverValicationCode;
	private String newPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.reset_password);

		initView();

		// 获取ForgetPassowrdActivity传过来的参数
		Intent intent = getIntent();
		userName = intent.getStringExtra("username");
		serverValicationCode = intent.getStringExtra("valicationCode");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_confirm_modifypwd_id:
			modifyPwd();
			break;
		default:
			break;
		}
	}

	/**
	 * 修改密码
	 * 
	 * @user 吴利昌
	 * @date 2015-9-5 上午12:27:06
	 */
	private boolean modifyPwd() {
		
		// 首先验证输入是否为空
		if (etValicationCode.getText().toString().trim().equals("")) {
			Toast.makeText(this, "请输入验证码!", Toast.LENGTH_SHORT).show();
			return false;
		} else if (etNewPassword.getText().toString().trim().endsWith("")) {
			Toast.makeText(this, "请输入新密码!", Toast.LENGTH_SHORT).show();
			return false;
		} else {
			// 如果验证码输入正确，则开启修改密码的线程
			if (!etValicationCode.getText().toString().trim()
					.equals(serverValicationCode)) {
				Toast.makeText(this, "验证码不正确!", Toast.LENGTH_SHORT).show();
				return false;
			} else {
				Thread thread = new Thread(sRunnable);
				thread.start();
			}
		}

		return true;
	}

	/**
	 * 初始化控件和监听事件
	 * 
	 * @user 吴利昌
	 * @date 2015-9-4 上午11:49:15
	 */
	public void initView() {

		btConfirmModifyPwd = (Button) findViewById(R.id.bt_confirm_modifypwd_id);
		etNewPassword = (EditText) findViewById(R.id.et_inputnewpwd_id);
		etValicationCode = (EditText) findViewById(R.id.et_valicationcode_id);

		btConfirmModifyPwd.setOnClickListener(this);
	}

	// --------------------------------修改密码线程处理过程--开始----------------------------------
	/**
	 * 自定义一个静态的具有弱引用的Handler，解决内存泄漏的问题，注册使用
	 */
	private static class MyHandler extends Handler {
		// 持有对本外部类的弱引用
		private final WeakReference<ResetPasswordActivity> mActivity;

		public MyHandler(ResetPasswordActivity activity) {
			mActivity = new WeakReference<ResetPasswordActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {

			// 获取上下文对象
			ResetPasswordActivity activity = mActivity.get();
			if (activity != null) {
				switch (msg.what) {
				case 1:
					Toast.makeText(activity, "修改密码成功!", Toast.LENGTH_SHORT)
							.show();
					break;
				case -1:
					Toast.makeText(activity, "修改密码失败!", Toast.LENGTH_SHORT)
							.show();
					break;
				case 0:
					Toast.makeText(activity, "哎呀,出错啦..", Toast.LENGTH_SHORT)
							.show();
					break;
				default:
					break;
				}
			}
		}
	}

	/** 实例化自定义的handler */
	private final MyHandler mHandler = new MyHandler(this);

	/** 自定义子线程 */
	private Runnable sRunnable = new Runnable() {
		@Override
		public void run() {
			Message msg = new Message();

			// 获取全局对象Application
			AppContext appContext = (AppContext) getApplication();

			// 获取用户Id
			SiteUser siteUser = appContext.getCacheUser();
			String userId = siteUser.getId() + "";

			// 接口所需参数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", userName);
			params.put("password", newPassword);
			params.put("Id", userId);

			try {
				// 获取服务器数据
				boolean status = appContext.updatePassword(params);

				// 返回值为true将消息的what值为1，否则what为-1，异常为0
				if (status) {
					msg.what = 1;
					msg.obj = status;
				} else {
					msg.what = 1;
				}

			} catch (AppException e) {
				msg.what = 0;
				e.printStackTrace();
			}
			mHandler.sendMessage(msg);
		}
	};

	// -------------------------------修改密码线程处理过程---完成-----------------------------------

}
