package com.example.day05_httpclient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamToString {

	public static String streamToString(InputStream in) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream bos=null;
		String result=null;
		try{
			bos=new ByteArrayOutputStream();
			byte[] by=new byte[1024];
			int len=-1;
			while((len=in.read(by))!=-1)
			{
				bos.write(by,0,len);
				bos.flush();
			}
			 result=bos.toString();
		
		}catch(Exception e){e.printStackTrace();}
		finally{
			if(bos!=null)
			{
				try{bos.close();}catch(Exception e){e.printStackTrace();}
			}
			
		}
		return result;
	}

}
