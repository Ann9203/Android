package demo2;

import java.util.HashMap;
import java.util.Set;

public class QianTaoDemo1 {
	//Ƕ��ѭ��Map
	public static void main(String[] args) {
		//Map����
		//����һ����ļ���
		HashMap<String , HashMap<String, StudentDemo>>chuanzhi=new HashMap<>();
		//����һ��Ԥ�Ȱ뼯��
		HashMap<String, StudentDemo>ry=new HashMap<>();
		//��Ԥ�Ȱ��������Ӧ��Ԫ��
		ry.put("20145", new StudentDemo("Сѩ", 23));
		ry.put("20146", new StudentDemo("С��", 22));
		ry.put("20142", new StudentDemo("С��", 24));
		ry.put("20141", new StudentDemo("С��", 22));
		ry.put("20147", new StudentDemo("С��", 21));
		//����һ����ҵ��ļ���
		HashMap<String, StudentDemo>jy=new HashMap<>();
		//�ھ�ҵ���������Ӧ��Ԫ��
		jy.put("20145", new StudentDemo("Сѩ", 23));
		jy.put("20146", new StudentDemo("С��", 22));
		jy.put("20142", new StudentDemo("С��", 24));
		jy.put("20141", new StudentDemo("С��", 22));
		jy.put("20147", new StudentDemo("С��", 21));
		
		//������ѧԺ�����Ԫ��
		chuanzhi.put("rure", ry);
		chuanzhi.put("jiuye", jy);
		//��������ѧԺ��Ԫ��
		Set<String>keySet=chuanzhi.keySet();
		for (String string : keySet) {
			System.out.println(string);
			HashMap<String, StudentDemo>xuesheng=chuanzhi.get(string);
			Set<String>xue=xuesheng.keySet();
			for (String string2 : xue) {
				StudentDemo stu=xuesheng.get(string2);
				System.out.println(string2+"\t"+stu.getName()+"\t"+stu.getAge());
				}
		}
		
	}

}
