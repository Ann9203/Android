package com.itheima.server;

import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;

public class GPSService extends Service {

	private SharedPreferences sp;
	private LocationManager location;
	private MyLocationListener mylocation = new MyLocationListener();
	/**
	 * GPS定位服务
	 * 
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// 获取本地的管理
		sp=getSharedPreferences("config", MODE_PRIVATE);
		location = (LocationManager) getSystemService(LOCATION_SERVICE);
		// 获取所有的定位方式
		List<String> providers = location.getProviders(true);
		for (String provider : providers) {
			System.out.println("最佳定位方式" + provider);
		}
		// 得到最佳的定位
		Criteria criteria = new Criteria();
		// 判断是否可以计算海拔
		criteria.setAltitudeRequired(true);

		// 得到最佳定位
		String bestProvider = location.getBestProvider(criteria, true);
		// 定位
		location.requestLocationUpdates(bestProvider, 0, 0, mylocation);
	}

	class MyLocationListener implements LocationListener {

		// 当位置发生改变进行定位的时候，获取经纬度
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			// 获取经纬度
			// 获取未读
			double latitude = location.getLatitude();
			// 获取经度
			double longitude = location.getLongitude();
			System.out.println(latitude + longitude);
			
			// 获取经纬度之后，发送短信
			
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(sp.getString("safenum", "5556"), null,
					"Latitude" + latitude + " longitude" + longitude, null,
					null);
			System.out.println(sp.getString("safenum", ""));
			// 停止服务
			stopSelf();
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		location.removeUpdates(mylocation);
	}

}
