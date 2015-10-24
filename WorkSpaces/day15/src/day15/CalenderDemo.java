
package day15;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalenderDemo {
	public static void main(String[] args) {
		Calendar cl=new GregorianCalendar();//获取当前时间的Calendar对象
		int year=cl.get(Calendar.YEAR);//获取当前的年份
		int month=cl.get(Calendar.MONTH)+1;//获取当前的月份
		int day=cl.get(Calendar.DAY_OF_MONTH);//获取当前几号
		System.out.println(year+"-"+month+"-"+day);
//		int date=Calendar.DAY_OF_MONTH;
//		System.out.println(date);
//		int i=Calendar .DAY_OF_WEEK;
//		System.out.println(i);
		cl.set(2008, 2, 1);
		//add可以是添加几天也可以是减少几天
		//cl.add(Calendar.DATE,-1);
	cl.add(Calendar .DATE, -1);
		
		int month1=cl.get(Calendar.MONTH)+1;
		int day1=cl.get(Calendar .DATE);
		System.out.println(month1+"*************"
				+ ".....***"+day1);
		
	}
}
