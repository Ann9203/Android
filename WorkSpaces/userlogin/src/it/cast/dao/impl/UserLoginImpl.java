package it.cast.dao.impl;

import java.util.ArrayList;

import it.cast.dao.UserLogin;
import it.cast.pojo.UserEntity;
/*
 * 
 * ʵ�ֽӿ��࣬��Ҫ����ʵ�ֽӿ��еĵ�½��ע��Ĺ���
 * */

public class UserLoginImpl implements UserLogin{

	/*
	 * ��Ϊ��½�Ĺ��ܲ�������һ����ʹ�ã�����ע���˾ͻ������ݣ����ݲ���ƾ�յ���ʧ
	 * ����Ҫ�õ���̬����һ���Ǿ�̬�����ݿ��Թ������Ҿ�̬�������ڷ������У�
	 * ���˾��Ǹ��ˣ���һ��������õ��ĵ��Ķ���
	 * */
	
	//public static UserEntity[] user=new UserEntity[5];
	public static ArrayList<UserEntity> user=new ArrayList<UserEntity>();
	//��ӹ��ܣ����֮�������о�Ҫ���Ǹ�Ԫ�ش���
	//���һ�εĻ���count�ͺñ�������ֵ��Ҫ�Ӽ�һ��
	public static int  count=0;
	/*
	 * ����ǵ�½�Ĺ���
	 * ��Ҫ������������װ�еĶ��󣬺ʹ������Ĳ������жԱ�
	 * ������������л�û�ж��󣬾�Ҫ���䷵��false
	 * 
	 * */
	public boolean isLogin(String name, String password) {
		// TODO Auto-generated method stub
		boolean flag = false;
		//����ǿforѭ������Ϊ�Ǽ��ϣ���������ǿforѭ��
		//ArrayList������ѭ����ʽ һ����Iterator������
		//�ڶ�������ǿforѭ��
		//��ǿforѭ�����ڲ��Ѿ��ж��˼����Ƿ�Ϊ���ˡ�
		for (UserEntity ue:user) {
		
			flag=ue.getName().equals(name)&&ue.getPassword().equals(password);
		}
//		int length=user.size();
//		for (int i = 0; i < length; i++) {
//			UserEntity ue=new UserEntity();
//			ue=user.get(i);
//			if(ue==null)
//			{
//				//���if��󣬾�break������Ļ��������ִ����ȥ
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
	 * ע�᣺ʵ���Ͼ���ʵ��һ����ӵ�����Ĺ���
	 * */

	public void regist(UserEntity us) {
		// TODO Auto-generated method stub
		//ÿ���һ��count����ֵ�ͼӼ�һ��
		user.add(us);
		/*
		 * count++
		 * 
		 * */
	}
	

}
