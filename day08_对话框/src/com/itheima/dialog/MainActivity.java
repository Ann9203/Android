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
		//new��һ���Ի��򴴽���
		AlertDialog.Builder builder=new Builder(this);
		//����ͼ��
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		//��������
		builder.setTitle("����");
		//��������
		builder.setMessage("�Ƿ�ѡ��ȡ����������ȷ����");
		//���ð�ť��ȷ��ȡ���¼�ain
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "��ѡ����ȷ���İ�ť", 0).show();
				
			}
		});
		//���ð�ť��ȡ���¼�
		builder.setNegativeButton("ȡ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				Toast.makeText(MainActivity.this, "��ѡ����ȡ���İ�ť", 0).show();
			}
		});
		//�����Ի���
		AlertDialog dialog=builder.create();
		//��ʾ�Ի���
		dialog.show();
		
	}
	public void click2(View v)
	{
		//��������
		AlertDialog.Builder builder=new Builder(this);
		//˭����������ѡ��
		final String[] items=new String[]{"֣��","֣��","֣��"};
		//���õ�ѡ�б�
		builder.setSingleChoiceItems(items, 1, new OnClickListener(){
			//�κ�һ����Ŀ�������������������
			//dialog �����˷������õĶԻ������
			//whitch  ѡ�е���Ŀ������ֵ
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, items[which],0).show();
				//�Ի���Сʱ
				dialog.dismiss();
			}
			
			
		});
		//�����Ի���
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
		//��ѡ��
		//�����Ի���
		AlertDialog.Builder builder=new Builder(this);
		final String[]items=new String[]{
				"����",
				"ơ��ƿ",
				"���",
				"AK47",
				"�޼ʵ���",
				"С�μ�"
		};
		builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				//��¼��which����Ŀ�Ǳ�ѡ���˻��Ǳ�ȡ����
				checkedItems[which]=isChecked;
				
			}
		});
		//����ȷ����ť
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method m,stub
				
				dialog.dismiss();
				//��˾������Ҫѡ�����Ŀ
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
		//�������ĶԻ���
		final ProgressDialog pd=new ProgressDialog(this);
		pd.setTitle("���ڸ��¡�������");
		//���ý���������ʽ
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//���ý������ĳ���
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
