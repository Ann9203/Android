package day15;

import java.awt.image.SampleModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		/*0.0
		 * 日期中：主要就是
		 * 		String---date
		 *    解析字符串，使用的方法是;parse方法
		 * 		Date-----String
		 *    使用的是Format方法
		 * */
		//将字符串格式的转换日期格式
		String dateString="1999-09-11 13:14:23";
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		//用parse 会抛出一个异常所以就要抛出异常
		Date date=simpleDateFormat.parse(dateString);
		System.out.println(date);
		//如何把date转换成字符串
		Date date2=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:dd z");
		String string=sdf.format(date2);
		System.out.println(string);
		
		
		

	}

}
