package com.itheima.mobileSafe;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.itheima.fragment.CacheFragment;
import com.itheima.fragment.SDFragment;

public class ClearCacheActivity extends FragmentActivity {

	private CacheFragment cacheFragment;
	private SDFragment sdFragment;
	private FragmentManager supportFragmentManager;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_clearcache);
		//获取fragment
		cacheFragment=new CacheFragment();
		sdFragment=new SDFragment();
		//2.加载fragment
		supportFragmentManager=getSupportFragmentManager();
		//开启事物
		FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
		//添加fragment的操作
		//数据不会重复刷新
		//参数1：fragment要替换的空间的id;
		//参数2：要替换fragment
		beginTransaction.add(R.id.fram_clearcache_fragment, cacheFragment);
		//界面展示以最后一个添加的为准
		beginTransaction.add(R.id.fram_clearcache_fragment, sdFragment);
		//隐藏fragment操作
		beginTransaction.hide(sdFragment);
		//事物提交
		beginTransaction.commit();
		
	}
	//缓存清理
	public void cache(View v){
		//获取事物
		FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
		//隐藏操作
		beginTransaction.hide(sdFragment);
		//显示操作
		
		beginTransaction.show(cacheFragment);
		//提交
		beginTransaction.commit();
	}
	//SD卡清理
	public void sd(View v){
		//获取事物
		FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
		//隐藏操作
		beginTransaction.show(cacheFragment);
		//显示操作
		beginTransaction.show(sdFragment);
		//提交
		beginTransaction.commit();
	}
}
