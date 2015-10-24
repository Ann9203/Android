package Demo1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;




/*
 * Map:是一一对应关系是一个以键值队存在的集合，数据不在是单个的存在，键必须
 * 是唯一的补可以重复
 * A：添加功能
 * 		V put(key key,v value),但key值不存在是添加元素，但存在的时候就是替换key值所对应的
 * 		value值
 * B:删除功能
 * 		void clear()清楚所有键值对数据
 * 		V remove(Object key)，根据指定的键值除去键值对
 * C:判断功能
 * 		boolean containsKey(Object key) 判断指定的键是否存在
 * 				containsValue(Object value)判断指定的值是否存在
 * 				isEmpty():判断集合是否为空
 * D:获取功能
 * 		set<Map.Entry<k,v>>entrySet();键值对对象的集合；
 * 		Object get(Object key);根据键值获取值
 * 		set<k>keySet();所有键的集合
 * 		Collection<V>values?()所有值得集合
 * E:长度判断功能
 * 		int size()
 * */


public class MapBasicDemo {
	public static void main(String[] args) {
		
		//创建一个Map集合对象
		Map<String,String> map=new HashMap<String ,String>();
		map.put("010235", "小雪");
		map.put("010236", "小明");
		map.put("010231", "小红");
		map.put("010232", "小绿");
		map.put("010233", "小朵");
		//remove 返回的是被删除的对象
//		System.out.println(map.remove("010233"));
//		//是否包含某个键值
//		System.out.println(map.containsKey("123132"));
//		//是否包含指定的Value值
//		System.out.println(map.containsValue("小雪"));
//		System.out.println(map.size());
		
		//如何获取Map中的元素
		//entrySet返回映射中包含的映射关系的set
		Set<Map.Entry<String, String>>entries=map.entrySet();
		Iterator<Map.Entry<String, String>>iterable=entries.iterator();
		while (iterable.hasNext()) {
			//穿过的是什么类型就要用什么类型来接受
			Map.Entry<String, String> mp=iterable.next();
			String key=mp.getKey();
			String value=mp.getValue();
			System.out.println(key+"**********"+value);
			
		}
		//第二种获取方式：就是用keyset来获取Map集合中的所有的key值
		//降Map中的keyset值都给set集合中
		Set<String>keySet=map.keySet();
		Iterator<String>iterator=keySet.iterator();
		while (iterator.hasNext()) {
			String key=iterator.next();
			String value=map.get(key);
			System.out.println(key+"************************"+value);
			
		}
		
	}
}
