package demo2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo3 {

	public static void main(String[] args) {
		Map<String ,String>map=new HashMap<>();
		map.put("¹ù¾¸", "Ğ¡ÁúÅ®");
		map.put("ÂÀ²¼", "õõ²õ");
		map.put("ÌÆÌ«×Ú", "ÑîÓñ»·");
		Set<Map.Entry<String, String>>mapEntries=map.entrySet();
		for (Map.Entry<String,String> ent:mapEntries) {
			String key=ent.getKey();
			String value=ent.getValue();
			System.out.println(key+"***************"+value);
		}
	}
}
