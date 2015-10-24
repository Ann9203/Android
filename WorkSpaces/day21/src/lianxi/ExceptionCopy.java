package lianxi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExceptionCopy {
	/*
	 * �ֽ��������ļ��쳣����ı�׼���루��һ�飩
	 * */
	public static void main(String[] args) {
		
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		try {
			//����������
			bis=new BufferedInputStream(new FileInputStream("Z:\\rr.jpeg"));
			bos=new BufferedOutputStream(new FileOutputStream("X:\\oo.jpeg") ); 
			//��������
			byte[] bys=new byte[1024];
			//���峤��
			int leng=0;
			//ѭ����ȡ�ֽ�����
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
