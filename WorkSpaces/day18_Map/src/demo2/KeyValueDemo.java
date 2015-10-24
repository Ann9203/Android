package demo2;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class KeyValueDemo {
	/*
	 * 
	 * 查询Map集合的API, 
	 * 找到获取key的集合与value集合的方法。
	 * 然后创建一个Map集合, 存入3组元素,分别是
	 *  : 1:“吕布”  , 2:“赵云” , 3:“典韦” . 最后分别获取key的集合与value的集合并遍历
	 *  Set<key> keySet() 获取所有键的集合
	 *  Collection<Value> values() 获取所有值得集合
	 * 
	 * */
	
	public static void main(String[] args) {
		Map<String ,String>map=new HashMap<String,String>();
		map.put("1", "吕布");
		map.put("2", "赵云");
		map.put("3", "典韦");
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
