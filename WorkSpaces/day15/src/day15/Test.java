package day15;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {
	public static void main(String[] args) throws ParseException {

		Date date=new Date();
		//不要默认的格式，自己写一个指定的格式
		//由Date---String 类型 需要format方法返回一个String类型
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String s=dateFormat.format(date);
		System.out.println(s);
		System.out.println("********************************");
		
		//不要默认格式自己指定一个格式
		//由Date--String类型  需要format返回一个字符串
		
		Date d3=new Date();
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s3=sm.format(d3);
		System.out.println(s3);
		System.out.println("_________________________________________________");
		
		
		//解析字符串  String---date 使用parse方法，返回一个Date类型
		String s4="2014-9-10 12:30:34";
		SimpleDateFormat smmm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d4=new Date();
		d4=smmm.parse(s4);
		System.out.println(d4);
	
		 
		System.out.println("******************************************************");
		//解析字符串  String---Date 使用parse方法，返回一个Date类型
		//同事simpleDateFormat的构造方法，要和String的格式是一样的，否则就会抛出异常
		String d="1999-3-12 00:00:00";
		SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2=new Date();
		//这里会抛出一个一异常的
		date2=ss.parse(d);
		//ss.parse(d);
		System.out.println(date2);
		
				
		
		}

	
}
