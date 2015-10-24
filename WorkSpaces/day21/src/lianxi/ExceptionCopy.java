package lianxi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExceptionCopy {
	/*
	 * 字节流复制文件异常处理的标准代码（敲一遍）
	 * */
	public static void main(String[] args) {
		
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		try {
			//创建流对象
			bis=new BufferedInputStream(new FileInputStream("Z:\\rr.jpeg"));
			bos=new BufferedOutputStream(new FileOutputStream("X:\\oo.jpeg") ); 
			//定义数组
			byte[] bys=new byte[1024];
			//定义长度
			int leng=0;
			//循环读取字节数据
			while((leng=bis.read(bys))!=-1)
			{
				bos.write(bys,0,leng);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			
			if(bis!=null)
			{
				try {
					bis.close();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
			if(bos!=null)
			{
				try {
					bos.close();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		
	}

}
