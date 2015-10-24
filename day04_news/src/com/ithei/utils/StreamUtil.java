package com.ithei.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtil {

	public static String streamToString(InputStream in) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream brry = new ByteArrayOutputStream();
		byte[] by=new byte[1024];
		int len=-1;
		
		try{
			while((len=in.read(by))!=-1)
			{
				brry.write(by,0,len);
				brry.flush();
			}
			brry.close();
			
		}catch(Exception e)
		{e.printStackTrace();}
		
		
		
		return brry.toString();
	}

}
