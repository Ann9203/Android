package com.jczb.car.ui;

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

/**
 * 忘记密码功能页面
 * @author 吴利昌
 * @date 2015-9-4上午11:30:54
 */
public class ForgetPasswordActivity extends Activity implements OnClickListener {
	
	//引用控件
	private EditText etUserName;
	private Button btResetPwd; 
	
	private Intent intent; 
	/**验证码*/
	private static String valicationCode;  
	/**手机号或者邮箱*/
	private String username; 
	
	private static boolean isComplete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.forget_assword);
		
		initView();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_resetpassword_id:
			username = etUserName.getText().toString().trim();
			if (username.equals("")) {
				Toast.makeText(this, "请您填写手机号码或邮箱!", Toast.LENGTH_SHORT).show();
			}else {
				
				//开启线程，获得验证码
				Thread thread = new Thread(sRunnable);
				thread.start();
				
//				//TODO：判断线程是否执行完毕---此处代码应该不会执行，线程没执行完成就跳过去了。。。暂时没找到好的解决方法
//				if (isComplete) {
					
//				}
			}
			break;

		default:
			break;
		}
	}
	
	/**
	 * 初始化控件和控件的监听事件
	 * 
	 * @user 吴利昌
	 * @date 2015-9-4 上午11:43:33
	 */
	public void initView(){
		etUserName = (EditText)findViewById(R.id.et_phoneNumOrEmail_id);
		btResetPwd = (Button)findViewById(R.id.bt_resetpassword_id);
		btResetPwd.setOnClickListener(this);
	}
	
	//--------------------------------获取验证码线程处理过程--开始----------------------------------
//		/**
//		 * 自定义一个静态的具有弱引用的Handler，解决内存泄漏的问题，注册使用
//		 */
//		private static class MyHandler extends Handler {
//			// 持有对本外部类的弱引用
//			private final WeakReference<ForgetPasswordActivity> mActivity;
//
//			public MyHandler(ForgetPasswordActivity activity) {
//				mActivity = new WeakReference<ForgetPasswordActivity>(activity);
//			}
			Handler mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				
//				// 获取上下文对象
//				ForgetPasswordActivity activity = mActivity.get();
//				if (activity != null) {
					switch (msg.what) {
					case 1:
						valicationCode = (String)msg.obj;
						//跳转
						intent = new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class);
						intent.putExtra("username", username);
						intent.putExtra("valicationCode", valicationCode);
						startActivity(intent);
						finish();
						break;
					case -1:
						Toast.makeText(ForgetPasswordActivity.this, "获取验证码失败!", Toast.LENGTH_SHORT).show();
						break;
					case 0:
						Toast.makeText(ForgetPasswordActivity.this, "哎呀,出错啦..", Toast.LENGTH_SHORT).show();
						break;
					default:
						break;
					}
//				}
			}};
		
//		/**实例化自定义的handler*/
//		private final MyHandler mHandler = new MyHandler(this);
		
		/**自定义子线程*/
		private Runnable sRunnable = new Runnable() {
			@Override
			public void run() {
				Message msg = new Message();

				// 获取全局对象Application
				AppContext appContext = (AppContext) getApplication();
				//接口所需参数
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("username", username);
				
				try {
					// 获取服务器数据
					String code = appContext.getCode(params);

					// 返回值非空则将消息的what值为1，否则what为-1，异常为0
					if (code.equals("")) {
						msg.what = -1;
					} else {
						msg.what = 1;
						msg.obj = code;
					}

				} catch (AppException e) {
					msg.what = 0;
					e.printStackTrace();
				}
				mHandler.sendMessage(msg);
			}
		};

		//-------------------------------获取验证码线程处理过程---完成-----------------------------------

}
