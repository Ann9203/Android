package day13;
public class Student_Test {
	public static void main(String[] args) {
		/*
			 * 编程题
		定义一个标准学生类(name,age)
		定义一个学生数组。
		存储5个学生，然后遍历。
		String[]
		Student[]
		 * */
		//使用泛型
//		ArrayList str=new ArrayList();
//		str.add(new Day13_StudentDemo("ww",12));
//		str.add(new Day13_StudentDemo("rr", 13));
//		str.add(new Day13_StudentDemo("tt", 14));
//		str.add(new Day13_StudentDemo("yy", 15));
//		str.add(new Day13_StudentDemo("uu", 16));
//		
//		Iterator ie=str.iterator();
//		while(ie.hasNext())
//		{
//			System.out.println(ie.next());
//			
//		}
		
		Day13_StudentDemo[] stu=new Day13_StudentDemo[5];
//		int[] in={23,15,16,12,13};
//		String[] str={"ww","ee","rr","tt","yy"};
//		Day13_StudentDemo sd=new Day13_StudentDemo("ww",13);
		
		
//		String[] str=new String()[]
//		//stu[0]=new Day13_StudentDemo("ss", 12);
        stu[0]=new Day13_StudentDemo("ss",12);
  		stu[1]=new Day13_StudentDemo("ww",14);
		stu[2]=new Day13_StudentDemo("rr",15);
		stu[3]=new Day13_StudentDemo("yy",17);
		stu[4]=new Day13_StudentDemo("rr",17);
//		
		for (int i = 0; i < stu.length; i++) {
			System.out.println(stu[i].getName()+" "+stu[i].getAge()+" ");
		}
		
	}
}
