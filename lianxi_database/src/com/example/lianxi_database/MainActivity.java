package com.example.lianxi_database;

import java.util.ArrayList;


import com.itheima.SQLiteHelper.MySQLiteHelper;
import com.itheima.adapter.UserAdapter;
import com.itheima.dao.StudentDAO.AddStudent;
import com.itheima.dao.StudentDAO.StudentDAO;
import com.itheima.domain.UserBean.UserBean;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener  {

	private Context context=this;
	private StudentDAO studao=new StudentDAO(context);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取控件
		ListView lv_student=(ListView)findViewById(R.id.lv_student);
		ArrayList<UserBean> studentAll = studao.queryAll();
		System.out.println(studentAll.size());
		UserAdapter ua=new UserAdapter(studentAll,context);
		lv_student.setAdapter(ua);	
//		Button bt_add=(Button)findViewById(R.id.bt_add);
//		bt_add.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ArrayList<UserBean> array=AddStudent.addAll();
		
		
		switch (v.getId())
		{
			case R.id.bt_add:
				for(int i=0;i<array.size();i++)
				{
					boolean add = studao.add(array.get(i));
					if(add)
					{
						Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(context, "添加失败", Toast.LENGTH_SHORT).show();
					}
				}
				
				break;
			default :
				break;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

	

}
