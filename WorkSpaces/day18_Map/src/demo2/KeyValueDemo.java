package demo2;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class KeyValueDemo {
	/*
	 * 
	 * ��ѯMap���ϵ�API, 
	 * �ҵ���ȡkey�ļ�����value���ϵķ�����
	 * Ȼ�󴴽�һ��Map����, ����3��Ԫ��,�ֱ���
	 *  : 1:��������  , 2:�����ơ� , 3:����Τ�� . ���ֱ��ȡkey�ļ�����value�ļ��ϲ�����
	 *  Set<key> keySet() ��ȡ���м��ļ���
	 *  Collection<Value> values() ��ȡ����ֵ�ü���
	 * 
	 * */
	
	public static void main(String[] args) {
		Map<String ,String>map=new HashMap<String,String>();
		map.put("1", "����");
		map.put("2", "����");
		map.put("3", "��Τ");
		Set<String>keySet=map.keySet();
		for (String string:keySet) {
			String value=map.get(string);
			System.out.println(string+"***************"+value);
		}
		Collection<String> value=map.values();
		for (String string :value) {
			System.out.println(string);
		}
		Set<Map.Entry<String, String>>keyvalue=map.entrySet();
		Iterator<Map.Entry<String, String>>iterator=keyvalue.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> mEntry=iterator.next();
			String key=mEntry.getKey();
			String value1=mEntry.getValue();
			System.err.println(key+"************"+value1);
			
		}
	}
}
