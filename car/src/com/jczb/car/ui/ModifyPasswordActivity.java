package com.jczb.car.ui;

import java.util.HashMap;
import java.util.Map;

import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;
import com.jczb.car.bean.SiteUser;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 修改密码
 * 
 * @author 丁国华
 * @date 2015年9月1日 16:57:53
 */
public class ModifyPasswordActivity extends Activity implements OnClickListener {

	/** 原密码 */
	private EditText etOldPassword;
	/** 新密码 */
	private EditText etNewPassword;
	/** 确认密码 */
	private EditText etNewPassword2;
	/** 确认修改 */
	private TextView tvConfirmModify;
	/** 修改密码的返回按钮*/
	private ImageButton ibModifyPassword;
	/** 获取全局对象Application */
	AppContext appContext = (AppContext) getApplication();
	/** 登录时访问服务器端时传参用的集合 */
	private Map<String, Object> params = new HashMap<String, Object>();
	/** 修改密码是否成功 */
	private boolean modifyStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置对应的修改页面
		setContentView(R.layout.modify_password);

		// 初始化控件
		initView();

	}

	/**
	 * 初始化控件，并设置相应的点击监听事件
	 * 
	 * @user 吴利昌
	 * @date 2015-9-1 下午5:47:11
	 */
	private void initView() {

		// 初始化控件
		etOldPassword = (EditText) findViewById(R.id.et_oldpassword_id);
		etNewPassword = (EditText) findViewById(R.id.et_newpassword_id);
		etNewPassword2 = (EditText) findViewById(R.id.et_newpassword2_id);
		tvConfirmModify = (TextView) findViewById(R.id.tv_confirmPwd_id);
        ibModifyPassword=(ImageButton) findViewById(R.id.modify_password_back);
		// 初始化点击的监听事件
		tvConfirmModify.setOnClickListener(this);
		ibModifyPassword.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_confirmPwd_id:
			modifyPassword();
			break;
		case R.id.modify_password_back:
			this.finish();
		    break;

		default:  
			break;
		}
	}

	/**
	 * 修改密码
	 * 
	 * @user 吴利昌
	 * @date 2015-9-1 下午6:29:16
	 */
	public void modifyPassword() {

		if (isPasswordValid()) {

			String new_password = etNewPassword.getText().toString().trim();

			// 从缓存中获取用户信息
			SiteUser siteUser = null;
			try {
				siteUser = appContext.getCacheUser();
			} catch (Exception e) {
				Log.e("获取SiteUser的缓存信息失败---->>>>>", e.getMessage());
				Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
			}

			// 缓存不为空，则取用户名和id
			if (siteUser != null) {
				String username = siteUser.getUserName();
				String userid = siteUser.getId() + "";

				// Map集合添加需要的参数
				params.put("password", new_password);
				params.put("username", username);
				params.put("id", userid);

				// 定义一个等待
				final Handler handler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						super.handleMessage(msg);

						// 需要根据线程中传递的msg中what的返回值进行判断
						switch (msg.what) {
						case 1:
							Toast.makeText(ModifyPasswordActivity.this,
									"修改成功,请重新登录!", Toast.LENGTH_SHORT).show();
							// 跳转到登录页面
							Intent intent = new Intent();
							intent.setClass(ModifyPasswordActivity.this,
									LoginActivity.class);
							startActivity(intent);
							finish();

							break;
						case -1:
							Toast.makeText(ModifyPasswordActivity.this,
									"修改失败!", Toast.LENGTH_SHORT).show();
							break;
						case 0:
							Toast.makeText(ModifyPasswordActivity.this,
									"哎呀,出错啦...", Toast.LENGTH_SHORT).show();
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

						try {
							// 获取服务器数据,是否修改成功
							modifyStatus = appContext.updatePassword(params);

							// 返回true则将消息的what值为1，否则为-1,出现系统错误则返回0
							if (modifyStatus) {
								msg.what = 1;
								msg.obj = modifyStatus;

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

	}

	/**
	 * 判断密码是否有效 包括1.判断是否为空2.两次输入的密码是否一致
	 * 
	 * @return
	 * @user 吴利昌
	 * @date 2015-9-1 下午6:35:12
	 */
	public boolean isPasswordValid() {

		// 获取控件输入的值
		String oldPassword = etOldPassword.getText().toString().trim();
		String newPassword = etNewPassword.getText().toString().trim();
		String newPassword2 = etNewPassword2.getText().toString().trim();

		if (oldPassword.equals("")) {
			Toast.makeText(this, "原密码不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}

		if (newPassword.equals("")) {
			Toast.makeText(this, "新密码不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}

		if (!newPassword2.equals(newPassword)) {
			Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
			return false;
		}

		return true;
	}

}
