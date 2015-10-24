package com.itheima.showfragement;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Fragment02 extends Fragment {
	View view ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 view = inflater.inflate(R.layout.fragment02, null);
		return view;
	}
	public void setText(String text)
	{
		EditText et = (EditText) view.findViewById(R.id.ed);
		et.setText(text);
	}
}
