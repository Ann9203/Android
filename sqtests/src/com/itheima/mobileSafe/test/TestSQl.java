package com.itheima.mobileSafe.test;

import java.util.List;
import java.util.Random;

import com.itheima.bean.BlackNum;
import com.itheima.db.dao.BlackNumSQL;

import android.test.AndroidTestCase;

public class TestSQl extends AndroidTestCase {

	public void testAdd(){
		BlackNumSQL blackNum=new BlackNumSQL(getContext());
		Random random=new Random();
		
		for(int i=0;i<35;i++){
			String mode=String.valueOf(random.nextInt(3));
			String phone="12345"+i;
			blackNum.addBlackNum(phone, mode);
			
			
		}
	}
	public void queryNum(){
		BlackNumSQL blackNum=new BlackNumSQL(getContext());
		List<BlackNum> queryBlackNum = blackNum.queryBlackNum();
		for(BlackNum black:queryBlackNum){
			
			System.out.println(black.toString());
		}
	}
}
