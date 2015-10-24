package com.itheima.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import com.itheima.domain.Student;

public class pullxml {
	/**
	 * ��listת����xml�ĵ�
	 * @author �ǳ�
	 * */
	public static void listToxml(List<Student> stulist) throws Exception
	{
		//����pull������ ������뵽�����л�������XmlPullParserFactory
		 XmlSerializer xs= XmlPullParserFactory.newInstance().newSerializer();
		 //���������		
		FileOutputStream ops=new FileOutputStream("src/students.xml");
		//�����ֽ��������Ҫ����pull������Ӧ����������������У�ͬͬʱ���ñ����ʽ
		xs.setOutput(ops, "UTF-8");
		//����xml��ͷ�ļ����Լ��Ƿ�Ҫ��Լ��
		//4.�����ĵ���ʼ�Ͷ�Ӧ���ַ���
		//����˵������һ���������ĵ�ʹ�õ��ַ������ڶ����������ĵ��Ƿ����
		xs.startDocument("UTF-8",true);
		//���õ�һ��Ԫ��
		//����˵������һ���������ĵ��Ƿ������ƿռ� �ڶ�����Ԫ�ص�����
		xs.startTag(null, "Students");
		for(Student s:stulist)
		{
			xs.startTag(null,"Student");
			//���ýڵ������
			xs.attribute(null, "id", String.valueOf(s.getId()));
			xs.startTag(null, "name");
			//�����ı�����
			xs.text(s.getStuname());
			xs.endTag(null, "name");
			xs.startTag(null, "sex");
			xs.text(s.getSex());
			xs.endTag(null, "sex");
			xs.startTag(null, "chinese");
			xs.text(String.valueOf(s.getChinese()));
			xs.endTag(null, "chinese");
			xs.startTag(null,"english");
			xs.text(String.valueOf(s.getEnglish()));
			xs.endTag(null, "english");
			xs.startTag(null,"math");
			xs.text(String.valueOf(s.getMath()));
			xs.endTag(null,"math");
			xs.endTag(null,"Student");
						
		}
		xs.endTag(null, "Students");
		xs.endDocument();
		//�ر�����Դ
		ops.close();
		
		 
	}
	
	/**
	 * ��xml�н��ļ�����list������ȥ ����һ������
	 * @author �ǳ�
	 * */
	public static List<Student> listToXml() throws Exception
	{
		List<Student> stulist=null;
		Student stu=null;
		//����pull������
		XmlPullParser xpp=XmlPullParserFactory.newInstance().newPullParser();
		//��������������
		FileInputStream fis=new FileInputStream("src/students.xml");
		//�����ֽ������ֽ���
		xpp.setInput(fis,"UTF-8");
		//�����¼�
		int  eventType=xpp.getEventType();
		//forѭ�������ǵ����ص�ʱ�����͵���Document�ڵ��ʱ��֤��û����Դ����ѭ����
		while(eventType !=XmlPullParser.END_DOCUMENT)
		{
			//�Ȼ�ȡ�ڵ������
			String TagName=xpp.getName();
			//�ж��������Ϊ�ʼ�Ľڵ��ʱ��
			if(eventType==XmlPullParser.START_TAG)
			{
				//���ڵ㲻��������Students��ʱ�򴴽�list����
				if(TagName.equals("Students"))
				{
					stulist=new ArrayList<Student>();
				}else if(TagName.equals("Student"))
				{
					stu=new Student();
					//����id����
					String id=xpp.getAttributeValue(null, "id");
					stu.setId(Integer.parseInt(id));					
				}else if(TagName.equals("name"))
				{
					//����name����
					stu.setStuname(xpp.nextText());
				}else if(TagName.equals("sex"))
				{
					stu.setSex(xpp.nextText());
				}else if(TagName.equals("chinese"))
				{
					String chinese=xpp.nextText();
					stu.setChinese(Integer.parseInt(chinese));
				}else if(TagName.equals("english"))
				{
					//��xml�ж�ȡ�ı�ֵ��ʱ���Ҫʹ��nextText�������
					String english=xpp.nextText();
					stu.setEnglish(Integer.parseInt(english));
				}else if(TagName.equals("math"))
				{
					String math=xpp.nextText();
					stu.setMath(Integer.parseInt(math));
				}		
			}
			//���¼�ִ�е������ı�ǩ��ʱ��
			else if(eventType==XmlPullParser.END_TAG)
			{
				if("Student".equals(TagName))
				{
					stulist.add(stu);
					stu=null;
				}

			}
			//ÿ��ִ�����֮��Ҫ���»�ȡ���¼�
			eventType=xpp.next();
		}
		
		return stulist;
	}
	
}
