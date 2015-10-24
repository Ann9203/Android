package day13;

public class StringDemo {
	public static void main(String[] args) {
		/*
		 * 需求：这个主要就是给出一个字符串，然后就是对字符串进行两个操作
		 * 			A:截取字符串
		 * 			B:分割字符串
		 * 步骤：
		 * 		使用的方法有：
		 * 			substring（int beginindex） 主要就是从第beginiex索引出开始截取
		 * 			substring（int beginindex,int endindex）从beginindex开始到endindex
		 * 			开始截取
		 * 			split（string regex）主要就是 以regex为依据进行分割
		 * */
		String str="篮球-足球-排球-乒乓球-橄榄球";
		//截取字符串
		System.out.println(str.substring(4));
		System.out.println(str.substring(4,7));
		//以"-"作为依据分割字符串
		String[] s=str.split("-");
		for (int i = 0; i < s.length; i++) {
			if (i==0) {
				System.out.print("{"+s[0]);
			}
			else if (i==s.length-1) {
				System.out.println(s[i]+"}");
				
			}else {
				System.out.print(s[i]+",");
			}
		}
		
	}

}
