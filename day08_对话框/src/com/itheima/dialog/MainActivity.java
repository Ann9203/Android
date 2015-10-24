package com.itheima.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void click1(View v)
	{
		//new出一个对话框创建器
		AlertDialog.Builder builder=new Builder(this);
		//设置图标
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		//设置正文
		builder.setTitle("警告");
		//设置内容
		builder.setMessage("是否选择取消操作，你确定吗？");
		//设置按钮的确定取消事件ain
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "你选择了确定的按钮", 0).show();
				
			}
		});
		//设置按钮的取消事件
		builder.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				Toast.makeText(MainActivity.this, "你选择了取消的按钮", 0).show();
			}
		});
		//创建对话框
		AlertDialog dialog=builder.create();
		//显示对话框
		dialog.show();
		
	}
	public void click2(View v)
	{
		//创建对象
		AlertDialog.Builder builder=new Builder(this);
		//谁定单向类表的选项
		final String[] items=new String[]{"郑博","郑博","郑博"};
		//设置单选列表
		builder.setSingleChoiceItems(items, 1, new OnClickListener(){
			//任何一个条目被点击都会调用这个方法
			//dialog 出发此方法调用的对话框对象
			//whitch  选中的条目的索引值
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, items[which],0).show();
				//对话框小时
				dialog.dismiss();
			}
			
			
		});
		//创建对话框
		builder.create().show();
	
		
	}
	boolean[] checkedItems=new boolean[]{
			false,
			true,
			false,
			true,
			false,
			false
			
	};
	public void click3(View v)
	{
		//多选框
		//创建对话框
		AlertDialog.Builder builder=new Builder(this);
		final String[]items=new String[]{
				"东东",
				"啤酒瓶",
				"板凳",
				"AK47",
				"洲际导弹",
				"小宋佳"
		};
		builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				//记录第which个条目是被选中了还是被取消了
				checkedItems[which]=isChecked;
				
			}
		});
		//设置确定按钮
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method m,stub
				
				dialog.dismiss();
				//土司弹出索要选择的条目
				String text="";
				for(int i=0;i<checkedItems.length;i++)
				{
					text+=checkedItems[i]?items[i]+",":"";
					
				}
				text=text.substring(0,text.length()-1);
				Toast.makeText(MainActivity.this, text, 0).show();
			}
		});
		builder.create().show();
	}
	public void click4(View v)
	{
		//进度条的对话框
		final ProgressDialog pd=new ProgressDialog(this);
		pd.setTitle("正在更新。。。。");
		//设置进度条的样式
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//设置进度条的长度
		pd.setMax(100);
		Thread tr=new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<=100;i++)
				{
					pd.setProgress(i);
					try {
						sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				pd.dismiss();
			}
		};
		tr.start();
		pd.show();
		
	}

}
