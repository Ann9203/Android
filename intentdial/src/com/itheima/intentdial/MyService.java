package com.itheima.intentdial;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.itheima.intentdial.ICat.Stub;

public class MyService extends Service {

	//����һ����ʱ��
	Timer timer=new Timer();
	String[] colors=new String[]{
			"��",
			"��",
			"��",
			"��"
			
	};
	double[] weights=new double[]{
			1.2,3.4,6.5,3.2
	};
	private String color;
	private double weight;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new Cat();
	}
	class Cat extends Stub{

		@Override
		public String getCat() throws RemoteException {
			// TODO Auto-generated method stub
			return color;
		}

		@Override
		public double getWeight() throws RemoteException {
			// TODO Auto-generated method stub
			return weight;
		}
		
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//��0��ʼÿ��800����ͻ�ִ��һ��
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//�漴�ı�color��weight��ֵ
				Random random=new Random();
				int i=random.nextInt(4);
				color=colors[i];
				weight=weights[i];
				System.out.println("-----------------"+i);
				System.out.println("--------------");
				System.out.println("��ɫ��:"+color+"����Ҳ����:"+weight);
				
				
			}
		}, 0, 800);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer.cancel();
	}

}
