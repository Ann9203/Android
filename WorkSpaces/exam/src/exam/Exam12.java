package exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exam12 {
/*
 * 11����д����
 * ѭ�������û��Ӽ����������ַ�����
 * ֱ�����롰end��ʱѭ��������
 * ����������������ַ������ֵ�˳�����ӡ��
 * 
 * ˼·��
 * 		����¼�룺Scanner����Ȼ�����
 * 		�����ֵ�˳�򣬾���Ҫ���бȽ�����
 * 		ʹ��BufferBuilder�а��տ��Ե����ӡ
 * */
	public static void main(String[] args) {
		//����stringBuilder����
		//StringBuilder sBuilder=new StringBuilder();
		ArrayList<String> aList=new ArrayList<String>();
		Scanner scanner=new Scanner(System.in);
		//String string=scanner.nextLine();
		//String string=scanner.next();
		//��Ϊ��һֱ�ڴ�ӡ
		while(true)
		{
			String string=scanner.nextLine();
			if("end".equals(string))
			{
				break;
			}
			
			
			//string.compareTo(string);
//			sBuilder.append(string);	
			aList.add(string);
		}
		
		Collections.sort(aList, Collections.reverseOrder());
		for (String string : aList) {
			System.out.print(string);
		}
		
		
	}
}
