package demo2;

import java.util.Set;
import java.util.TreeMap;

public class CharDemo {
	/*
	 * ��һ���ַ���:  "cbxzbvavdvgd"  Ҫ���ȡ�ַ����У�
	 * ÿһ����ĸ���ִ�����"a(1)b(2)c(1)d(2)g(1)v(3)x(1)z(1)"
	 * �����һ�� ˼· ��
	 * A�����ַ���ת�����ַ�����
	 * B:����һ�����ϣ����ַ�Ϊ���������ٸ�Ϊֵ
	 * C:�������飬�����ַ��õ�ֵ������Map��������û��ֵ������еĻ�
	 * ����ԭ�����ϼ�һ�����û�еĻ���Ϊ1
	 * D: ����һ��stringBulider��Ȼ�����Map���ϣ���ֵ�ͽ�д��StringBuilder��
	 * 
	 * */  
	public static void main(String[] args) {
		
		String string="cbxzbvavdvgd";
		//����һ���ַ�����
		char[] ch=string.toCharArray();
		//����һ��Map����
		TreeMap<Character, Integer>map=new TreeMap<>();
		//��������
		for (Character c : ch) {
			Integer integer=map.get(c);
			if(integer==null)
			{
				map.put(c, 1);
			}else {
				integer++;
				map.put(c, integer);
			}
		}
		//����һ��StringBuilder����Ϊ��StringBufferҪ��Ч��
		
		StringBuilder sBuilder=new StringBuilder();
		//ѭ��Map���ϣ��������е�Ԫ����ӵ�StringBuilder��ȥ
		Set<Character> key=map.keySet();
		for (Character character : key) {
			Integer integer=map.get(character);
			sBuilder.append(character).append("(").append(integer).append(")");
		}
		String result=sBuilder.toString();
		System.out.println(result);
	}

}
