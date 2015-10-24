package it.cast.dao.impl;

import java.util.ArrayList;

import it.cast.dao.UserLogin;
import it.cast.pojo.UserEntity;
/*
 * 
 * 实现接口类，主要就是实现接口中的登陆和注册的功能
 * */

public class UserLoginImpl implements UserLogin{

	/*
	 * 因为登陆的功能不仅仅是一个人使用，而且注册了就会有数据，数据不能凭空的消失
	 * 所以要用到静态，第一就是静态的数据可以共享，而且静态的数据在方法区中，
	 * 改了就是改了，下一个对象会用到改掉的对象
	 * */
	
	//public static UserEntity[] user=new UserEntity[5];
	public static ArrayList<UserEntity> user=new ArrayList<UserEntity>();
	//添加功能，添加之后数组中就要有那个元素存在
	//添加一次的话，count就好比是索引值就要加加一次
	public static int  count=0;
	/*
	 * 这个是登陆的功能
	 * 主要就是让数组中装有的对象，和穿过来的参数进行对比
	 * 但是如果数组中还没有对象，就要让其返回false
	 * 
	 * */
	public boolean isLogin(String name, String password) {
		// TODO Auto-generated method stub
		boolean flag = false;
		//用增强for循环，因为是集合，可以用增强for循环
		//ArrayList有两种循环方式 一个是Iterator迭代器
		//第二种是增强for循环
		//增强for循环在内部已经判断了集合是否为空了。
		for (UserEntity ue:user) {
		
			flag=ue.getName().equals(name)&&ue.getPassword().equals(password);
		}
//		int length=user.size();
//		for (int i = 0; i < length; i++) {
//			UserEntity ue=new UserEntity();
//			ue=user.get(i);
//			if(ue==null)
//			{
//				//如果if完后，就break，否则的话他会继续执行下去
//				flag=false;
//				break;
//			}
//			else if(ue.getName().equals(name)&&ue.getPassword().equals(password))
//			{
//				flag=true;
//				break;
//			}
//		}
		return flag;
	}
	
	/*
	 * 注册：实际上就是实现一个添加的数组的功能
	 * */

	public void regist(UserEntity us) {
		// TODO Auto-generated method stub
		//每天加一次count索引值就加加一次
		user.add(us);
		/*
		 * count++
		 * 
		 * */
	}
	

}
