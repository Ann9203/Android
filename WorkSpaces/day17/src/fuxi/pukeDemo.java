package fuxi;

import java.util.ArrayList;
import java.util.Collections;

public class pukeDemo {
	/*
	 * 斗地主（敲2遍）
	 * 
	 * */

	public static void main(String[] args) {
		String [] huase={"黑桃","梅花","红桃","方片"};
		String [] shuzhi={"A","2","3","4","5","6","7","8","9","10","J","Q","K"}; 
		//创建一个集合，来装牌
		ArrayList<String> pai=new ArrayList<String>();
		//在牌中添加大王和小王
		pai.add("大王");
		pai.add("小王");
		//买牌，组装成52张牌，然后添加到数组中去
		for(int i=0;i<huase.length;i++)
		{
			for(int j=0;j<shuzhi.length;j++)
			{
				pai.add(huase[i].concat(shuzhi[j]));
			}
		}
		//洗牌，将牌的顺序打乱了
		Collections.shuffle(pai);
		/*
		 * 发牌：
		 * 		A:首先要有是个容器装载着牌，也就是斗地主的三个人
		 * 	    B:其次就是每个人呢去爪牌，做%3的算法
		 * 		C:每次爪牌都是留下最低下的三张底牌
		 */
		ArrayList<String >zhoujielun=new ArrayList<String>();
		ArrayList<String>zhourunfa=new ArrayList<String>();
		ArrayList<String>caicai=new ArrayList<String >();
		//发牌
		//因为如果循环的话每次都要调用size方法，所以呢，就将size方法返回的值传个一个变量
		//这样的话有利于提高程序的运行效率
		int length=pai.size();
		for (int i = 0; i < length-3; i++) {
			if(i%3==0)
			{
				zhoujielun.add(pai.get(i));
			}
			else if(i%3==1){
				zhourunfa.add(pai.get(i));
			}
			else if(i%3==2) {
				caicai.add(pai.get(i));
			}
		}
		System.out.println("周董的牌："+zhoujielun);
		System.out.println("赌神的牌："+zhourunfa);
		System.out.println("小菜的牌："+caicai);
		
		//底牌
		//因为冲洗了牌之后牌的顺序就一直没有变过，所以呢
		//底牌肯定是最后三张牌
		//设置一个集合，装下底牌
		ArrayList<String>dipai=new ArrayList<String>();
		for (int i = length-3; i <length; i++) {
			dipai.add(pai.get(i));
		}
		System.out.println("底牌是  ："+dipai);
		
	}
}
