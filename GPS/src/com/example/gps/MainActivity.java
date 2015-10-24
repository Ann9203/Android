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
		// ��ȡ���صĹ���
		location = (LocationManager) getSystemService(LOCATION_SERVICE);
		// ��ȡ���еĶ�λ��ʽ
		List<String> providers = location.getProviders(true);
		for (String provider : providers) {
			System.out.println("��Ѷ�λ��ʽ" + provider);
		}
		// �õ���ѵĶ�λ
		Criteria criteria = new Criteria();
		// �ж��Ƿ���Լ��㺣��
		criteria.setAltitudeRequired(true);

		// �õ���Ѷ�λ
		String bestProvider = location.getBestProvider(criteria, true);
		// ��λ
		location.requestLocationUpdates(bestProvider, 0, 0, mylocation);

	}

	class MyLocationListener implements LocationListener {

		// ��λ�÷����ı���ж�λ��ʱ�򣬻�ȡ��γ��
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			// ��ȡ��γ��
			// ��ȡδ��
			double latitude = location.getLatitude();
			// ��ȡ����
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

	// ����

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		location.removeUpdates(mylocation);
	}

}
