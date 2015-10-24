package com.jczb.car.ui;

import java.util.HashMap;
import java.util.Map;

import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;
import com.jczb.car.bean.SiteUser;

import android.R.string;
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

public class EditNicknameActivity extends Activity implements OnClickListener {
	
   /** 原昵称*/
   private EditText etOldNickname;
   /** 新呢称*/
   private EditText etNewNickname;
   /** 确认新昵称*/
   private EditText etConfirmNewNickname;
   /**确定修改新呢称 */
   private TextView tvConfirmEditNickname;
   /**修改昵称的返回按钮 */
   private ImageButton ibEditNickName;
   
   /** 获取全局对象Application */
	AppContext appContext = (AppContext) getApplication();
	/** 登录时访问服务器端时传参用的集合 */
	private Map<String, Object> params = new HashMap<String, Object>();
	/** 修改昵称是否成功 */
	private boolean EditStatus;
   
   
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置加载页面
        setContentView(R.layout.edit_nickname);
        //初始化页面控件
        initView();
  }
/**
 * 初始化控件，并设置相应的点击监听事件 
 * @user 丁国华
 * @date 2015年9月2日 09:11:40
 */

	private void initView() {
		// TODO Auto-generated method stub
		//初始化控件
		etOldNickname=(EditText) findViewById(R.id.tv_oldNickname);
		etNewNickname=(EditText) findViewById(R.id.tv_newNickname);
		etConfirmNewNickname=(EditText) findViewById(R.id.tv_confirmNickname);
		tvConfirmEditNickname=(TextView)findViewById(R.id.nickname_confirm);
		ibEditNickName=(ImageButton)findViewById(R.id.edit_nickname_back);
		//初始化点击的监听事件
		tvConfirmEditNickname.setOnClickListener(this);
		ibEditNickName.setOnClickListener(this);
		
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.nickname_confirm:
			eidtNickName();
			break;
		case R.id.edit_nickname_back:
			this.finish();
			break;
		

		default:
			break;
		}
		
	}
	
	public void eidtNickName() {

		if (isoldNicknameandnewNickname()) {

			String new_nickname = etNewNickname.getText().toString().trim();

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
				String nickname = siteUser.getUserName();
				String userid = siteUser.getId() + "";

				// Map集合添加需要的参数
				
				params.put("nickname", nickname);
				params.put("id", userid);

				// 定义一个等待
				final Handler handler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						super.handleMessage(msg);

						// 需要根据线程中传递的msg中what的返回值进行判断
						switch (msg.what) {
						case 1:
							Toast.makeText(EditNicknameActivity.this,
									"昵称修改成功!", Toast.LENGTH_SHORT).show();
							// 跳转到登录页面
//							Intent intent = new Intent();
//							intent.setClass(EditNicknameActivity.this,
//									LoginActivity.class);
//							startActivity(intent);
//							finish();

							break;
						case -1:
							Toast.makeText(EditNicknameActivity.this,
									"昵称修改失败!", Toast.LENGTH_SHORT).show();
							break;
						case 0:
							Toast.makeText(EditNicknameActivity.this,
									"哎呀,出错啦...", Toast.LENGTH_SHORT).show();
						default:
							break;
						}
					}
			};
			
			// 开启一个登录子线程
			Thread nicknameThread = new Thread(new Runnable() {

				@Override
				public void run() {
					Message msg = new Message();

					try {
						// 获取服务器数据,是否修改成功
						EditStatus = appContext.updateNickname(params);

						// 返回true则将消息的what值为1，否则为-1,出现系统错误则返回0
						if (EditStatus) {
							msg.what = 1;
							msg.obj = EditStatus;

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
			nicknameThread.start();

		}

	}

}
			
	/**
	 * 判断原昵称和新昵称是否为空
	 * 
	 * @return
	 * @user 丁国华
	 * @date 2015年9月1日 19:33:54
	 */		
			
			public boolean isoldNicknameandnewNickname() {

				// 获取控件输入的值
				String oldNickname = etOldNickname.getText().toString().trim();
		        String newNickname = etNewNickname.getText().toString().trim();
				String confirmNewNickname = etConfirmNewNickname.getText().toString().trim();

				if (oldNickname.equals("")) {
					Toast.makeText(this, "原昵称不能为空", Toast.LENGTH_SHORT).show();
					return false;
				}

				if (newNickname.equals("")) {
					Toast.makeText(this, "新昵称不能为空", Toast.LENGTH_SHORT).show();
					return false;
				}

				if (!confirmNewNickname.equals(newNickname)) {
					Toast.makeText(this, "两次昵称不一致", Toast.LENGTH_SHORT).show();
					return false;
				}

				return true;
			}

}
