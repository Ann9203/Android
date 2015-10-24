package com.itheima.mobileSafe.test;

import java.util.ArrayList;
import java.util.HashMap;

import android.test.AndroidTestCase;

import com.itheima.Engien.ContactEngien;

public class ContactTest extends AndroidTestCase {

	public void Test1(){
		
		ArrayList<HashMap<String,String>> list=(ArrayList<HashMap<String, String>>) ContactEngien.getContact(getContext());
		for(HashMap<String, String> ha:list){
			System.out.println(ha.toString());
		}
	}
}
