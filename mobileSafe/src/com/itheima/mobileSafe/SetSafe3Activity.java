package com.itheima.mobileSafe;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima.Utils.NetPreMethod;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SetSafe3Activity extends NetPreMethod {
	
	@ViewInject(R.id.et_safenum)
	private EditText ed_setsafe3_safenum;
	private Editor edit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setsafe4);
		ViewUtils.inject(this);
		
		
		//数据回显
		ed_setsafe3_safenum.setText(sp.getString("safenum", ""));
		
		
	}

	@Override
	public void preMethod() {
		// TODO Auto-generated method stub
		Intent intent =new Intent(this,SetSafe2Activity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.set_enter_pretanim,R.anim.set_exit_pretanim);
	}

	@Override
	public void nextMethod() {
		// TODO Auto-generated method stub
		//在跳到下一个界面之前保存密码将输入的号码保存到sp中
		String safenum = ed_setsafe3_safenum.getText().toString().trim();
		if(TextUtils.isEmpty(safenum)){
			Toast.makeText(this, "请输入安全号码",0).show();			
		}
		
		 edit=sp.edit();
		edit.putString("safenum", safenum);
		edit.commit();
		Intent intent =new Intent(this,SetSafe4Activity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.set_enter_nextanim, R.anim.set_exit_nextanim);
	}
	public void select(View v)
	{
		//跳转到下一个界面，当时是需要跳转界面返回给内容的
		//Intent intent =new Intent(this,ContactActivity.class);
		//startActivityForResult(intent, 0);
		//跳转到联系人界面
		Intent intent=new Intent();
		intent.setAction("android.intent.action.PICK");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setType("vnd.android.cursor.dir/phone_v2");
		startActivityForResult(intent, 1);
	}
	//返回给数据
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
//			if(data!=null){
//				String phonenum=data.getStringExtra("phone");
//				ed_setsafe3_safenum.setText(phonenum);
//			}
			String num = null;
			//直接跳转到联系人的界面
			if(data!=null){
				
				Uri uri=data.getData();
				//定义内容提供解析这
				ContentResolver cr=getContentResolver();
				Cursor cursor = cr.query(uri, null, null, null, null);
				while(cursor.moveToNext()){
					 num = cursor.getString(cursor.getColumnIndex("data1"));
					 num=num.replace("-", "");
				}
				ed_setsafe3_safenum.setText(num);
				

			}
		}

}
