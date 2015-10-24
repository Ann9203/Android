package com.example.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamToString {

	public static String streamToString(InputStream in) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		byte[] by=new byte[1024];
		int len=-1;
		try{
			
			while((len=in.read(by))!=-1)
			{
				bos.write(by,0,len);
				bos.flush();
			}
			bos.close();
			return bos.toString();
		}catch(Exception e){e.printStackTrace();}
	
		return bos.toString();
	}

}
