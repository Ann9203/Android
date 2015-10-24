package com.itheima.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class streamToString {
	public static String streamAndString(InputStream in)
	{
		ByteArrayOutputStream os=null;
		try{
			os=new ByteArrayOutputStream();
			byte[] by=new byte[1024];
			int len=-1;
			while((len=in.read(by))!=-1)
			{
				os.write(by,0,len);
				os.flush();
			}
			return os.toString();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			if(os!=null)
			{
				try{
					os.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		return os.toString();
	}

}
