package com.itheima.center;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click(View v)
	{
		//����Ƿ�������Ĺ㲥
		//���Ȼ��Ǵ�����ͼ����
		Intent in=new Intent();
		//�������͵�action������
		in.setAction("com.itheima.fan");
		//��������Ĺ㲥
		
		sendOrderedBroadcast(in, null,//���ܵ�Ȩ��
				
		 new MyReciver(), //�����������߶������������й㲥�������Ŷ��յ��㲥֮��̫���յ��㲥������һ�����ܵ������㲥
		 
		 null, 100, "ÿ�����·�100�����", null);//�㲥�п���ֱ��Я������������
		
	}
	class MyReciver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//ֻ������ȡ������
			String order=getResultData();
			System.out.println("��̰���յ���Ϣ"+order);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
