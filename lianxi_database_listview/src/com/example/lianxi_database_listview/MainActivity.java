package com.example.lianxi_database_listview;

import java.util.ArrayList;

import com.itheima.adapter.UserAdapter;
import com.itheima.dao.StudentDAO.AddStudent;
import com.itheima.dao.StudentDAO.StudentDAO;
import com.itheima.domain.Student.StudentBean;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Context mcontext=this;
	private ListView lv_show;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ȡ�ؼ���id
		Button bt_add=(Button)findViewById(R.id.bt_add);
		Button bt_del=(Button)findViewById(R.id.bt_del);
		Button bt_update=(Button)findViewById(R.id.bt_update);
		Button bt_query=(Button)findViewById(R.id.bt_query);
		 lv_show=(ListView)findViewById(R.id.lv_student);
		bt_add.setOnClickListener(this);
		bt_del.setOnClickListener(this);
		bt_update.setOnClickListener(this);
		bt_query.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		StudentDAO studao=new StudentDAO(mcontext);
		ArrayList<StudentBean> array=AddStudent.addAll();
		
		switch(v.getId())
		{
			case R.id.bt_add:
				for(StudentBean stubean:array)
				{
					boolean result=studao.add(stubean);
					if(result)
					{
						Toast.makeText(mcontext, "��ϲ�㣬��ӳɹ�", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(mcontext, "���ź������ʧ��", Toast.LENGTH_SHORT).show();
					}
				}

				break;
			case R.id.bt_del:
				int resultdel = studao.delete("С��");
				Toast.makeText(mcontext, "��һ��ɾ����"+resultdel+"������", Toast.LENGTH_SHORT).show();
				break;
			case R.id.bt_update:
			StudentBean user=new StudentBean();
			user.name="С��";
			user.phone="111222";
			user.school="����ѧ";
			int resultupd = studao.update(user);
			Toast.makeText(mcontext, "��һ���޸���"+resultupd+"������", Toast.LENGTH_SHORT).show();
			
				break;
			case R.id.bt_query:
				ArrayList<StudentBean> stuquary= studao.queryAll();
				UserAdapter ua=new UserAdapter(stuquary,mcontext);
				lv_show.setAdapter(ua);
				break;
			default:
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
