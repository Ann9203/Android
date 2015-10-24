package demo2;

import java.util.HashMap;
import java.util.Set;

import sun.awt.image.PNGImageDecoder.Chromaticities;

public class CharCount {
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
		//���ַ���ת��������
		char[] ch=string.toCharArray();
		//����һ�����ϣ�����װ���ַ������count����Map����
		HashMap<Character, Integer>charcount=new HashMap<>();
		//�������飬Ȼ����Map���������Ԫ��
		//ֱ��ǿת��Character���͵�
		for (Character c : ch) {
			Integer integer=charcount.get(c);
			//�����ֵΪNullֵ
			if(integer==null)
			{
				charcount.put(c, 1);
			}else {
				integer++;
				charcount.put(c, integer);
			}
		}
		//��Ӻ�Ԫ�أ�
		//��Ҫ��Map�����е�Ԫ��ת����String���͵Ĵ�ӡ����
		StringBuilder sBuilder=new StringBuilder();
		Set<Character>characters=charcount.keySet();
		for (Character character : characters) {
			Integer integer=charcount.get(character);
			sBuilder.append(character).append("(").append(integer).append(")");
		}
		String result=sBuilder.toString();
		System.out.println(result);
		
	}
}
