package com.itheima.mobileSafe;

import com.itheima.Utils.MyMD5;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	private GridView gv_home_applications;
	private AlertDialog dialog;
	private EditText et_pwd;
	private EditText et_again;
	private SharedPreferences sp;
	private Editor edit;

	
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		sp=getSharedPreferences("config", MODE_PRIVATE);
		edit=sp.edit();
		//获取GridView实例
		gv_home_applications=(GridView)findViewById(R.id.gv_home_application);
		//GridView实例是和listView相同的
		gv_home_applications.setAdapter(new Applications());
		gv_home_applications.setOnItemClickListener(new OnItemClickListener() {

			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
	
				//根据选择哪一个，来跳转界面
				switch(position)
				{
					case 0:
						//主要来实现连个功能
						//如果是第一次访问就实现设置密码的功能
						//如果不是第一次访问，那么进入该页面就要输入本人的密码
						if(TextUtils.isEmpty(sp.getString("pwd", ""))){
							showSetPwdDialog();
							
						}else{
							showPwdDialog();
						}
						break;
					case 1:
						phoneNumSafe();
						break;
					case 2:
						Intent intent=new Intent(HomeActivity.this,SoftManagerActivity.class);
						startActivity(intent);
						break;
					case 3:
						Intent intentTask=new Intent(HomeActivity.this,TaskManagerActivity.class);
						startActivity(intentTask);
						break;
					case 5:
						Intent intentVirus =new Intent(HomeActivity.this,AntVirusActivity.class);
						startActivity(intentVirus);
						break;
					case 6:
						Intent intentClearCache=new Intent(HomeActivity.this,ClearCacheActivity.class);
						startActivity(intentClearCache);
						break;
					case 7:
						enterTools();
						break;
					//选择了第八个，就跳转setting界面
					case 8:
						enterSetting();
						break;
				}
			}

			
		});
	}
	/**
	 * 进入通讯保护界面
	 */
	public void  phoneNumSafe(){
		Intent intent =new Intent(HomeActivity.this,BlackNumActivity.class);
		startActivity(intent);
	}
	
	/**
	 * 写一个内部类，继承BaseAddapter
	 * @author 雪宝宝
	 *
	 */
	class Applications extends BaseAdapter{

		//获取个数
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 9;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view;
			//将图片的id设置成一个int的数组
			int[] images={R.drawable.safe, R.drawable.callmsgsafe, R.drawable.app,
					R.drawable.taskmanager, R.drawable.netmanager, R.drawable.trojan,
					R.drawable.sysoptimize, R.drawable.atools, R.drawable.settings};
			String[] dess={"手机防盗", "通讯卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理",
					"高级工具", "设置中心" };
			view=View.inflate(HomeActivity.this, R.layout.activity_application,null);
			//获取控件id
			ImageView iv_icon=(ImageView) view.findViewById(R.id.iv_application_icon);
			TextView tv_des=(TextView) view.findViewById(R.id.tv_application_des);
			iv_icon.setImageResource(images[position]);
			tv_des.setText(dess[position]);
			return view;
		}
		
		
	}
	/**
	 * 跳转到设置的界面
	 */
	public void enterSetting()
	{
		Intent intent=new Intent(this,SettingActivity.class);
		startActivity(intent);
	}
	
	
	/**
	 * 设置弹出密码设置的对话框
	 * 同事密码设置完毕之后要在保存数据的文件夹中写入第一次访问
	 * 
	 */
	public void showSetPwdDialog(){
		
		//创建弹出的对话框
		 AlertDialog.Builder builder=new Builder(this);
		builder.setCancelable(false);
		//添加一个布局文件在对话框中
		View view=View.inflate(getApplicationContext(), R.layout.activity_setpwd, null);
		
		 et_pwd=(EditText)view.findViewById(R.id.et_set_pwd);
		 et_again=(EditText)view.findViewById(R.id.et_set_comfirpwd);
		Button bt_ok=(Button)view.findViewById(R.id.bt_ok);
		Button bt_cancel=(Button)view.findViewById(R.id.bt_cansel);
		bt_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setPwd();
				dialog.dismiss();
				enterSafe();
			}
		});
		bt_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//点击取消的话就是对话框消失
				//为什么dialog对象设成局部的就不可以用呢？？？？？
				dialog.dismiss();
			}
		});
		//设置两个按钮的点击事件
		builder.setView(view);
		dialog = builder.create();
		dialog.show();
		
	}
	public void setPwd(){
		
		String pwd=et_pwd.getText().toString().trim();
		String again=et_again.getText().toString().trim();
		//首先先要判断密码是否为空
		if(TextUtils.isEmpty(pwd)){
			Toast.makeText(getApplicationContext(), "密码不能为空", 0).show();
			return;
		}
		//判断两次密码输入的是否是正确的
		if(!pwd.equals(again)){
			Toast.makeText(getApplicationContext(), "两次密码输入不一致", 0).show();
			return;
		}
		//输入的密码需要进行加密
		//edit.putString("pwd", value)
		String pwdMD5=MyMD5.passwordMD5(pwd);
		//将密码房屋config中进行数据保存
		edit.putString("pwd", pwdMD5);
		//edit.putBoolean("first", false);
		edit.commit();
		
	}
	//为什么要设置成全局变量呢？？
	 int count=0;
	/**
	 * 如果不是第一次进入页面，就要显示输入密码提示框
	 */
	private void showPwdDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder=new Builder(this);
		builder.setCancelable(false);
		View view=View.inflate(getApplicationContext(), R.layout.activity_inputpwd, null);
		final EditText et_pwd=(EditText)view.findViewById(R.id.et_set_intpupwd);
		Button bt_ok=(Button)view.findViewById(R.id.bt_ok);
		Button bt_cancel=(Button)view.findViewById(R.id.bt_cansel);
		ImageView iv_eye=(ImageView)view.findViewById(R.id.iv_eye);
		
		iv_eye.setOnClickListener(new OnClickListener() {
			
			@Override 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(count % 2==0){
					et_pwd.setInputType(0);
				}else{
					et_pwd.setInputType(129);
				}
				count++;
				
			}
		});

		bt_cancel.setOnClickListener(new OnClickListener() {
			  
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		bt_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					//isOkPwd(pwd);
				String pwd=et_pwd.getText().toString().trim();
				String passwordMD5 = MyMD5.passwordMD5(pwd);
				if(sp.getString("pwd", "").equals(passwordMD5)){
					enterSafe();
					dialog.dismiss();
				}else{
					Toast.makeText(getApplicationContext(), "密码输入不正确", 0).show();
					return;
				}
			}
		});
		builder.setView(view);
		dialog=builder.create();
		dialog.show();
	}
	public void enterSafe(){
		Intent intent=new Intent(this,ShowSafeActivity.class);
		startActivity(intent);
		
	}
/**
 * 跳转到第七个界面
 */
	public void enterTools(){
		Intent intent=new Intent(this,AdvancedToolsActivity.class);
		startActivity(intent);
	}
	
	
	
}
