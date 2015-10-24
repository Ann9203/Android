package com.example.gps;

import java.util.List;

import android.app.Activity;
import android.content.Loader;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private LocationManager location;
	private TextView tv_gps;
	MyLocationListener mylocation = new MyLocationListener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_gps = (TextView) findViewById(R.id.tv_gps);
		// 获取本地的管理
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

	// 销毁

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		location.removeUpdates(mylocation);
	}

}
