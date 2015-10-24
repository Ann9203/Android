package com.itheima.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText et_males;
	private EditText et_females;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_males=(EditText)findViewById(R.id.et_males);
		et_females=(EditText)findViewById(R.id.et_females);
		
	}
	public void click(View v)
	{
		String males=et_males.getText().toString().trim();
		String females=et_females.getText().toString().trim();
		//携带数据传递过去
		Intent intent=new Intent(this,SecondActivity.class);
//		intent.putExtra("males", males);
//		intent.putExtra("females", females);
		//也可以使用Bundle
		Bundle bundle=new Bundle();
		bundle.putString("males", males);
		bundle.putString("females", females);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
