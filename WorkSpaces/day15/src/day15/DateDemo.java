package day15;

import java.awt.image.SampleModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		/*0.0
		 * �����У���Ҫ����
		 * 		String---date
		 *    �����ַ�����ʹ�õķ�����;parse����
		 * 		Date-----String
		 *    ʹ�õ���Format����
		 * */
		//���ַ�����ʽ��ת�����ڸ�ʽ
		String dateString="1999-09-11 13:14:23";
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		//��parse ���׳�һ���쳣���Ծ�Ҫ�׳��쳣
		Date date=simpleDateFormat.parse(dateString);
		System.out.println(date);
		//��ΰ�dateת�����ַ���
		Date date2=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:dd z");
		String string=sdf.format(date2);
		System.out.println(string);
		
		
		

	}

}
