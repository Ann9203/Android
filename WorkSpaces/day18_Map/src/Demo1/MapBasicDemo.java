package Demo1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;




/*
 * Map:��һһ��Ӧ��ϵ��һ���Լ�ֵ�Ӵ��ڵļ��ϣ����ݲ����ǵ����Ĵ��ڣ�������
 * ��Ψһ�Ĳ������ظ�
 * A����ӹ���
 * 		V put(key key,v value),��keyֵ�����������Ԫ�أ������ڵ�ʱ������滻keyֵ����Ӧ��
 * 		valueֵ
 * B:ɾ������
 * 		void clear()������м�ֵ������
 * 		V remove(Object key)������ָ���ļ�ֵ��ȥ��ֵ��
 * C:�жϹ���
 * 		boolean containsKey(Object key) �ж�ָ���ļ��Ƿ����
 * 				containsValue(Object value)�ж�ָ����ֵ�Ƿ����
 * 				isEmpty():�жϼ����Ƿ�Ϊ��
 * D:��ȡ����
 * 		set<Map.Entry<k,v>>entrySet();��ֵ�Զ���ļ��ϣ�
 * 		Object get(Object key);���ݼ�ֵ��ȡֵ
 * 		set<k>keySet();���м��ļ���
 * 		Collection<V>values?()����ֵ�ü���
 * E:�����жϹ���
 * 		int size()
 * */


public class MapBasicDemo {
	public static void main(String[] args) {
		
		//����һ��Map���϶���
		Map<String,String> map=new HashMap<String ,String>();
		map.put("010235", "Сѩ");
		map.put("010236", "С��");
		map.put("010231", "С��");
		map.put("010232", "С��");
		map.put("010233", "С��");
		//remove ���ص��Ǳ�ɾ���Ķ���
//		System.out.println(map.remove("010233"));
//		//�Ƿ����ĳ����ֵ
//		System.out.println(map.containsKey("123132"));
//		//�Ƿ����ָ����Valueֵ
//		System.out.println(map.containsValue("Сѩ"));
//		System.out.println(map.size());
		
		//��λ�ȡMap�е�Ԫ��
		//entrySet����ӳ���а�����ӳ���ϵ��set
		Set<Map.Entry<String, String>>entries=map.entrySet();
		Iterator<Map.Entry<String, String>>iterable=entries.iterator();
		while (iterable.hasNext()) {
			//��������ʲô���;�Ҫ��ʲô����������
			Map.Entry<String, String> mp=iterable.next();
			String key=mp.getKey();
			String value=mp.getValue();
			System.out.println(key+"**********"+value);
			
		}
		//�ڶ��ֻ�ȡ��ʽ��������keyset����ȡMap�����е����е�keyֵ
		//��Map�е�keysetֵ����set������
		Set<String>keySet=map.keySet();
		Iterator<String>iterator=keySet.iterator();
		while (iterator.hasNext()) {
			String key=iterator.next();
			String value=map.get(key);
			System.out.println(key+"************************"+value);
			
		}
		
	}
}
