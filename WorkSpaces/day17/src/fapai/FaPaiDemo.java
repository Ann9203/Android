package fapai;

import java.util.ArrayList;
import java.util.Collections;

public class FaPaiDemo {
	/*
	 * 斗地主案例
	 * 	    A:买牌  写两个数组，一个是花色，一个是数据
	 * 		B:洗牌  洗牌就是将牌的顺序打乱了
	 * 		C:发牌  定义几个人的集合，然后每个人发牌
	 * 		D:底牌  就是剩下的三张牌
	 * 
	 * */
	
	public static void main(String[] args) {
		
		//把花色和数字分开，分别放到两个集合中
		//然后再通过二者的结合，放入到集合中去
		
		String [] huase={"黑桃","红桃","梅花","方片"};
		String [] shuzi={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		ArrayList<String > pai=new ArrayList<String>();
		pai.add("大王");
		pai.add("小王");
		//根据for循环，组合两个数组中的内容
		//买牌
		for (int i = 0; i < huase.length; i++) {
			for (int j = 0; j < shuzi.length; j++) {
				pai.add(huase[i].concat(shuzi[j]));
			}
		}
		//洗牌
		//就是通过shuff方法，随即置换
		//shuff方法是Collections工具类中的方法
		Collections.shuffle(pai);
		
		/*
		 * 发牌
		 * 	首先要创建三个容器，分别代表三个人，
		 * 然后在再容器中随即发牌
		 * */
		//定义三个玩斗地主的人
		ArrayList<String >zhoujielun=new ArrayList<String>();
		ArrayList<String>zhourunfa=new ArrayList<String>();
		ArrayList<String>zhoutian=new ArrayList<String>();
		//给三个人发牌
		for (int x=0;x<pai.size()-3;x++) {
			if(x%3==0)
			{
				zhoujielun.add(pai.get(x));
				
			}
			else if(x%3==1)
			{
				zhourunfa.add(pai.get(x));
				
			}
			else if(x%3==2)
			{
				zhoutian.add(pai.get(x));
				
			}
		}
		System.out.println("周董的牌："+zhoujielun);
		System.out.println("赌神的牌："+zhourunfa);
		System.out.println("农民的牌："+zhoutian);
		//底牌还有三张 ，也写一个集合吧
		//这个只是发牌，但是却没有打乱牌的顺序，所以呢直接后三张是打牌
		ArrayList< String > dipai=new ArrayList<String>();
		for(int x=pai.size()-3;x<pai.size();x++)
		{
			dipai.add(pai.get(x));
			
		}
		System.out.print("底牌是："+dipai);
		
	}

}
