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
	 * 从list转换成xml文档
	 * @author 星尘
	 * */
	public static void listToxml(List<Student> stulist) throws Exception
	{
		//定义pull解析器 这里参与到了序列化的问题XmlPullParserFactory
		 XmlSerializer xs= XmlPullParserFactory.newInstance().newSerializer();
		 //定义输出流		
		FileOutputStream ops=new FileOutputStream("src/students.xml");
		//设置字节输出流，要告诉pull解析器应该在哪里存入数据中，同同时设置编码格式
		xs.setOutput(ops, "UTF-8");
		//设置xml的头文件，以及是否要被约束
		//4.设置文档开始和对应的字符集
		//参数说明：第一个参数是文档使用的字符集，第二个参数是文档是否独立
		xs.startDocument("UTF-8",true);
		//设置第一个元素
		//参数说明：第一个参数是文档是否有名称空间 第二个是元素的名称
		xs.startTag(null, "Students");
		for(Student s:stulist)
		{
			xs.startTag(null,"Student");
			//设置节点的属性
			xs.attribute(null, "id", String.valueOf(s.getId()));
			xs.startTag(null, "name");
			//设置文本内容
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
		//关闭流资源
		ops.close();
		
		 
	}
	
	/**
	 * 从xml中将文件读到list集合中去 生成一个集合
	 * @author 星尘
	 * */
	public static List<Student> listToXml() throws Exception
	{
		List<Student> stulist=null;
		Student stu=null;
		//创建pull解析器
		XmlPullParser xpp=XmlPullParserFactory.newInstance().newPullParser();
		//创建输入流对象
		FileInputStream fis=new FileInputStream("src/students.xml");
		//设置字节流和字节码
		xpp.setInput(fis,"UTF-8");
		//设置事件
		int  eventType=xpp.getEventType();
		//for循环，就是当返回的时间类型等于Document节点的时候证明没有资源可以循环了
		while(eventType !=XmlPullParser.END_DOCUMENT)
		{
			//先获取节点的名称
			String TagName=xpp.getName();
			//判断类型如果为最开始的节点的时候
			if(eventType==XmlPullParser.START_TAG)
			{
				//但节点不等于最大的Students的时候创建list对象
				if(TagName.equals("Students"))
				{
					stulist=new ArrayList<Student>();
				}else if(TagName.equals("Student"))
				{
					stu=new Student();
					//设置id属性
					String id=xpp.getAttributeValue(null, "id");
					stu.setId(Integer.parseInt(id));					
				}else if(TagName.equals("name"))
				{
					//设置name属性
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
					//重xml中读取文本值得时候就要使用nextText这个方法
					String english=xpp.nextText();
					stu.setEnglish(Integer.parseInt(english));
				}else if(TagName.equals("math"))
				{
					String math=xpp.nextText();
					stu.setMath(Integer.parseInt(math));
				}		
			}
			//当事件执行到结束的标签的时候
			else if(eventType==XmlPullParser.END_TAG)
			{
				if("Student".equals(TagName))
				{
					stulist.add(stu);
					stu=null;
				}

			}
			//每次执行完毕之后都要重新获取新事件
			eventType=xpp.next();
		}
		
		return stulist;
	}
	
}
