package LinkedListDemo;

import java.util.LinkedList;

public class StatcDemo {
	//����һ����ʵ��ջ���ܵĶ���
	//��Ȼ��ʵ��ջ���ܣ��������ڲ��Ƿ�װ��LinkedList����
	//˽�л�һ��LinkedList������Ȼ���ʼ��һ������
	private LinkedList linkedList;
	public StatcDemo()
	{
		
		linkedList=new LinkedList();
	}
	//��ӹ�����Ϊջ���Ƚ����
	//����ӹ����У��ͷ�װ��LinkedList��addFirst����
	public void add(Object obj)
	{
		linkedList.addFirst(obj);
	}
	//��Ϊÿ����Ӷ���addFirst������ȡ��Ԫ�ص�ʱ��ֱ��ȡ������OK��
	//��һ������ֵ
	public Object  get(int index)
	{
		return linkedList.get(index);
		
	}
	//��һ���Ӽ�����ȡ�����飬Ҫ���ݼ��ϵĳ�����ȡ������
	//�ڶ���Ҫ��Ѽ����е����е�����ȡ�����Ļ�����Ҫʹ�õ���forѭ��
	//forѭ�������Ļ���������Ӧ���г���
	public int size()
	{
		return linkedList.size();
		
	}

}
