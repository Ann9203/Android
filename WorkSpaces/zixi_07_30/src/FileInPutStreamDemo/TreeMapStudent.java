package FileInPutStreamDemo;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapStudent {

	public static void main(String[] args) {
//		//TreeMap<String,StudentDemo> tm1 = new TreeMap<String, StudentDemo>(new Comparator<StudentDemo>())
//		TreeMap<String,StudentDemo> tm = new TreeMap<String, StudentDemo>(new Comparator<StudentDemo>(){ 
//			
//			public int compare(StudentDemo s1, StudentDemo s2) {
//				
//				int num = s1.getScore()-s2.getScore();
//				int num2 = (num==0)? s1.getAge()-s2.getAge() : num;
//				int num3 = (num2==0)? s1.getName().compareTo(s2.getName()) : num2;
//				return num3;
//			
//			
//				
//		});
//		TreeMap<String,StudentDemo>tm=new TreeMap<String,StudentDemo>(new Comparator<StudentDemo>() {
//
//			@Override
//			public int compare(T o1, T o2) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		});
		TreeMap<StudentDemo, String>tm=new TreeMap<>(new Comparator<StudentDemo>() {

			@Override
			public int compare(StudentDemo o1, StudentDemo o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		//HashMap tm=new HashMap
			
		
		StudentDemo s1 = new StudentDemo("Õı◊ÊœÕ",20,78);
		StudentDemo s2 = new StudentDemo("¡÷÷æ¡·",30,80);
		StudentDemo s3 = new StudentDemo("’≈Œ¿Ω°",40,67);
		StudentDemo s4 = new StudentDemo("’‘—≈÷•",55,86);
		StudentDemo s5 = new StudentDemo("≥¬Ω‡“«",45,93);
		
//		tm.put("czbk001", s1);
//		tm.put("czbk002", s2);
//		tm.put("czbk003", s3);
//		tm.put("czbk004", s4);
//		tm.put("czbk005", s5);
//	
//		Set<String> key = tm.keySet();
//		
//		for (String student : key) {
//			
//			StudentDemo value = tm.get(student);
//			
//			System.out.println(value.getName()+"***"+value.getScore());
//		}
		
		
	
	}
}
