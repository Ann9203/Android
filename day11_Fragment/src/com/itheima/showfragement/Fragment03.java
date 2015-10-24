package com.itheima.showfragement;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment03 extends android.app.Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View view = inflater.inflate(R.layout.fragment03, null);
		Button bt=(Button)view.findViewById(R.id.bt);

		bt.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 EditText et=(EditText) view.findViewById(R.id.edit1);
				 String text=et.getText().toString().trim();
				
				MainActivity activity = (MainActivity) getActivity();
				activity.setText(text);
				
			}
		});
		return view;
	}
}
