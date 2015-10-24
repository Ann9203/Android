package com.itheima.mobileSafe;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itheima.Engien.BackUpMsg;
import com.itheima.Utils.MyAysncTask;
import com.itheima.face.ProgressBarInterface;

public class AdvancedToolsActivity extends Activity {


	private ProgressDialog pbdialog;
	private ProgressBar pb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advancedtools);
		pb=(ProgressBar) findViewById(R.id.pb_tools_progressbar);

	}
	public void query(View View){
		Intent intent =new Intent(this,QueryLocationActivity.class);
		startActivity(intent);
	}
	public void backupmsg(View v){
		new MyAysncTask() {
			
			@Override
			public void preExecute() {
				// TODO Auto-generated method stub
				//pb.setVisibility(View.VISIBLE);
				showdialog();
			}
			
			@Override
			public void nextExecute() {
				// TODO Auto-generated method stub
				//pb.setVisibility(View.GONE);
				pbdialog.dismiss();
			}
			
			@Override
			public void doInExecute() {
				// TODO Auto-generated method stub
				BackUpMsg.backupMsg(getApplicationContext(),new ProgressBarInterface(){

					@Override
					public void setMax(int i) {
						// TODO Auto-generated method stub
						//pb.setMax(i);
						pbdialog.setMax(i);
					}

					@Override
					public void setProgress(int current) {
						// TODO Auto-generated method stub
						//pb.setProgress(current);
						pbdialog.setProgress(current);
					}}
				);
			}
		}.execute();
		
		Toast.makeText(getApplicationContext(), "备份成功！！", 0).show();
	}
	/**
	 * 设置弹出的对话框
	 */
	public void showdialog(){
		//progressDialog = new ProgressDialog(this);
//		progressDialog.setCancelable(false);//不能取消
//		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//		/*progressDialog.setMax(max);//设置最大进度
//		progressDialog.setProgress(value);//设置当前进度*/
//		progressDialog.show();
		pbdialog=new ProgressDialog(this);
		pbdialog.setCancelable(false);
		pbdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pbdialog.show();
		
	}
	/**
	 * 备份还原
	 */
	public void restoremsg(View v){
		BackUpMsg.RestoreMsg(getApplicationContext());
		System.out.println("还原成功");
	}
}
