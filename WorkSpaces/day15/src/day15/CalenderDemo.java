
package day15;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalenderDemo {
	public static void main(String[] args) {
		Calendar cl=new GregorianCalendar();//��ȡ��ǰʱ���Calendar����
		int year=cl.get(Calendar.YEAR);//��ȡ��ǰ�����
		int month=cl.get(Calendar.MONTH)+1;//��ȡ��ǰ���·�
		int day=cl.get(Calendar.DAY_OF_MONTH);//��ȡ��ǰ����
		System.out.println(year+"-"+month+"-"+day);
//		int date=Calendar.DAY_OF_MONTH;
//		System.out.println(date);
//		int i=Calendar .DAY_OF_WEEK;
//		System.out.println(i);
		cl.set(2008, 2, 1);
		//add��������Ӽ���Ҳ�����Ǽ��ټ���
		//cl.add(Calendar.DATE,-1);
	cl.add(Calendar .DATE, -1);
		
		int month1=cl.get(Calendar.MONTH)+1;
		int day1=cl.get(Calendar .DATE);
		System.out.println(month1+"*************"
				+ ".....***"+day1);
		
	}
}
