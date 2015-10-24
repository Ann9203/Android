package com.itheima.camera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
		Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("sdcard/haha.jpg")));
		startActivityForResult(intent, 10);
	}
	public void click2(View v)
	{
		Intent intent =new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("sdcard/haha.3gp")));
		startActivityForResult(intent, 20);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==10)
		{
			Toast.makeText(this, "≈ƒ’’ÕÍ±œ", 0).show();
			
		}else if(requestCode==20)
		{
			Toast.makeText(this, "…„œÒÕÍ±œ", 0).show();
		}
	}

}
